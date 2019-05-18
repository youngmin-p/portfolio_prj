package com.propofol.www.user.portfolio.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.propofol.www.user.dao.PortfolioDAO;
import com.propofol.www.user.portfolio.domain.MyPortfolioSearch;
import com.propofol.www.user.portfolio.vo.MyPortfolioVO;

@Component
public class MyPortfolioService {
	
	@Autowired(required=false)
	private PortfolioDAO p_dao;
	
	public MyPortfolioService() {
		p_dao = PortfolioDAO.getInstance();
		
	} // MyPortfolioService
	
	// -------------------- ��Ʈ������ ���� ���� -------------------- //
	
	/**
	 * ���̵� null���� üũ (DB ��ȸ X)
	 * @param user_id
	 * @return
	 */
	public int chkLogin(String user_id) {
		int result = 0;
		
		if (user_id != null && !user_id.isEmpty()) {
			result = 1;
		} // end if
		
		return result;
	} // chkLogin
	
	/**
	 * ��Ʈ������ ��� ���� Ȯ�� (���̵� �����ϴ���)
	 * @param user_id
	 * @return
	 */
	public int chkWriteState(String user_id) {
		int result = 0;
		
		// ��Ʈ������ ���̺� ��ȸ ���
		result = p_dao.selectWriteState(user_id);
		
		return result;
	} // chkWriteState
	
	// -------------------- ��Ʈ������ ���� ���� -------------------- //
	
	/**
	 * �� ��Ʈ������ ��ȸ
	 * @param user_id
	 * @return
	 */
	public MyPortfolioSearch searchMyPortfolio(String user_id) {
		MyPortfolioSearch mp_search = null;
		
		mp_search = p_dao.selectMyPortfolio(user_id);
		
		return mp_search;
	} // searchMyPortfolio
	
	/**
	 * �� ��Ʈ������ ���
	 * @param mp_vo
	 * @return
	 * @throws IOException 
	 */
	public boolean addMyPortfolio(HttpServletRequest request) throws IOException {
		boolean flag = false;
		
		MultipartRequest mr = new MultipartRequest(
				request, "C:/Users/Park/git/propofol_prj/WebContent/upload", 1024 * 1024 * 10, "UTF-8", new DefaultFileRenamePolicy());
		
		MyPortfolioVO mp_vo = new MyPortfolioVO(
				(String) request.getAttribute("user_id"), mr.getFilesystemName("thumbnail_img"), 
				mr.getParameter("title"), mr.getParameter("public_st"));
		
		flag = (p_dao.insertMyPortfolio(mp_vo) == 1);
		
		return flag;
	} // addMyPortfolio
	
	/**
	 * �� ��Ʈ������ ����
	 * @param mp_vo
	 * @return
	 * @throws IOException 
	 */
	public boolean modifyMyPortfolio(HttpServletRequest request) throws IOException {
		boolean flag = false;
		
		MultipartRequest mr = new MultipartRequest(
				request, "C:/Users/Park/git/propofol_prj/WebContent/upload", 1024 * 1024 * 10, "UTF-8", new DefaultFileRenamePolicy());
		
		String prev_img = mr.getParameter("prev_img");
		String thumbnail_img = mr.getFilesystemName("thumbnail_img");
		
		if (thumbnail_img == null || "".equals(thumbnail_img)) {
			thumbnail_img = prev_img;
		} // end if
		
		MyPortfolioVO mp_vo = new MyPortfolioVO(
				(String) request.getAttribute("user_id"), thumbnail_img, 
				mr.getParameter("title"), mr.getParameter("public_st"));
		
		flag = (p_dao.updateMyPortfolio(mp_vo) == 1);
		
		return flag;
	} // modifyMyPortfolio
	
	/**
	 * �� ��Ʈ������ ����
	 * @param user_id
	 * @return
	 */
	public boolean removeMyPortfolio(String user_id) {
		boolean flag = false;
		
		flag = (p_dao.deleteMyPortfolio(user_id) == 1);
		
		return flag;
	} // removeMyPortfolio
	
} // class
