package com.propofol.www.user.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.propofol.www.user.member.service.MemberService;
import com.propofol.www.user.member.vo.LoginVO;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class LoginController {
	
	@Autowired(required=false)
	private MemberService m_service;
	
	@RequestMapping(value="/member/loginForm.do", method=GET)
	public String moveLoginPage(HttpServletRequest request, HttpSession session) {
		
		return "member/loginForm";
	} // moveLoginPage
	
	@RequestMapping(value="/member/loginProcess.do", method=POST)
	public String processLogin(LoginVO l_vo, HttpSession session, RedirectAttributes redirect) {
		boolean flag = false;
		
		String moveURL = "redirect:./loginForm.do";
		
		flag = m_service.searchLogin(l_vo);
		
		if (flag) {
			session.setAttribute("user_id", l_vo.getUser_id());
			session.setMaxInactiveInterval(60 * 10 * 1 * 1);
			
			moveURL = "redirect:" + session.getAttribute("currentPage");
		} else {
			redirect.addFlashAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
		} // end else
		
		return moveURL;
	} // processLogin
	
	@RequestMapping(value="/member/logoutProcess.do", method=GET)
	public String processLogout(HttpServletRequest request, HttpSession session) {
		String user_id = (String) session.getAttribute("user_id");
		
		if (user_id != null && !"".equals(user_id)) {
			session.removeAttribute("user_id");
			session.removeAttribute("currentPage");
			session.invalidate();
		} // end if
		
		return "redirect:/index.do";
	} // processLogout
	
} // class
