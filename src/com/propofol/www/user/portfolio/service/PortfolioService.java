package com.propofol.www.user.portfolio.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.propofol.www.user.dao.PortfolioDAO;
import com.propofol.www.user.portfolio.domain.AboutMeSearch;
import com.propofol.www.user.portfolio.domain.ExperienceSearch;
import com.propofol.www.user.portfolio.domain.PortfolioListSearch;
import com.propofol.www.user.portfolio.domain.PortfolioViewTitleSearch;
import com.propofol.www.user.portfolio.domain.TechStacksSearch;
import com.propofol.www.user.portfolio.domain.TellMeSearch;
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
	
	/**
	 * 포트폴리오 게시물 조회 (10건, ajax call)
	 * @param idx
	 * @return
	 */
	public JSONObject searchPortfolioListCall(int idx) {
		JSONObject json_obj = new JSONObject();
		
		PortfolioListIdx plIdx = new PortfolioListIdx(idx + 1, idx + 10);
		
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
	
	/**
	 * 포트폴리오 게시글 타이틀 조회
	 * @param target_id
	 * @return
	 */
	public PortfolioViewTitleSearch searchPortfolioViewTitle(String target_id) {
		PortfolioViewTitleSearch pvt_search = p_dao.selectPortfolioViewTitle(target_id);
		
		return pvt_search;
	} // searchPortfolioViewTitle
	
	/**
	 * 포트폴리오 게시글 조회
	 * @param target_id
	 * @return
	 */
	public JSONObject searchPortfolioView(String target_id) {
		JSONObject json_obj = new JSONObject();
		
		PortfolioViewTitleSearch pvt_search = p_dao.selectPortfolioViewTitle(target_id);
		
		if (pvt_search != null) {
			json_obj.put("title", pvt_search.getTitle());
			json_obj.put("user_id", pvt_search.getUser_id());
			json_obj.put("write_dt", pvt_search.getWrite_dt());			
		} // end if
		
		AboutMeSearch am_search = p_dao.selectAboutMe(target_id);
		
		boolean amFlag = false;
		
		if (am_search != null) {
			amFlag = true;
			
			json_obj.put("am_title", am_search.getTitle());
			json_obj.put("am_contents", am_search.getContents());
			json_obj.put("am_upload_img", am_search.getUpload_img());
		} // end if
		
		json_obj.put("am_result", amFlag);
		
		TechStacksSearch ts_search = p_dao.selectTechStacks(target_id);
		
		boolean tsFlag = false;
		
		if (ts_search != null) {
			tsFlag = true;
			
			JSONArray json_arr = new JSONArray();
			
			JSONObject json_temp = null;
			
			for (String technique : ts_search.getSelected_technique()) {
				json_temp = new JSONObject();
				
				json_temp.put("technique", technique);
				
				json_arr.add(json_temp);
			} // end for
			
			json_obj.put("ts_selected_technique", json_arr);
		} // end if
		
		json_obj.put("ts_result", tsFlag);
		
		TellMeSearch tm_search = p_dao.selectTellMe(target_id);
		
		boolean tmFlag = false;
		
		if (tm_search != null) {
			tmFlag = true;
			
			json_obj.put("tm_phone_no", tm_search.getPhone_no());
			json_obj.put("tm_email", tm_search.getEmail());
			json_obj.put("tm_domain", tm_search.getDomain());
			json_obj.put("tm_blog", tm_search.getBlog());
		} // end if
		
		json_obj.put("tm_result", tmFlag);
		
		List<ExperienceSearch> expList = p_dao.selectExperience(target_id);
		
		boolean eduFlag = false;
		boolean prjFlag = false;
		
		if (expList != null) {
			JSONArray json_arr = new JSONArray();
			
			JSONObject json_temp = null;
			
			for (ExperienceSearch exp_search : expList) {
				if ("Edu".equals(exp_search.getExp_cd())) {
					eduFlag = true;
				} // end if
				
				if ("Prj".equals(exp_search.getExp_cd())) {
					prjFlag = true;
				} // end if
				
				json_temp = new JSONObject();
				
				json_temp.put("exp_cd", exp_search.getExp_cd());
				json_temp.put("exp_title", exp_search.getTitle());
				json_temp.put("exp_contents", exp_search.getContents());
				json_temp.put("exp_upload_img", exp_search.getUpload_img());
				
				json_arr.add(json_temp);
			} // end for
			
			json_obj.put("expList", json_arr);
		} // end if
		
		json_obj.put("edu_result", eduFlag);
		json_obj.put("prj_result", prjFlag);
		
		return json_obj;
	} // searchPortfolioView
	
	// -------------------- 포트폴리오 게시판 종료 -------------------- //
	
} // class
