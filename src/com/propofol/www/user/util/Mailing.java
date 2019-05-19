package com.propofol.www.user.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailing {
	public static void sendNaverEmail(String subject, String text) throws AddressException, MessagingException {
		String host = "smtp.naver.com";
		
		Properties prop = new Properties();
		
		prop.put("mail.transport.protocol", "smtp");
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.enable", "true");
		prop.put("mail.smtp.trust", host);
		
		Authenticator auth = new SMTPAuthenticator();
		
		Session mailSession = Session.getDefaultInstance(prop, auth);
		
		Message msg = new MimeMessage(mailSession);
		
		msg.setFrom(new InternetAddress("rich0616@naver.com"));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress("rich0616@naver.com"));
		
		msg.setSubject(subject);
		msg.setText(text);
		
		Transport.send(msg);
	} // sendNaverEmail
	
	public static void main(String[] args) {
		String subject = "테스트 메일입니다.";
		String text = "테스트 내용입니다. 보낸 사람 정보 : 영민";
		
		try {
			Mailing.sendNaverEmail(subject, text);
			System.out.println("----- 전송 완료");
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
		
	}
	
} // class
