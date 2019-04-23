package com.propofol.www.user.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class IndexController {
	
	@RequestMapping(value="/index.do", method=GET)
	public String moveIndexPage() {
		
		return "index";
	} // moveIndexPage
	
} // class
