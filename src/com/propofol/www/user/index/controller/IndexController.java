package com.propofol.www.user.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.propofol.www.user.index.vo.ContactVO;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
	
	@RequestMapping(value="/index.do", method=GET)
	public String moveIndexPage(HttpSession session, Model model) {
		
		String user_id = (String) session.getAttribute("user_id");
		
		if (user_id != null && !"".equals(user_id)) {
			model.addAttribute("user_id", user_id);
		} // end if
		
		return "index";
	} // moveIndexPage
	
	@RequestMapping(value="/contact.do", method=POST)
	public String contactMe(ContactVO c_vo) {
		// 메일 서비스 추가
		
		
		return "redirect:/index.do";
	} // contactMe
	
} // class
