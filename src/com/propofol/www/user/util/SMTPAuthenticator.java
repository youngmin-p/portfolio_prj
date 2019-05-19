package com.propofol.www.user.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {
	protected PasswordAuthentication getPasswordAuthentication() {
		final String userName = "";
		final String password = "";
		
		return new PasswordAuthentication(userName, password);
	} // getPasswordAuthentication
	
} // class
