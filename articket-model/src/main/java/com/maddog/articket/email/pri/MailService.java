package com.maddog.articket.email.pri;

/**
 * 寄送 Email Service Interface
 */
public interface MailService {

	/**
	 * 寄送 Email
	 *
	 * @param to
	 * 			收件者
	 * @param subject
	 * 			主旨
	 * @param text
	 * 			內文
 	 */
	void sendMail(String to, String subject, String text);

}
