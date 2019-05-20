package com.propofol.www.user.index.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.propofol.www.user.index.service.IndexService;
import com.propofol.www.user.index.vo.ContactVO;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
	
	@Autowired(required=false)
	private IndexService idx_service;
	
	@RequestMapping(value="/index.do", method=GET)
	public String moveIndexPage(HttpSession session, Model model) {
		
		String user_id = (String) session.getAttribute("user_id");
		
		if (user_id != null && !"".equals(user_id)) {
			model.addAttribute("user_id", user_id);
		} // end if
		
		return "index";
	} // moveIndexPage
	
	@RequestMapping(value="/contact.do", method=POST)
	public String contactMe(ContactVO c_vo, RedirectAttributes redirect) {
		boolean flag = false;
		
		String msg = "";
		
		flag = idx_service.sendNaverEmail(c_vo);
		
		if (flag) {
			msg = "성공적으로 메일이 발송되었습니다!";
		} else {
			msg = "메일 발송 중 문제가 발생했습니다! 잠시 후 다시 시도해주세요.";
		} // end else
		
		redirect.addFlashAttribute("msg", msg);
		
		return "redirect:/index.do";
	} // contactMe
	
} // class
