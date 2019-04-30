package com.propofol.www.user.portfolio.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.propofol.www.user.dao.PortfolioDAO;
import com.propofol.www.user.portfolio.domain.ExperienceSearch;
import com.propofol.www.user.portfolio.vo.ExperienceSearchVO;
import com.propofol.www.user.portfolio.vo.ExperienceVO;

@Component
public class ExperienceService {
	
	@Autowired(required=false)
	private PortfolioDAO p_dao;
	
	public ExperienceService() {
		p_dao = PortfolioDAO.getInstance();
		
	} // ExperienceService
	
	// -------------------- 관련 경험 시작 -------------------- //
	
	/**
	 * 관련 경험 조회
	 * @param user_id
	 * @return
	 */
	public ExperienceSearch searchExperience(ExperienceSearchVO es_vo) {
		ExperienceSearch exp_search = null;
		
		exp_search = p_dao.selectExperience(es_vo);
		
		return exp_search;
	} // searchExperience
	
	/**
	 * 관련 경험 등록
	 * @param am_vo
	 * @return
	 * @throws IOException 
	 */
	public boolean addExperience(HttpServletRequest request) throws IOException {
		boolean flag = false;
		
		MultipartRequest mr = new MultipartRequest(
				request, "C:/Users/owner/git/propofol_prj/WebContent/upload", 1024 * 1024 * 10, "UTF-8", new DefaultFileRenamePolicy());
		
		ExperienceVO exp_vo = new ExperienceVO(
				(String) request.getAttribute("user_id"), mr.getParameter("exp_cd"), mr.getParameter("title"), 
				mr.getParameter("contents"), mr.getFilesystemName("upload_img"));
		
		flag = (p_dao.insertExperience(exp_vo) == 1);
		
		return flag;
	} // addExperience
	
	/**
	 * 관련 경험 수정
	 * @param am_vo
	 * @return
	 * @throws IOException 
	 */
	public boolean modifyExperience(HttpServletRequest request) throws IOException {
		boolean flag = false;
		
		MultipartRequest mr = new MultipartRequest(
				request, "C:/Users/owner/git/propofol_prj/WebContent/upload", 1024 * 1024 * 10, "UTF-8", new DefaultFileRenamePolicy());
		
		ExperienceVO exp_vo = new ExperienceVO(
				(String) request.getAttribute("user_id"), mr.getParameter("exp_cd"), mr.getParameter("title"), 
				mr.getParameter("contents"), mr.getFilesystemName("upload_img"));
		
		flag = (p_dao.updateExperience(exp_vo) == 1);
		
		return flag;
	} // modifyExperience
	
	/**
	 * 관련 경험 초기화 (해당 아이디의 테이블에 존재하는 데이터 삭제)
	 * @param user_id
	 * @return
	 */
	public boolean resetExperience(String user_id) {
		boolean flag = false;
		
		flag = (p_dao.resetExperience(user_id) == 1);
		
		return flag;
	} // resetExperience
	
	// -------------------- 관련 경험 종료 -------------------- //
	
} // class
