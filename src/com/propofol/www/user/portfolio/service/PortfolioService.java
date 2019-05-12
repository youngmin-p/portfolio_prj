package com.propofol.www.user.portfolio.service;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.propofol.www.user.dao.PortfolioDAO;
import com.propofol.www.user.portfolio.domain.PortfolioListSearch;
import com.propofol.www.user.portfolio.domain.PortfolioViewSearch;

@Component
public class PortfolioService {
	
	@Autowired(required=false)
	private PortfolioDAO p_dao;
	
	public PortfolioService() {
		p_dao = PortfolioDAO.getInstance();
		
	} // PortfolioService
	
	// -------------------- 포트폴리오 게시판 시작 -------------------- //
	
	/**
	 * 포트폴리오 게시글 수
	 * @return
	 */
	public int portfolioTotalCount() {
		int cnt = 0;
		
		cnt = p_dao.selectPortfolioTotalCount();
		
		return cnt;
	} // portfolioTotalCount
	
	/**
	 * 포트폴리오 게시판 조회 (최초 20건)
	 * @return
	 */
	public List<PortfolioListSearch> searchPortfolioList() {
		int cnt = portfolioTotalCount();
		
		if (cnt < 20) {
			
		} // end if
		
		List<PortfolioListSearch> plsList = p_dao.selectPortfolioList();
		
		return plsList;
	} // searchPortfolioList
	
	public JSONObject searchPortfolioView(String target_id) {
		JSONObject json = new JSONObject();
		
		PortfolioViewSearch pv_search = p_dao.selectPortfolioView(target_id);
		
		System.out.println(pv_search.toString());
		
		json.put("pv_search", pv_search);
		
		return json;
	} // searchPortfolioView
	
	// -------------------- 포트폴리오 게시판 종료 -------------------- //
	
} // class
