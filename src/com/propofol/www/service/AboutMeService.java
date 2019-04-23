package com.propofol.www.service;

import com.propofol.www.dao.PortfolioDAO;
import com.propofol.www.domain.AboutMeSearch;
import com.propofol.www.vo.AboutMeVO;

public class AboutMeService {
	private PortfolioDAO p_dao;
	
	public AboutMeService() {
		p_dao = PortfolioDAO.getInstance();
		
	} // AboutMeService
	
	// -------------------- 자기소개 시작 -------------------- //
	
	/**
	 * 자기소개 조회
	 * @param user_id
	 * @return
	 */
	public AboutMeSearch searchAboutMe(String user_id) {
		AboutMeSearch am_search = null;
		
		// DAO 접근
		
		
		return am_search;
	} // searchAboutMe
	
	/**
	 * 자기소개 추가
	 * @param am_vo
	 * @return
	 */
	public int addAboutMe(AboutMeVO am_vo) {
		int result = 0;
		
		// DAO 접근
		
		
		return result;
	} // addAboutMe
	
	/**
	 * 자기소개 수정
	 * @param am_vo
	 * @return
	 */
	public int modifyAboutMe(AboutMeVO am_vo) {
		int result = 0;
		
		// DAO 접근
		
		
		return result;
	} // modifyAboutMe
	
	/**
	 * 자기소개 초기화 (해당 아이디의 테이블에 존재하는 데이터 삭제)
	 * @param user_id
	 * @return
	 */
	public int resetAboutMe(String user_id) {
		int result = 0;
		
		// DAO 접근
		
		
		return result;
	} // resetAboutMe
	
	// -------------------- 자기소개 종료 -------------------- //
	
} // class
