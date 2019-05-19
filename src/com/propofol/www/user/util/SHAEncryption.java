package com.propofol.www.user.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.tomcat.util.codec.binary.Base64;

public class SHAEncryption {
	private static Base64 base64 = new Base64();
	
	public static String SHAEncoding(String plaintext) {
		String ciphertext = "";
		
		if (plaintext != null && !"".equals(plaintext)) {
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				
				md.update(plaintext.getBytes());
				
				ciphertext = new String(base64.encode(md.digest()));
			} catch (NoSuchAlgorithmException nsae) {
				nsae.printStackTrace();
			} // end catch
		} // end if
		
		return ciphertext;
	} // SHAEncoding
	
} // class
