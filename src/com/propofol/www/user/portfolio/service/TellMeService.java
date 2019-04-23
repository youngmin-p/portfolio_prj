package com.propofol.www.user.portfolio.service;

import com.propofol.www.user.dao.PortfolioDAO;
import com.propofol.www.user.portfolio.domain.TellMeSearch;
import com.propofol.www.user.portfolio.vo.TellMeVO;

public class TellMeService {
	private PortfolioDAO p_dao;
	
	public TellMeService() {
		p_dao = PortfolioDAO.getInstance();
		
	} // TellMeService
	
	// -------------------- 연락처 시작 -------------------- //
	
	/**
	 * 연락처 조회
	 * @param user_id
	 * @return
	 */
	public TellMeSearch searchTellMe(String user_id) {
		TellMeSearch tm_search = null;
		
		// DAO 접근
		
		
		return tm_search;
	} // searchTellMe
	
	/**
	 * 연락처 추가
	 * @param am_vo
	 * @return
	 */
	public int addTellMe(TellMeVO am_vo) {
		int result = 0;
		
		// DAO 접근
		
		
		return result;
	} // addTellMe
	
	/**
	 * 연락처 수정
	 * @param am_vo
	 * @return
	 */
	public int modifyTellMe(TellMeVO am_vo) {
		int result = 0;
		
		// DAO 접근
		
		
		return result;
	} // modifyTellMe
	
	/**
	 * 연락처 초기화 (해당 아이디의 테이블에 존재하는 데이터 삭제)
	 * @param user_id
	 * @return
	 */
	public int resetTellMe(String user_id) {
		int result = 0;
		
		// DAO 접근
		
		
		return result;
	} // resetTellMe
	
	// -------------------- 연락처 종료 -------------------- //
	
} // class
