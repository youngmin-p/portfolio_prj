package com.propofol.www.user.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.propofol.www.user.dao.MemberDAO;
import com.propofol.www.user.member.vo.LoginVO;

@Component
public class MemberService {
	
	@Autowired(required=false)
	private MemberDAO m_dao;
	
	public MemberService() {
		m_dao = MemberDAO.getInstance();
		
	} // MemberService
	
	/**
	 * 로그인 체크
	 * @param l_vo
	 * @return
	 */
	public boolean searchLogin(LoginVO l_vo) {
		boolean flag = false;
		
		flag = (m_dao.selectLogin(l_vo) == 1);
		
		return flag;
	} // searchLogin
	
} // class
