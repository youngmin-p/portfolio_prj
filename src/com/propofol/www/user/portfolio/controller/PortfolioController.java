package com.propofol.www.user.portfolio.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oreilly.servlet.MultipartRequest;
import com.propofol.www.user.portfolio.domain.AboutMeSearch;
import com.propofol.www.user.portfolio.domain.ExperienceSearch;
import com.propofol.www.user.portfolio.domain.MyPortfolioSearch;
import com.propofol.www.user.portfolio.domain.PortfolioListSearch;
import com.propofol.www.user.portfolio.domain.TechStacksSearch;
import com.propofol.www.user.portfolio.domain.TellMeSearch;
import com.propofol.www.user.portfolio.service.AboutMeService;
import com.propofol.www.user.portfolio.service.ExperienceService;
import com.propofol.www.user.portfolio.service.MyPortfolioService;
import com.propofol.www.user.portfolio.service.PortfolioService;
import com.propofol.www.user.portfolio.service.TechStacksService;
import com.propofol.www.user.portfolio.service.TellMeService;
import com.propofol.www.user.portfolio.vo.ExperienceSearchVO;
import com.propofol.www.user.portfolio.vo.ExperienceVO;
import com.propofol.www.user.portfolio.vo.TechStacksVO;
import com.propofol.www.user.portfolio.vo.TellMeVO;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
	
	@Autowired(required=false)
	private PortfolioService pl_service;
	
	/**
	 * �� ��Ʈ������ ���� ȭ�� ���� ������
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
	 * Session���� �α��� ���θ� Ȯ���Ͽ�, ���� writeState�� �Ѱ��ش�.
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/portfolio/chkLogin.do", method={GET, POST})
	public String chkLogin(@RequestParam(name="user_id") String user_id) {
		int loginCnt = 0;
		
		String url = "";
		
		loginCnt = mp_service.chkLogin(user_id);
		
		// �α��� ���¿� ���� �б�
		if (loginCnt == 0) {
			// user_id�� null�̸� loginForm���� �̵�
			url = "loginForm.do";
		} // end if
		
		if (loginCnt == 1) {
			// user_id�� null�� �ƴϸ� writeState�� �̵�
			url = "forward:/portfolio/writeState.do";
		} // end else
		
		return url;
	} // chkLogin
	
	/**
	 * ��Ʈ������ ��� ���� Ȯ��
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
			// portfolio ���̺��� user_id�� �����ϸ� result�� 1�̰�, ������ 0
			// user_id ���� ������ ���� 1 �Ǵ� 0�� ��ȯ
			result = mp_service.chkWriteState(user_id);
		} // end if
		
		// temp value
		result = 1;
		
		// �α��� ���¿� ���� �б�
		if (result == 0) {
			// user_id�� null�̸� loginForm���� �̵�
			url = "loginForm.do";
		} // end if
		
		if (result == 1) {
			// user_id�� null�� �ƴϸ� showMyPortfolio�� �̵�
			url = "forward:/portfolio/showMyPortfolio.do";
		} // end if
		
		return url;
	} // writeState
	
	/**
	 * ��Ʈ������ ��ȸ ����
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
		
		System.out.println("----- showMyPortfolio�� ���� : " + user_id + " / mp_search ��ü : " + mp_search);
		
		return "portfolio/myPortfolio";
	} // moveMyPortfolio
	
	/**
	 * �� ��Ʈ������ ���
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
					
					redirect.addFlashAttribute("msg", user_id + "���� ��Ʈ�������� ���������� ��ϵǾ����ϴ�.");
				} // end if
			} // end if
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} // end catch
		
		return moveURL;
	} // addMyPortfolio
	
	/**
	 * ��Ʈ������ ����
	 * multipart/form-data�� parameter�� VO�� ���� �� ����.
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
				
				redirect.addFlashAttribute("msg", user_id + "���� ��Ʈ�������� ���������� �����Ǿ����ϴ�.");
			} // end if
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} // end catch
		
		return moveURL;
	} // modifyMyPortfolio
	
	/**
	 * �� ��Ʈ������ ����
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
				
				redirect.addFlashAttribute("msg", user_id + "���� ��Ʈ�������� ���������� �����Ǿ����ϴ�.");
			} // end if
		} // end if
		
		return moveURL;
	} // removeMyPortfolio
	
	// -------------------- ��Ʈ������ �ڱ�Ұ� ���� -------------------- //
	
	/**
	 * �ڱ�Ұ� ��ȸ
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
					
					redirect.addFlashAttribute("msg", user_id + "���� �ڱ�Ұ��� ���������� ��ϵǾ����ϴ�.");
				} // end if
			} // end if
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} // end catch
		
		return moveURL;
	} // addAboutMe
	
	/**
	 * �ڱ�Ұ� ���� (����)
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
					
					redirect.addFlashAttribute("msg", user_id + "���� �ڱ�Ұ��� ���������� ����Ǿ����ϴ�.");
				} // end if				
			} // end if
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} // end catch
		
		return moveURL;
	} // modifyAboutMe
	
	/**
	 * �ڱ�Ұ� �ʱ�ȭ
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
				
				redirect.addFlashAttribute("msg", user_id + "���� �ڱ�Ұ��� ���������� �ʱ�ȭ�Ǿ����ϴ�.");
			} // end if
		} // end if
		
		return moveURL;
	} // resetAboutMe
	
	// -------------------- ��Ʈ������ ��� ���� ���� -------------------- //
	
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
				
				redirect.addFlashAttribute("msg", user_id + "���� ��� ������ ���������� ��ϵǾ����ϴ�.");
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
				
				redirect.addFlashAttribute("msg", user_id + "���� ��� ������ ���������� �����Ǿ����ϴ�.");
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
				
				redirect.addFlashAttribute("msg", user_id + "���� ��� ������ ���������� �ʱ�ȭ�Ǿ����ϴ�.");
			} // end if
		} // end if

		return moveURL;
	} // resetTechStacks
	
	// -------------------- ��Ʈ������ ���� ���� ���� -------------------- //
	
	@RequestMapping(value="/portfolio/experienceForm.do", method=GET)
	public String experienceForm(@RequestParam(name="exp_cd", defaultValue="Edu", required=false) String exp_cd, HttpSession session, Model model) {
		boolean flag = false;
		
		String user_id = (String) session.getAttribute("user_id");
		
		// temp data
		user_id = "young";
		
		if (user_id != null && !"".equals(user_id)) {
			ExperienceSearchVO es_vo = new ExperienceSearchVO();
			
			es_vo.setUser_id(user_id);
			es_vo.setExp_cd(exp_cd);
			
			ExperienceSearch exp_search = exp_service.searchExperience(es_vo);
			
			if (exp_search != null) {
				flag = true;
				
				model.addAttribute("exp_search", exp_search);
			} // end if
			
			if (exp_search == null) {
				model.addAttribute("nexp_cd", exp_cd);
			} // end if
			
			model.addAttribute("isExist", flag);
		} // end if
		
		return "portfolio/experienceForm";
	} // experienceForm
	
	@RequestMapping(value="/portfolio/experienceAdd.do", method=POST)
	public String addExperience(HttpServletRequest request, HttpSession session, RedirectAttributes redirect) {
		String user_id = (String) session.getAttribute("user_id");
		String moveURL = "redirect:/error/error.html";
		String exp_cd = "";
		String word = "";
		
		// temp data
		user_id = "young";
		
		try {
			if (user_id != null && !"".equals(user_id)) {
				request.setAttribute("user_id", user_id);
				
				exp_cd = exp_service.addExperience(request);
				
				if (!"".equals(exp_cd)) {
					moveURL = "redirect:experienceForm.do?exp_cd=" + exp_cd;
					
					if ("Edu".equals(exp_cd)) {
						word = "����������";
					} // end if
					
					if ("Prj".equals(exp_cd)) {
						word = "������Ʈ��";
					} // end if
					
					redirect.addFlashAttribute("msg", user_id + "���� " + word + " ���������� ��ϵǾ����ϴ�.");
				} // end if
			} // end if
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} // end catch
		
		return moveURL;
	} // addExperience
	
	@RequestMapping(value="/portfolio/experienceModify.do", method=POST)
	public String modifyExperience(HttpServletRequest request, HttpSession session, RedirectAttributes redirect) {
		String user_id = (String) session.getAttribute("user_id");
		String moveURL = "redirect:/error/error.html";
		String exp_cd = "";
		String word = "";
		
		// temp data
		user_id = "young";
		
		try {
			if (user_id != null && !"".equals(user_id)) {
				request.setAttribute("user_id", user_id);
				
				exp_cd = exp_service.modifyExperience(request);
				
				if (!"".equals(exp_cd)) {
					moveURL = "redirect:experienceForm.do?exp_cd=" + exp_cd;
					
					if ("Edu".equals(exp_cd)) {
						word = "����������";
					} // end if
					
					if ("Prj".equals(exp_cd)) {
						word = "������Ʈ��";
					} // end if
					
					redirect.addFlashAttribute("msg", user_id + "���� " + word + " ���������� �����Ǿ����ϴ�.");
				} // end if
			} // end if
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} // end catch
		
		return moveURL;
	} // modifyExperience
	
	@RequestMapping(value="/portfolio/experienceReset.do", method=POST)
	public String resetExperience(HttpServletRequest request, HttpSession session, RedirectAttributes redirect) {
		String user_id = (String) session.getAttribute("user_id");
		String moveURL = "redirect:/error/error.html";
		String exp_cd = "";
		String word = "";
		
		// temp data
		user_id = "young";
		
		try {
			if (user_id != null && !"".equals(user_id)) {
				request.setAttribute("user_id", user_id);
				
				exp_cd = exp_service.resetExperience(request);
				
				if (!"".equals(exp_cd)) {
					moveURL = "redirect:experienceForm.do?exp_cd=" + exp_cd;
					
					if ("Edu".equals(exp_cd)) {
						word = "����������";
					} // end if
					
					if ("Prj".equals(exp_cd)) {
						word = "������Ʈ��";
					} // end if
					
					redirect.addFlashAttribute("msg", user_id + "���� " + word + " ���������� �ʱ�ȭ�Ǿ����ϴ�.");
				} // end if
			} // end if
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} // end catch
		
		return moveURL;
	} // resetExperience
	
	// -------------------- ��Ʈ������ ����ó ���� -------------------- //
	
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
				
				redirect.addFlashAttribute("msg", user_id + "���� ����ó�� ���������� ��ϵǾ����ϴ�.");
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
				
				redirect.addFlashAttribute("msg", user_id + "���� ����ó�� ���������� �����Ǿ����ϴ�.");
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
				
				redirect.addFlashAttribute("msg", user_id + "���� ����ó�� ���������� �ʱ�ȭ�Ǿ����ϴ�.");
			} // end if
		} // end if
		
		return moveURL;
	} // resetTellMe
	
	// -------------------- ��Ʈ������ �Խ��� ���� -------------------- //
	
	@RequestMapping(value="/portfolio/portfolioList.do", method=GET)
	public String movePortfolioList(HttpSession session, Model model) {
		String user_id = (String) session.getAttribute("user_id");
		String moveURL = "redirect:/error/error.html";
		
		// temp data
		user_id = "young";
		
		if (user_id != null && !"".equals(user_id)) {
			List<PortfolioListSearch> plsList = pl_service.searchPortfolioList();
			
			moveURL = "portfolio/portfolioList";
			
			model.addAttribute("plsList", plsList);
		} // end if
		
		return moveURL;
	} // movePortfolioList
	
	@ResponseBody
	@RequestMapping(value="/portfolio/portfolioListCall.do", method=GET)
	public String callPortfolioList() {
		JSONObject json = new JSONObject();
		
		
		
		return json.toJSONString();
	} // callPortfolioList
	
	@ResponseBody
	@RequestMapping(value="/portfolio/portfolioView.do", method=GET, produces="aplication/text; charset=utf-8")
	public String showPortfolioView(@RequestParam(name="target_id", required=false) String target_id, HttpSession session, Model model) {
		String user_id = (String) session.getAttribute("user_id");
		String moveURL = "redirect:/error/error.html";
		
		// temp data
		user_id = "young";
		
		if (user_id != null && !"".equals(user_id)) {
			
		} // end if
		
		System.out.println("----- target_id = " + target_id);
		
		moveURL = "portfolio/portfolioView";
		
		JSONObject json = pl_service.searchPortfolioView(target_id);
		
		System.out.println("---- json ��û");
		
		return json.toJSONString();
	} // showPortfolioView
	
} // class
