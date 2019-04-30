package com.propofol.www.user.portfolio.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.propofol.www.user.dao.PortfolioDAO;
import com.propofol.www.user.portfolio.domain.AboutMeSearch;
import com.propofol.www.user.portfolio.vo.AboutMeVO;

@Component
public class AboutMeService {
	
	@Autowired(required=false)
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
		
		am_search = p_dao.selectAboutMe(user_id);
		
		return am_search;
	} // searchAboutMe
	
	/**
	 * 자기소개 등록
	 * @param am_vo
	 * @return
	 * @throws IOException 
	 */
	public boolean addAboutMe(HttpServletRequest request) throws IOException {
		boolean flag = false;
		
		MultipartRequest mr = new MultipartRequest(
				request, "C:/Users/owner/git/propofol_prj/WebContent/upload", 1024 * 1024 * 10, "UTF-8", new DefaultFileRenamePolicy());
		
		AboutMeVO am_vo = new AboutMeVO(
				(String) request.getAttribute("user_id"), mr.getParameter("title"), 
				mr.getParameter("contents"), mr.getFilesystemName("upload_img"));
		
		flag = (p_dao.insertAboutMe(am_vo) == 1);
		
		return flag;
	} // addAboutMe
	
	/**
	 * 자기소개 적용 (수정)
	 * @param am_vo
	 * @return
	 * @throws IOException 
	 */
	public boolean modifyAboutMe(HttpServletRequest request) throws IOException {
		boolean flag = false;
		
		MultipartRequest mr = new MultipartRequest
				(request, "C:/Users/owner/git/propofol_prj/WebContent/upload", 1024 * 1024 * 10, "UTF-8", new DefaultFileRenamePolicy());
		
		AboutMeVO am_vo = new AboutMeVO(
				(String) request.getAttribute("user_id"), mr.getParameter("title"), 
				mr.getParameter("contents"), mr.getFilesystemName("upload_img"));
		
		flag = (p_dao.updateAboutMe(am_vo) == 1);
		
		return flag;
	} // modifyAboutMe
	
	/**
	 * 자기소개 초기화 (테이블에 존재하는 해당 아이디의 데이터 삭제)
	 * @param user_id
	 * @return
	 */
	public boolean resetAboutMe(String user_id) {
		boolean flag = false;
		
		flag = (p_dao.resetAboutMe(user_id) == 1);
		
		return flag;
	} // resetAboutMe
	
	// -------------------- 자기소개 종료 -------------------- //
	
} // class
