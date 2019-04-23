package com.propofol.www.user.portfolio.service;

import com.propofol.www.user.dao.PortfolioDAO;
import com.propofol.www.user.portfolio.domain.TechStacksSearch;
import com.propofol.www.user.portfolio.vo.TechStacksVO;

public class TechStacksService {
	private PortfolioDAO p_dao;
	
	public TechStacksService() {
		p_dao = PortfolioDAO.getInstance();
		
	} // TechStacksService
	
	// -------------------- 기술 스택 시작 -------------------- //
	
	/**
	 * 기술 스택 조회
	 * @param user_id
	 * @return
	 */
	public TechStacksSearch searchTechStacks(String user_id) {
		TechStacksSearch ts_search = null;
		
		// DAO 접근
		
		
		return ts_search;
	} // searchTechStacks
	
	/**
	 * 기술 스택 추가
	 * @param am_vo
	 * @return
	 */
	public int addTechStacks(TechStacksVO am_vo) {
		int result = 0;
		
		// DAO 접근
		
		
		return result;
	} // addTechStacks
	
	/**
	 * 기술 스택 수정
	 * @param am_vo
	 * @return
	 */
	public int modifyTechStacks(TechStacksVO am_vo) {
		int result = 0;
		
		// DAO 접근
		
		
		return result;
	} // modifyTechStacks
	
	/**
	 * 기술 스택 초기화 (해당 아이디의 테이블에 존재하는 데이터 삭제)
	 * @param user_id
	 * @return
	 */
	public int resetTechStacks(String user_id) {
		int result = 0;
		
		// DAO 접근
		
		
		return result;
	} // resetTechStacks
	
	// -------------------- 기술 스택 종료 -------------------- //
	
} // class
