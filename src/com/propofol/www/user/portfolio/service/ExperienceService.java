package com.propofol.www.user.portfolio.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.propofol.www.user.dao.PortfolioDAO;
import com.propofol.www.user.portfolio.domain.ExperienceSearch;
import com.propofol.www.user.portfolio.vo.ExperienceResetVO;
import com.propofol.www.user.portfolio.vo.ExperienceSearchVO;
import com.propofol.www.user.portfolio.vo.ExperienceVO;

@Component
public class ExperienceService {
	
	@Autowired(required=false)
	private PortfolioDAO p_dao;
	
	public ExperienceService() {
		p_dao = PortfolioDAO.getInstance();
		
	} // ExperienceService
	
	// -------------------- ���� ���� ���� -------------------- //
	
	/**
	 * ���� ���� ��ȸ
	 * @param user_id
	 * @return
	 */
	public ExperienceSearch searchExperience(ExperienceSearchVO es_vo) {
		ExperienceSearch exp_search = null;
		
		exp_search = p_dao.selectExperience(es_vo);
		
		return exp_search;
	} // searchExperience
	
	/**
	 * ���� ���� ���
	 * @param am_vo
	 * @return
	 * @throws IOException 
	 */
	public String addExperience(HttpServletRequest request) throws IOException {
		boolean flag = false;
		
		MultipartRequest mr = new MultipartRequest(
				request, "C:/Users/owner/git/propofol_prj/WebContent/upload", 1024 * 1024 * 10, "UTF-8", new DefaultFileRenamePolicy());
		
		ExperienceVO exp_vo = new ExperienceVO(
				(String) request.getAttribute("user_id"), mr.getParameter("exp_cd"), mr.getParameter("title"), 
				mr.getParameter("contents"), mr.getFilesystemName("upload_img"));
		
		flag = (p_dao.insertExperience(exp_vo) == 1);
		
		String exp_cd = "";
		
		if (flag) {
			exp_cd = mr.getParameter("exp_cd");
		} // end if
		
		return exp_cd;
	} // addExperience
	
	/**
	 * ���� ���� ����
	 * @param am_vo
	 * @return
	 * @throws IOException 
	 */
	public String modifyExperience(HttpServletRequest request) throws IOException {
		boolean flag = false;
		
		MultipartRequest mr = new MultipartRequest(
				request, "C:/Users/owner/git/propofol_prj/WebContent/upload", 1024 * 1024 * 10, "UTF-8", new DefaultFileRenamePolicy());
		
		ExperienceVO exp_vo = new ExperienceVO(
				(String) request.getAttribute("user_id"), mr.getParameter("exp_cd"), mr.getParameter("title"), 
				mr.getParameter("contents"), mr.getFilesystemName("upload_img"));
		
		System.out.println("----- exp_vo ��ü �� " + exp_vo.toString());
		
		flag = (p_dao.updateExperience(exp_vo) == 1);
		
		String exp_cd = "";
		
		if (flag) {
			exp_cd = mr.getParameter("exp_cd");
		} // end if
		
		return exp_cd;
	} // modifyExperience
	
	/**
	 * ���� ���� �ʱ�ȭ (�ش� ���̵��� ���̺� �����ϴ� ������ ����)
	 * @param user_id
	 * @return
	 * @throws IOException 
	 */
	public String resetExperience(HttpServletRequest request) throws IOException {
		boolean flag = false;

		MultipartRequest mr = new MultipartRequest(
				request, "C:/Users/owner/git/propofol_prj/WebContent/upload", 1024 * 1024 * 10, "UTF-8", new DefaultFileRenamePolicy());
		
		ExperienceResetVO expr_vo = new ExperienceResetVO(
				(String) request.getAttribute("user_id"), mr.getParameter("exp_cd"));
		
		flag = (p_dao.resetExperience(expr_vo) == 1);
		
		String exp_cd = "";
		
		if (flag) {
			exp_cd = mr.getParameter("exp_cd");
		} // end if
		
		return exp_cd;
	} // resetExperience
	
	// -------------------- ���� ���� ���� -------------------- //
	
} // class
