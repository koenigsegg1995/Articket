package com.maddog.articket.email.impl;

import com.maddog.articket.email.pri.MailService;
import jakarta.mail.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * 寄送 G-Mail Service Implementation
 */
@Service("mailService")
@Slf4j
public class GMailService implements MailService {

	/**
	 * Email 發送器
	 */
	@Autowired
	private JavaMailSender mailSender;

	/**
	 * 以 G-Mail 帳號寄送 Email
	 *
	 * @param to
	 * 			收件者
	 * @param subject
	 * 			主旨
	 * @param text
	 * 			內文
	 */
	@Override
	public void sendMail(String to, String subject, String text) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();

			// 設定信件資訊
			message.setTo(to);
			message.setSubject(subject);
			message.setText(text);

			// 寄送
			mailSender.send(message);

			log.info("Email 寄送成功！");
		} catch (Exception e) {
			log.error("Email 寄送失敗！ {}", e.getMessage());
		}
	}

}
