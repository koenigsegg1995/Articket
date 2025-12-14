package com.maddog.articket.email;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

import java.util.Properties;


@Service
public class MailService {


	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
		public void sendMail(String to, String subject, String messageText) {

			try {
				// 設定使用SSL連線至 Gmail smtp Server
				Properties props = new Properties();
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.socketFactory.port", "465");
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.port", "465");

				final String myGmail = "xxx@gmail.com";
				final String myGmail_password = "xxx";
				Session session = Session.getInstance(props, new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(myGmail, myGmail_password);
					}
				});
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(myGmail));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

				// 設定信中的主旨
				message.setSubject(subject);
				// 設定信中的內容
				message.setText(messageText);

				Transport.send(message);
				System.out.println("傳送成功!");
			} catch (MessagingException e) {
				System.out.println("傳送失敗!");
				e.printStackTrace();
			}
		}

		public static void main(String args[]) {

			String to = "xxx@gmail.com";

			String subject = "您的驗證碼";

			String ch_name = "親愛的會員";
			String passRandom = "123456";
			String messageText = "Hello! " + ch_name + " 請謹記此密碼: " + passRandom + "\n" + " (已經啟用)";

			MailService mailService = new MailService();
			mailService.sendMail(to, subject, messageText);
		}

}
