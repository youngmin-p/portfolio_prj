package com.propofol.www.user.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.propofol.www.user.dao.PortfolioDAO;
import com.propofol.www.user.portfolio.domain.TellMeSearch;
import com.propofol.www.user.portfolio.vo.TellMeVO;

@Component
public class TellMeService {
	
	@Autowired(required=false)
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
		
		tm_search = p_dao.selectTellMe(user_id);
		
		return tm_search;
	} // searchTellMe
	
	/**
	 * 연락처 추가
	 * @param am_vo
	 * @return
	 */
	public boolean addTellMe(TellMeVO tm_vo) {
		boolean flag = false;
		
		flag = (p_dao.insertTellMe(tm_vo) == 1);
		
		return flag;
	} // addTellMe
	
	/**
	 * 연락처 수정
	 * @param am_vo
	 * @return
	 */
	public boolean modifyTellMe(TellMeVO tm_vo) {
		boolean flag = false;
		
		flag = (p_dao.updateTellMe(tm_vo) == 1);
		
		return flag;
	} // modifyTellMe
	
	/**
	 * 연락처 초기화 (해당 아이디의 테이블에 존재하는 데이터 삭제)
	 * @param user_id
	 * @return
	 */
	public boolean resetTellMe(String user_id) {
		boolean flag = false;
		
		flag = (p_dao.resetTellMe(user_id) == 1);
		
		return flag;
	} // resetTellMe
	
	// -------------------- 연락처 종료 -------------------- //
	
} // class
