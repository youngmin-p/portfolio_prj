package com.propofol.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.propofol.www.domain.MyPortfolioSearch;
import com.propofol.www.service.MyPortfolioService;
import com.propofol.www.vo.ExperienceVO;
import com.propofol.www.vo.MyPortfolioVO;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
public class PortfolioController {
	
	@RequestMapping(value="/portfolio/myPortfolio.do", method=GET)
	public String moveMyPortfolio(HttpSession session) {
		String user_id = (String) session.getAttribute("user_id");
		
		user_id = "park";
		
		return "forward:/portfolio/chkLogin.do?user_id=" + user_id;
	} // moveMyPortfolio
	
	/**
	 * Session으로 로그인 여부를 확인하여, 다음 writeState로 넘겨준다.
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/portfolio/chkLogin.do", method=GET)
	public String chkLogin(@RequestParam("user_id") String user_id) {
		int loginCnt = 0;
		
		String url = "";
		
		MyPortfolioService mp_service = new MyPortfolioService();
		
		loginCnt = mp_service.chkLogin(user_id);
		
		// 로그인 상태에 따라 분기
		if (loginCnt == 0) {
			// user_id가 null이면 loginForm으로 이동
			url = "loginForm.do";
		} // end if
		
		if (loginCnt == 1) {
			// user_id가 null이 아니면 writeState로 이동
//			url = "forward:/portfolio/writeState.do?user_id=" + user_id;
			url = "forward:/portfolio/writeState.do?";
		} // end else
		
		return url;
	} // chkLogin
	
	@RequestMapping(value="/portfolio/writeState.do", method=GET)
	public String writeState(@RequestParam("user_id") String user_id, HttpSession session) {
		int result = 0;
		
		String session_id = (String) session.getAttribute("user_id");
		String url = "";
		
		System.out.println("session_id = " + session_id);
		System.out.println("user_id = " + user_id);
		
		if (user_id.equals(session_id)) {
			// portfolio 테이블에서 user_id가 존재하면 result가 1이고, 없으면 0
			// DB 조회 수행 (Service)
			MyPortfolioService mp_service = new MyPortfolioService();
			
			// user_id 존재 유무에 따라 1 또는 0을 반환
//			result = mp_service.chkWriteState(user_id);
		} // end if
		
		result = 1;
		
		// 로그인 상태에 따라 분기
		if (result == 0) {
			// user_id가 null이면 loginForm으로 이동
			url = "loginForm.do";
		} // end if
		
		if (result == 1) {
			// user_id가 null이 아니면 showMyPortfolio로 이동
			url = "forward:/portfolio/showMyPortfolio.do?";
		} // end else
		
		System.out.println("----- writeState에서의 user_id : " + user_id);
		
		return url;
	} // writeState
	
	@RequestMapping(value="/portfolio/showMyPortfolio.do", method=GET)
	public String showMyPortfolio(@RequestParam("user_id") String user_id, HttpSession session, Model model) {
		String session_id = (String) session.getAttribute("user_id");
		
//		if (user_id.equals(session_id)) {
//			MyPortfolioService mp_service = new MyPortfolioService();
//			
//			// 포트폴리오 조회 (섬네일, 제목)
//			MyPortfolioSearch mp_search = mp_service.searchMyPortfolio(user_id);
//			
//			model.addAttribute("mp_search", mp_search);
//		} // end if
		
		System.out.println("----- showMyPortfolio에 들어옴 : " + user_id);
		
		return "portfolio/myPortfolio";
	} // moveMyPortfolio
	
	@RequestMapping(value="/portfolio/myPortfolioAdd.do", method=GET)
	public String addMyPortfolio(MyPortfolioVO mp_vo, HttpSession session) {
		String session_id = (String) session.getAttribute("user_id");
		
		// 포트폴리오 추가
		
		
		return "portfolio/showMyPortfolio.do?user_id" + session_id;
	} // addMyPortfolio
	
	@RequestMapping(value="/portfolio/myPortfolioModify.do", method=GET)
	public String modifyMyPortfolio(MyPortfolioVO mp_vo, HttpSession session) {
		String session_id = (String) session.getAttribute("user_id");
		
		// 포트폴리오 수정
		
		
		return "portfolio/showMyPortfolio.do?user_id" + session_id;
	} // modifyMyPortfolio
	
	@RequestMapping(value="/portfolio/myPortfolioRemove.do", method=GET)
	public String removeMyPortfolio(HttpSession session) {
		String session_id = (String) session.getAttribute("user_id");
		
		// 포트폴리오 삭제
		
		
		return "portfolio/showMyPortfolio.do?user_id" + session_id;
	} // removeMyPortfolio
	
	// -------------------- 포트폴리오 세부 업무 처리 시작 -------------------- //
	// -------------------- 포트폴리오 자기소개 시작 -------------------- //
	
	@RequestMapping(value="/portfolio/aboutMeForm.do", method=GET)
	public String aboutMeForm(HttpSession session) {
		
		
		return "portfolio/aboutMeForm";
	} // aboutMeForm
	
	// -------------------- 포트폴리오 기술 스택 시작 -------------------- //
	
	@RequestMapping(value="/portfolio/techStacksForm.do", method=GET)
	public String techStacksForm(HttpSession session) {
		
		
		return "portfolio/techStacksForm";
	} // techStacksForm
	
	// -------------------- 포트폴리오 관련 경험 시작 -------------------- //
	
	@RequestMapping(value="/portfolio/experienceForm.do", method=GET)
	public String experienceForm(HttpSession session) {
		// session에 저장된 아이디를 받아서 관련 경험에 등록된 정보를 조회
		String user_id = (String) session.getAttribute("user_id");
		
		// 관련 경험 서비스 조회
		
		
		return "portfolio/experienceForm";
	} // experienceForm
	
	@RequestMapping(value="/portfolio/experienceAdd.do", method=GET)
	public String experienceAdd(ExperienceVO exp_vo, HttpSession session) {
		String user_id = (String) session.getAttribute("user_id");
		
		// 관련 경험 추가
		
		
		return "portfolio/experienceForm";
	} // experienceAdd
	
	@RequestMapping(value="/portfolio/experienceModify.do", method=GET)
	public String experienceModify(ExperienceVO exp_vo, HttpSession session) {
		String user_id = (String) session.getAttribute("user_id");
		
		// 관련 경험 수정
		
		
		return "portfolio/experienceForm";
	} // experienceModify
	
	// -------------------- 포트폴리오 연락처 시작 -------------------- //
	
	@RequestMapping(value="/portfolio/tellMeForm.do", method=GET)
	public String tellMeForm(HttpSession session) {
		
		
		return "portfolio/tellMeForm";
	} // tellMeForm
	
	// -------------------- 포트폴리오 게시판 시작 -------------------- //
	
	@RequestMapping(value="/portfolio/portfolioList.do", method=GET)
	public String movePortfolioList() {
		// 포트폴리오 섬네일, 제목 조회 결과
		
		return "portfolio/portfolioList";
	} // movePortfolioList
	
} // class
