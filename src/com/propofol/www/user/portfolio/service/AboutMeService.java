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
	
	// -------------------- �ڱ�Ұ� ���� -------------------- //
	
	/**
	 * �ڱ�Ұ� ��ȸ
	 * @param user_id
	 * @return
	 */
	public AboutMeSearch searchAboutMe(String user_id) {
		AboutMeSearch am_search = null;
		
		am_search = p_dao.selectAboutMe(user_id);
		
		return am_search;
	} // searchAboutMe
	
	/**
	 * �ڱ�Ұ� ���
	 * @param am_vo
	 * @return
	 * @throws IOException 
	 */
	public boolean addAboutMe(HttpServletRequest request) throws IOException {
		boolean flag = false;
		
		MultipartRequest mr = new MultipartRequest(
				request, "C:/Users/Park/git/propofol_prj/WebContent/upload", 1024 * 1024 * 10, "UTF-8", new DefaultFileRenamePolicy());
		
		AboutMeVO am_vo = new AboutMeVO(
				(String) request.getAttribute("user_id"), mr.getParameter("title"), 
				mr.getParameter("contents"), mr.getFilesystemName("upload_img"));
		
		flag = (p_dao.insertAboutMe(am_vo) == 1);
		
		return flag;
	} // addAboutMe
	
	/**
	 * �ڱ�Ұ� ���� (����)
	 * @param am_vo
	 * @return
	 * @throws IOException 
	 */
	public boolean modifyAboutMe(HttpServletRequest request) throws IOException {
		boolean flag = false;
		
		MultipartRequest mr = new MultipartRequest
				(request, "C:/Users/Park/git/propofol_prj/WebContent/upload", 1024 * 1024 * 10, "UTF-8", new DefaultFileRenamePolicy());
		
		String prev_img = mr.getParameter("prev_img");
		String upload_img = mr.getFilesystemName("upload_img");
		
		if (upload_img == null || "".equals(upload_img)) {
			upload_img = prev_img;
		} // end if
		
		AboutMeVO am_vo = new AboutMeVO(
				(String) request.getAttribute("user_id"), mr.getParameter("title"), 
				mr.getParameter("contents"), upload_img);
		
		flag = (p_dao.updateAboutMe(am_vo) == 1);
		
		return flag;
	} // modifyAboutMe
	
	/**
	 * �ڱ�Ұ� �ʱ�ȭ (���̺� �����ϴ� �ش� ���̵��� ������ ����)
	 * @param user_id
	 * @return
	 */
	public boolean resetAboutMe(String user_id) {
		boolean flag = false;
		
		flag = (p_dao.resetAboutMe(user_id) == 1);
		
		return flag;
	} // resetAboutMe
	
	// -------------------- �ڱ�Ұ� ���� -------------------- //
	
} // class
