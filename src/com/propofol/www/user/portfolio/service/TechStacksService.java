package com.propofol.www.user.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.propofol.www.user.dao.PortfolioDAO;
import com.propofol.www.user.portfolio.domain.TechStacksSearch;
import com.propofol.www.user.portfolio.vo.TechStacksVO;

@Component
public class TechStacksService {
	
	@Autowired(required=false)
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
		
		ts_search = p_dao.selectTechStacks(user_id);
		
		return ts_search;
	} // searchTechStacks
	
	/**
	 * 기술 스택 등록
	 * @param am_vo
	 * @return
	 */
	public boolean addTechStacks(TechStacksVO ts_vo) {
		boolean flag = false;
		
		flag = (p_dao.insertTechStacks(ts_vo) == 1);
		
		return flag;
	} // addTechStacks
	
	/**
	 * 기술 스택 수정
	 * @param am_vo
	 * @return
	 */
	public boolean modifyTechStacks(TechStacksVO ts_vo) {
		boolean flag = false;
		
		flag = (p_dao.updateTechStacks(ts_vo) == 1);
		
		return flag;
	} // modifyTechStacks
	
	/**
	 * 기술 스택 초기화 (해당 아이디의 테이블에 존재하는 데이터 삭제)
	 * @param user_id
	 * @return
	 */
	public boolean resetTechStacks(String user_id) {
		boolean flag = false;
		
		flag = (p_dao.resetTechStacks(user_id) == 1);
		
		return flag;
	} // resetTechStacks
	
	// -------------------- 기술 스택 종료 -------------------- //
	
} // class
