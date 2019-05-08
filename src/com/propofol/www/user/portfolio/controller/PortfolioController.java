package com.propofol.www.user.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.propofol.www.user.portfolio.domain.AboutMeSearch;
import com.propofol.www.user.portfolio.domain.ExperienceSearch;
import com.propofol.www.user.portfolio.domain.MyPortfolioSearch;
import com.propofol.www.user.portfolio.domain.TechStacksSearch;
import com.propofol.www.user.portfolio.domain.TellMeSearch;
import com.propofol.www.user.portfolio.service.AboutMeService;
import com.propofol.www.user.portfolio.service.ExperienceService;
import com.propofol.www.user.portfolio.service.MyPortfolioService;
import com.propofol.www.user.portfolio.service.TechStacksService;
import com.propofol.www.user.portfolio.service.TellMeService;
import com.propofol.www.user.portfolio.vo.ExperienceSearchVO;
import com.propofol.www.user.portfolio.vo.ExperienceVO;
import com.propofol.www.user.portfolio.vo.TechStacksVO;
import com.propofol.www.user.portfolio.vo.TellMeVO;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PortfolioController {
	
	@Autowired(required=false)
	private MyPortfolioService mp_service;
	
	@Autowired(required=false)
	private AboutMeService am_service;
	
	@Autowired(required=false)
	private TechStacksService ts_service;
	
	@Autowired(required=false)
	private ExperienceService exp_service;
	
	@Autowired(required=false)
	private TellMeService tm_service;
	
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
		boolean flag = false;
		
		String session_id = (String) session.getAttribute("user_id");
		
		if (user_id.equals(session_id)) {
			MyPortfolioSearch mp_search = mp_service.searchMyPortfolio(user_id);
			
			if (mp_search != null) {
				flag = true;
			} // end if
			
			model.addAttribute("mp_search", mp_search);
			model.addAttribute("isExist", flag);
		} // end if
		
		// temp line
		MyPortfolioSearch mp_search = mp_service.searchMyPortfolio(user_id);
		
		if (mp_search != null) {
			flag = true;
		} // end if
		
		model.addAttribute("mp_search", mp_search);
		model.addAttribute("isExist", flag);
		
		System.out.println("----- showMyPortfolio에 들어옴 : " + user_id + " / mp_search 객체 : " + mp_search);
		
		return "portfolio/myPortfolio";
	} // moveMyPortfolio
	
	/**
	 * 내 포트폴리오 등록
	 * @param mp_vo
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/portfolio/myPortfolioAdd.do", method=POST)
	public String addMyPortfolio(HttpServletRequest request, HttpSession session, RedirectAttributes redirect) {
		boolean flag = false;
		
		String user_id = (String) session.getAttribute("user_id");
		String moveURL = "redirect:/error/error.html";
		
		user_id = "young";
		
		if (!"".equals(user_id) && !user_id.isEmpty()) {
			request.setAttribute("user_id", user_id);			
		} // end if
		
		try {
			if (!"".equals(user_id) && user_id != null) {
				flag = mp_service.addMyPortfolio(request);
				
				if (flag) {
					moveURL = "redirect:myPortfolio.do";
					
					redirect.addFlashAttribute("msg", user_id + "님의 포트폴리오가 성공적으로 등록되었습니다.");
				} // end if
			} // end if
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} // end catch
		
		return moveURL;
	} // addMyPortfolio
	
	/**
	 * 포트폴리오 수정
	 * multipart/form-data는 parameter를 VO로 받을 수 없다.
	 * @param mp_vo
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/portfolio/myPortfolioModify.do", method=POST)
	public String modifyMyPortfolio(HttpServletRequest request, HttpSession session, RedirectAttributes redirect) {
		boolean flag = false;
		
		String user_id = (String) session.getAttribute("user_id");
		String moveURL = "redirect:/error/error.html";
		
		// temp data
		user_id = "young";
		
		if (!"".equals(user_id) && !user_id.isEmpty()) {
			request.setAttribute("user_id", user_id);			
		} // end if
		
		try {
			flag = mp_service.modifyMyPortfolio(request);
			
			if (flag) {
				moveURL = "redirect:myPortfolio.do";
				
				redirect.addFlashAttribute("msg", user_id + "님의 포트폴리오가 성공적으로 수정되었습니다.");
			} // end if
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} // end catch
		
		return moveURL;
	} // modifyMyPortfolio
	
	/**
	 * 내 포트폴리오 삭제
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/portfolio/myPortfolioRemove.do", method=POST)
	public String removeMyPortfolio(HttpSession session, RedirectAttributes redirect) {
		boolean flag = false;
		
		String user_id = (String) session.getAttribute("user_id");
		String moveURL = "redirect:/error/error.html";
		
		// temp data
		user_id = "young";
		
		if (!"".equals(user_id) && user_id != null) {
			flag = mp_service.removeMyPortfolio(user_id);
			
			if (flag) {
				moveURL = "redirect:myPortfolio.do";
				
				redirect.addFlashAttribute("msg", user_id + "님의 포트폴리오가 성공적으로 삭제되었습니다.");
			} // end if
		} // end if
		
		return moveURL;
	} // removeMyPortfolio
	
	// -------------------- 포트폴리오 자기소개 시작 -------------------- //
	
	/**
	 * 자기소개 조회
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/portfolio/aboutMeForm.do", method=GET)
	public String aboutMeForm(HttpSession session, Model model) {
		boolean flag = false;
		
		String user_id = (String) session.getAttribute("user_id");
		
		user_id = "young";
		
		if (user_id != null && !"".equals(user_id)) {
			AboutMeSearch am_search = am_service.searchAboutMe(user_id);
			
			System.out.println(am_search);
			
			if (am_search != null) {
				flag = true;
				
				model.addAttribute("am_search", am_search);
				model.addAttribute("isExist", flag);
			} // end if
		} // end if
		
		return "portfolio/aboutMeForm";
	} // aboutMeForm
	
	@RequestMapping(value="/portfolio/aboutMeAdd.do", method=POST)
	public String addAboutMe(HttpServletRequest request, HttpSession session, RedirectAttributes redirect) {
		boolean flag = false;
		
		String user_id = (String) session.getAttribute("user_id");
		String moveURL = "redirect:/error/error.html";
		
		user_id = "young";
		
		try {
			if (user_id != null && !"".equals(user_id)) {
				request.setAttribute("user_id", user_id);
				
				flag = am_service.addAboutMe(request);
				
				if (flag) {
					moveURL = "redirect:aboutMeForm.do";
					
					redirect.addFlashAttribute("msg", user_id + "님의 자기소개가 성공적으로 등록되었습니다.");
				} // end if
			} // end if
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} // end catch
		
		return moveURL;
	} // addAboutMe
	
	/**
	 * 자기소개 적용 (수정)
	 * @param request
	 * @param session
	 * @param redirect
	 * @return
	 */
	@RequestMapping(value="/portfolio/aboutMeModify.do", method=POST)
	public String modifyAboutMe(HttpServletRequest request, HttpSession session, RedirectAttributes redirect) {
		boolean flag = false;
		
		String user_id = (String) session.getAttribute("user_id");
		String moveURL = "redirect:/error/error.html";
		
		// temp data
		user_id = "young";
		
		try {
			if (user_id != null && !"".equals(user_id)) {
				request.setAttribute("user_id", user_id);
				
				flag = am_service.modifyAboutMe(request);
				
				if (flag) {
					moveURL = "redirect:aboutMeForm.do";
					
					redirect.addFlashAttribute("msg", user_id + "님의 자기소개가 성공적으로 적용되었습니다.");
				} // end if				
			} // end if
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} // end catch
		
		return moveURL;
	} // modifyAboutMe
	
	/**
	 * 자기소개 초기화
	 * @param session
	 * @param redirect
	 * @return
	 */
	@RequestMapping(value="/portfolio/aboutMeReset.do", method=POST)
	public String resetAboutMe(HttpSession session, RedirectAttributes redirect) {
		boolean flag = false;
		
		String user_id = (String) session.getAttribute("user_id");
		String moveURL = "redirect:/error/error.html";
		
		// temp data
		user_id = "young";
		
		if (user_id != null && !"".equals(user_id)) {
			flag = am_service.resetAboutMe(user_id);
			
			if (flag) {
				moveURL = "redirect:aboutMeForm.do";
				
				redirect.addFlashAttribute("msg", user_id + "님의 자기소개가 성공적으로 초기화되었습니다.");
			} // end if
		} // end if
		
		return moveURL;
	} // resetAboutMe
	
	// -------------------- 포트폴리오 기술 스택 시작 -------------------- //
	
	@RequestMapping(value="/portfolio/techStacksForm.do", method=GET)
	public String techStacksForm(HttpSession session, Model model) {
		boolean flag = false;
		
		String user_id = (String) session.getAttribute("user_id");
		
		// temp data
		user_id = "young";
		
		if (user_id != null && !"".equals(user_id)) {
			TechStacksSearch ts_search = ts_service.searchTechStacks(user_id);
			
			if (ts_search != null) {
				flag = true;
				
				model.addAttribute("ts_search", ts_search);
				model.addAttribute("isExist", flag);
			} // end if
		} // end if
		
		return "portfolio/techStacksForm";
	} // techStacksForm
	
	@RequestMapping(value="/portfolio/techStacksAdd.do", method=GET)
	public String addTechStacks(@RequestParam(name="selected_technique", required=false) String[] selected_technique, HttpSession session, RedirectAttributes redirect) {
		boolean flag = false;
		
		String user_id = (String) session.getAttribute("user_id");
		String moveURL = "redirect:/error/error.html";
		
		// temp data
		user_id = "young";
		
		if (user_id != null && !"".equals(user_id)) {
			TechStacksVO ts_vo = new TechStacksVO();
			
			ts_vo.setUser_id(user_id);
			ts_vo.setSelected_technique(selected_technique);
			
			flag = ts_service.addTechStacks(ts_vo);
			
			if (flag) {
				moveURL = "redirect:techStacksForm.do";
				
				redirect.addFlashAttribute("msg", user_id + "님의 기술 스택이 성공적으로 등록되었습니다.");
			} // end if
		} // end if
		
		return moveURL;
	} // addTechStacks
	
	@RequestMapping(value="/portfolio/techStacksModify.do", method=GET)
	public String modifyTechStacks(@RequestParam(name="selected_technique", required=false) String[] selected_technique, HttpSession session, RedirectAttributes redirect) {
		boolean flag = false;
		
		String user_id = (String) session.getAttribute("user_id");
		String moveURL = "redirect:/error/error.html";
		
		// temp data
		user_id = "young";
		
		if (user_id != null && !"".equals(user_id)) {
			TechStacksVO ts_vo = new TechStacksVO();
			
			ts_vo.setUser_id(user_id);
			ts_vo.setSelected_technique(selected_technique);
			
			flag = ts_service.modifyTechStacks(ts_vo);
			
			if (flag) {
				moveURL = "redirect:techStacksForm.do";
				
				redirect.addFlashAttribute("msg", user_id + "님의 기술 스택이 성공적으로 수정되었습니다.");
			} // end if
		} // end if
		
		return moveURL;
	} // modifyTechStacks
	
	@RequestMapping(value="/portfolio/techStacksReset.do", method=GET)
	public String resetTechStacks(HttpSession session, RedirectAttributes redirect) {
		boolean flag = false;
		
		String user_id = (String) session.getAttribute("user_id");
		String moveURL = "redirect:/error/error.html";
		
		// temp data
		user_id = "young";
		
		if (user_id != null && !"".equals(user_id)) {
			flag = ts_service.resetTechStacks(user_id);
			
			if (flag) {
				moveURL = "redirect:techStacksForm.do";
				
				redirect.addFlashAttribute("msg", user_id + "님의 기술 스택이 성공적으로 초기화되었습니다.");
			} // end if
		} // end if
		
		return moveURL;
	} // resetTechStacks
	
	// -------------------- 포트폴리오 관련 경험 시작 -------------------- //
	
	@RequestMapping(value="/portfolio/experienceForm.do", method=GET)
	public String experienceForm(@RequestParam(name="exp_cd", defaultValue="Edu", required=false) String exp_cd, HttpSession session, Model model) {
		boolean flag = false;
		
		String user_id = (String) session.getAttribute("user_id");
		
		user_id = "young";
		
		if (user_id != null && !"".equals(user_id)) {
			/*
			ExperienceSearchVO es_vo = 
					new ExperienceSearchVO(user_id, exp_cd);
			*/
			
			ExperienceSearchVO es_vo = new ExperienceSearchVO();
			
			es_vo.setUser_id(user_id);
			es_vo.setExp_cd(exp_cd);
			
			// "Prj"는 아직 없기 때문에 못 불러오는 중"
			System.out.println("---- Experience user_id = " + user_id + " / exp_cd = " + exp_cd);
			
			ExperienceSearch exp_search = exp_service.searchExperience(es_vo);
			
			if (exp_search != null) {
				flag = true;
				
				System.out.println("exp_search의 exp_cd = " + exp_search.getExp_cd());
				
				model.addAttribute("exp_search", exp_search);
				model.addAttribute("isExist", flag);
			} // end if
		} // end if
		
		return "portfolio/experienceForm";
	} // experienceForm
	
	@RequestMapping(value="/portfolio/experienceAdd.do", method=POST)
	public String addExperience(HttpServletRequest request, HttpSession session, RedirectAttributes redirect) {
		boolean flag = false;
		
		String user_id = (String) session.getAttribute("user_id");
		String moveURL = "redirect:/error/error.html";
		String exp_cd = request.getParameter("exp_cd");
		String word = "";
		
		// temp data
		user_id = "young";
		
		try {
			if (user_id != null && !"".equals(user_id)) {
				request.setAttribute("user_id", user_id);
				
				flag = exp_service.addExperience(request);
				
				if (flag) {
					moveURL = "redirect:experienceForm.do";
					
					if (exp_cd == "Edu") {
						word = "교육사항이";
					} // end if
					
					if (exp_cd == "Prj") {
						word = "프로젝트가";
					} // end if
					
					redirect.addFlashAttribute("msg", user_id + "님의  " + word + " 성공적으로 등록되었습니다.");
				} // end if
			} // end if
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} // end catch
		
		return moveURL;
	} // addExperience
	
	@RequestMapping(value="/portfolio/experienceModify.do", method=POST)
	public String modifyExperience(ExperienceVO exp_vo, HttpSession session) {
		String user_id = (String) session.getAttribute("user_id");
		
		// 관련 경험 수정
		
		
		return "portfolio/experienceForm";
	} // modifyExperience
	
	@RequestMapping(value="/portfolio/experienceReset.do", method=POST)
	public String resetExperience(@RequestParam(name="exp_cd", required=false) String exp_cd, HttpSession session, RedirectAttributes redirect) {
		boolean flag = false;
		
		String user_id = (String) session.getAttribute("user_id");
		String moveURL = "redirect:/error/error.html";
		String word = "";
		
		// temp data
		user_id = "young";
		
		if (user_id != null && !"".equals(user_id)) {
			flag = exp_service.resetExperience(user_id);
			
			if (flag) {
				moveURL = "redirect:experienceForm.do";
				
				if (exp_cd == "Edu") {
					word = "교육사항이";
				} // end if
				
				if (exp_cd == "Prj") {
					word = "프로젝트가";
				} // end if
				
				redirect.addFlashAttribute("msg", user_id + "님의 " + word + " 성공적으로 초기화되었습니다.");
			} // end if
		} // end if
		
		return moveURL;
	} // resetExperience
	
	// -------------------- 포트폴리오 연락처 시작 -------------------- //
	
	@RequestMapping(value="/portfolio/tellMeForm.do", method=GET)
	public String tellMeForm(HttpSession session, Model model) {
		boolean flag = false;
		
		String user_id = (String) session.getAttribute("user_id");
		
		// temp data
		user_id = "young";
		
		if (user_id != null && !"".equals(user_id)) {
			TellMeSearch tm_search = tm_service.searchTellMe(user_id);
			
			if (tm_search != null) {
				flag = true;
				
				model.addAttribute("tm_search", tm_search);
				model.addAttribute("isExist", flag);
			} // end if
		} // end if
		
		return "portfolio/tellMeForm";
	} // tellMeForm
	
	@RequestMapping(value="/portfolio/tellMeAdd.do", method=GET)
	public String addTellMe(TellMeVO tm_vo, HttpSession session, RedirectAttributes redirect) {
		boolean flag = false;
		
		String user_id = (String) session.getAttribute("user_id");
		String moveURL = "redirect:/error/error.html";
		
		// temp data
		user_id = "young";
		
		if (user_id != null && !"".equals(user_id)) {
			tm_vo.setUser_id(user_id);
			
			flag = tm_service.addTellMe(tm_vo);
			
			if (flag) {
				moveURL = "redirect:tellMeForm.do";
				
				redirect.addFlashAttribute("msg", user_id + "님의 연락처가 성공적으로 등록되었습니다.");
			} // end if
		} // end if
		
		return moveURL;
	} // addTellMe
	
	@RequestMapping(value="/portfolio/tellMeModify.do", method=GET)
	public String modifyTellMe(TellMeVO tm_vo, HttpSession session, RedirectAttributes redirect) {
		boolean flag = false;
		
		String user_id = (String) session.getAttribute("user_id");
		String moveURL = "redirect:/error/error.html";
		
		// temp data
		user_id = "young";
		
		if (user_id != null && !"".equals(user_id)) {
			tm_vo.setUser_id(user_id);
			
			flag = tm_service.modifyTellMe(tm_vo);
			
			if (flag) {
				moveURL = "redirect:tellMeForm.do";
				
				redirect.addFlashAttribute("msg", user_id + "님의 연락처가 성공적으로 수정되었습니다.");
			} // end if
		} // end if
		
		return moveURL;
	} // modifyTellMe
	
	@RequestMapping(value="/portfolio/tellMeReset.do", method=GET)
	public String resetTellMe(HttpSession session, RedirectAttributes redirect) {
		boolean flag = false;
		
		String user_id = (String) session.getAttribute("user_id");
		String moveURL = "redirect:/error/error.html";
		
		// temp data
		user_id = "young";
		
		if (user_id != null && !"".equals(user_id)) {
			flag = tm_service.resetTellMe(user_id);
			
			if (flag) {
				moveURL = "redirect:tellMeForm.do";
				
				redirect.addFlashAttribute("msg", user_id + "님의 연락처가 성공적으로 초기화되었습니다.");
			} // end if
		} // end if
		
		return moveURL;
	} // resetTellMe
	
	// -------------------- 포트폴리오 게시판 시작 -------------------- //
	
	@RequestMapping(value="/portfolio/portfolioList.do", method=GET)
	public String movePortfolioList() {
		// 포트폴리오 섬네일, 제목 조회 결과
		
		return "portfolio/portfolioList";
	} // movePortfolioList
	
} // class
