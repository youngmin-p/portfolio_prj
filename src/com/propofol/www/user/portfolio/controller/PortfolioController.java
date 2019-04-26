package com.propofol.www.user.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.propofol.www.user.portfolio.domain.MyPortfolioSearch;
import com.propofol.www.user.portfolio.service.MyPortfolioService;
import com.propofol.www.user.portfolio.vo.ExperienceVO;
import com.propofol.www.user.portfolio.vo.MyPortfolioVO;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PortfolioController {
	
	@Autowired(required=false)
	private MyPortfolioService mp_service;
	
	/**
	 * 내 포트폴리오 관리 화면 최초 진입점
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/portfolio/myPortfolio.do", method={GET, POST})
	public String moveMyPortfolio(HttpSession session) {
		String user_id = (String) session.getAttribute("user_id");
		
		// temp value
		user_id = "young";
		
		return "forward:/portfolio/chkLogin.do?user_id=" + user_id;
	} // moveMyPortfolio
	
	/**
	 * Session으로 로그인 여부를 확인하여, 다음 writeState로 넘겨준다.
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/portfolio/chkLogin.do", method={GET, POST})
	public String chkLogin(@RequestParam(name="user_id") String user_id) {
		int loginCnt = 0;
		
		String url = "";
		
		loginCnt = mp_service.chkLogin(user_id);
		
		// 로그인 상태에 따라 분기
		if (loginCnt == 0) {
			// user_id가 null이면 loginForm으로 이동
			url = "loginForm.do";
		} // end if
		
		if (loginCnt == 1) {
			// user_id가 null이 아니면 writeState로 이동
			url = "forward:/portfolio/writeState.do";
		} // end else
		
		return url;
	} // chkLogin
	
	/**
	 * 포트폴리오 등록 여부 확인
	 * @param user_id
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/portfolio/writeState.do", method={GET, POST})
	public String writeState(@RequestParam(name="user_id") String user_id, HttpSession session) {
		int result = 0;
		
		String session_id = (String) session.getAttribute("user_id");
		String url = "";
		
		System.out.println("session_id = " + session_id + " / user_id = " + user_id);
		
		if (user_id.equals(session_id)) {
			// portfolio 테이블에서 user_id가 존재하면 result가 1이고, 없으면 0
			// user_id 존재 유무에 따라 1 또는 0을 반환
			result = mp_service.chkWriteState(user_id);
		} // end if
		
		// temp value
		result = 1;
		
		// 로그인 상태에 따라 분기
		if (result == 0) {
			// user_id가 null이면 loginForm으로 이동
			url = "loginForm.do";
		} // end if
		
		if (result == 1) {
			// user_id가 null이 아니면 showMyPortfolio로 이동
			url = "forward:/portfolio/showMyPortfolio.do";
		} // end if
		
		return url;
	} // writeState
	
	/**
	 * 포트폴리오 조회 수행
	 * @param user_id
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/portfolio/showMyPortfolio.do", method={GET, POST})
	public String showMyPortfolio(@RequestParam(name="user_id") String user_id, HttpSession session, Model model) {
		String session_id = (String) session.getAttribute("user_id");
		
		if (user_id.equals(session_id)) {
			// 포트폴리오 조회 (섬네일, 제목)
			MyPortfolioSearch mp_search = mp_service.searchMyPortfolio(user_id);
			
			model.addAttribute("mp_search", mp_search);
			model.addAttribute("isExist", true);
		} // end if
		
		System.out.println("----- showMyPortfolio에 들어옴 : " + user_id);
		
		// temp line
		MyPortfolioSearch mp_search = mp_service.searchMyPortfolio(user_id);
		model.addAttribute("mp_search", mp_search);
		model.addAttribute("isExist", true);
		
		return "portfolio/myPortfolio";
	} // moveMyPortfolio
	
	@RequestMapping(value="/portfolio/myPortfolioAdd.do", method=POST)
	public String addMyPortfolio(MyPortfolioVO mp_vo, HttpSession session) {
		String session_id = (String) session.getAttribute("user_id");
		
		// 포트폴리오 추가
		
		
		return "redirect:/portfolio/myPortfolio.do";
	} // addMyPortfolio
	
	/**
	 * 포트폴리오 수정
	 * multipart/form-data는 parameter를 VO로 받을 수 없다.
	 * @param mp_vo
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/portfolio/myPortfolioModify.do", method=POST)
	public String modifyMyPortfolio(HttpServletRequest request, HttpSession session, Model model) {
		int result = 0;
		
		String user_id = (String) session.getAttribute("user_id");
		String moveURL = "redirect:/error/error.html";
		
		request.setAttribute("user_id", user_id);
		
		try {
			result = mp_service.modifyMyPortfolio(request);
			
			if (result == 1) {
				moveURL = "forward:myPortfolio.do";
				
				// 여기부터 작업 시작 (19-04-26 18:08)
				model.addAttribute("msg", user_id + "님의 포트폴리오가 성공적으로 수정되었습니다!");
			} // end if
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} // end catch
		
		return moveURL;
	} // modifyMyPortfolio
	
	@RequestMapping(value="/portfolio/myPortfolioRemove.do", method=GET)
	public String removeMyPortfolio(HttpSession session) {
		String session_id = (String) session.getAttribute("user_id");
		
		// 포트폴리오 삭제
		
		
		return "redirect:/portfolio/myPortfolio.do";
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
	public String experienceForm(@RequestParam(name="exp_cd", defaultValue="Edu", required=false) String exp_cd, HttpSession session, Model model) {
		// session에 저장된 아이디를 받아서 관련 경험에 등록된 정보를 조회
		String user_id = (String) session.getAttribute("user_id");
		
		System.out.println("----- exp_cd val=" + exp_cd);
		
		// 관련 경험 서비스 조회
		if ("Prj".equals(exp_cd)) {
			// 프로젝트 관련 경험 선택 시
		} // end if
		
		if ("Edu".equals(exp_cd)) {
			// 교육사항 관련 경험 선택 시
		} // end if
		
		model.addAttribute("exp_cd", exp_cd);
		
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
