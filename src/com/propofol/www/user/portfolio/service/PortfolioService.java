package com.propofol.www.user.portfolio.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.propofol.www.user.dao.PortfolioDAO;
import com.propofol.www.user.portfolio.domain.AboutMeSearch;
import com.propofol.www.user.portfolio.domain.PortfolioListSearch;
import com.propofol.www.user.portfolio.domain.PortfolioViewSearch;
import com.propofol.www.user.portfolio.vo.PortfolioListIdx;

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
		
		List<PortfolioListSearch> plsList = p_dao.selectPortfolioList();
		
		return plsList;
	} // searchPortfolioList
	
	public JSONObject searchPortfolioListCall(int idx) {
		JSONObject json_obj = new JSONObject();
		
		PortfolioListIdx plIdx = new PortfolioListIdx(idx + 1, idx + 5);
		
		List<PortfolioListSearch> plsList = p_dao.selectPortfolioListCall(plIdx);
		
		JSONArray json_arr = new JSONArray();
		
		JSONObject json_temp = null;
		
		for (PortfolioListSearch pls : plsList) {
			json_temp = new JSONObject();
			
			json_temp.put("thumbnail_img", pls.getThumbnail_img());
			json_temp.put("title", pls.getTitle());
			json_temp.put("write_dt", pls.getWrite_dt());
			json_temp.put("user_id", pls.getUser_id());
			json_temp.put("hits", pls.getHits());
			json_temp.put("num", pls.getNum());
			
			json_arr.add(json_temp);
		} // end for
		
		json_obj.put("plsList", json_arr);
		
		return json_obj;
	} // searchPortfolioListCall
	
	public JSONObject searchPortfolioView(String target_id) {
		JSONObject json_obj = new JSONObject();
		
		PortfolioViewSearch pv_search = p_dao.selectPortfolioView(target_id);
		
		// 값을 하나씩 받을 것인지? 여러 개의 VO로 받을 것인지?
		JSONArray json_arr = new JSONArray();
		
		JSONObject json_temp = null;
		
		// 객체가 null인지 확인하고, 값을 따로 담아야 할 듯?
		// 값을 분배해서 JSONArray 형태로 파싱해야 할 거 같은데...
		if (pv_search.getAm_search() != null) {
			System.out.println(pv_search.getAm_search().toString());
		}
		

		System.out.println(pv_search.getTs_search().toString());
		System.out.println(pv_search.getExpList().toString());
		
		json_obj.put("pv_search", pv_search);
		
		return json_obj;
	} // searchPortfolioView
	
	// -------------------- 포트폴리오 게시판 종료 -------------------- //
	
	public static void main(String[] args) {
		PortfolioService p_service = new PortfolioService();
		
//		int idx = 1;
//		p_service.searchPortfolioListCall(idx);
		
		String target_id = "young";
		
		JSONObject json_obj = p_service.searchPortfolioView(target_id);
		
	}
	
} // class
