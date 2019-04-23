package com.propofol.www.user.portfolio.service;

import com.propofol.www.user.dao.PortfolioDAO;
import com.propofol.www.user.portfolio.domain.ExperienceSearch;
import com.propofol.www.user.portfolio.vo.ExperienceVO;

public class ExperienceService {
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
	public ExperienceSearch searchExperience(String user_id) {
		ExperienceSearch exp_search = null;
		
		// DAO 접근
		
		
		return exp_search;
	} // searchExperience
	
	/**
	 * 관련 경험 추가
	 * @param am_vo
	 * @return
	 */
	public int addExperience(ExperienceVO am_vo) {
		int result = 0;
		
		// DAO 접근
		
		
		return result;
	} // addExperience
	
	/**
	 * 관련 경험 수정
	 * @param am_vo
	 * @return
	 */
	public int modifyExperience(ExperienceVO am_vo) {
		int result = 0;
		
		// DAO 접근
		
		
		return result;
	} // modifyExperience
	
	/**
	 * 관련 경험 초기화 (해당 아이디의 테이블에 존재하는 데이터 삭제)
	 * @param user_id
	 * @return
	 */
	public int resetExperience(String user_id) {
		int result = 0;
		
		// DAO 접근
		
		
		return result;
	} // resetExperience
	
	// -------------------- 관련 경험 종료 -------------------- //
	
} // class
