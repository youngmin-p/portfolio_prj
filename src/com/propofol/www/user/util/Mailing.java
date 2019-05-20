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

import com.propofol.www.user.index.vo.ContactVO;

public class Mailing {
	public static void sendNaverEmail(ContactVO c_vo) throws AddressException, MessagingException {
		String host = "smtp.naver.com";
		String myAddress = "rich0616@naver.com";
		
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
		
		msg.setFrom(new InternetAddress(myAddress));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(myAddress));
		
		StringBuilder sbSubject = new StringBuilder();
		
		sbSubject.append(c_vo.getEmail()).append(" ").append(c_vo.getName()).append("님으로부터 문의 내용이 도착했습니다.");
		
		msg.setSubject(sbSubject.toString());
		msg.setText(c_vo.getMessage());
		
		Transport.send(msg);
	} // sendNaverEmail
	
} // class
