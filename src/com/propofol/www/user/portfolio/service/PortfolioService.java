package com.propofol.www.user.portfolio.service;

import java.util.ArrayList;
import java.util.List;

import com.propofol.www.user.dao.PortfolioDAO;
import com.propofol.www.user.portfolio.domain.PortfolioListSearch;
import com.propofol.www.user.portfolio.domain.PortfolioViewSearch;

public class PortfolioService {
	private PortfolioDAO p_dao;
	
	public PortfolioService() {
		p_dao = PortfolioDAO.getInstance();
		
	} // PortfolioService
	
	// -------------------- 포트폴리오 게시판 시작 -------------------- //
	
	/**
	 * 포트폴리오 게시판 조회 (비동기식)
	 * @param user_id
	 * @return
	 */
	public List<PortfolioListSearch> searchPortfolioList(int index) {
		List<PortfolioListSearch> list = new ArrayList<PortfolioListSearch>();
		
		// DAO 접근
		// ajax로 받아오려면 json이 필요할 거 같은데?
		
		
		return list;
	} // searchPortfolioList
	
	public PortfolioViewSearch searchPortfolioView(String user_id) {
		PortfolioViewSearch pv_search = null;
		
		// DAO 접근
		
		
		return pv_search;
	} // searchPortfolioView
	
	// -------------------- 포트폴리오 게시판 종료 -------------------- //
	
} // class
