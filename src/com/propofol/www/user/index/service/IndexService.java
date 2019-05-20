package com.propofol.www.user.index.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.stereotype.Component;

import com.propofol.www.user.index.vo.ContactVO;
import com.propofol.www.user.util.Mailing;

@Component
public class IndexService {
	
	/**
	 * 네이버 메일 전송
	 * @param l_vo
	 * @return
	 */
	public boolean sendNaverEmail(ContactVO c_vo) {
		boolean flag = false;
		
		try {
			Mailing.sendNaverEmail(c_vo);
			
			flag = true;
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		} // end catch
		
		return flag;
	} // searchLogin
	
} // class
