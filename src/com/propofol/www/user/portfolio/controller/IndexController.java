package com.propofol.www.user.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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
	
} // class
