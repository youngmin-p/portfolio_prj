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
	
	// -------------------- ��� ���� ���� -------------------- //
	
	/**
	 * ��� ���� ��ȸ
	 * @param user_id
	 * @return
	 */
	public TechStacksSearch searchTechStacks(String user_id) {
		TechStacksSearch ts_search = null;
		
		ts_search = p_dao.selectTechStacks(user_id);
		
		return ts_search;
	} // searchTechStacks
	
	/**
	 * ��� ���� ���
	 * @param am_vo
	 * @return
	 */
	public boolean addTechStacks(TechStacksVO ts_vo) {
		boolean flag = false;
		
		flag = (p_dao.insertTechStacks(ts_vo) == 1);
		
		return flag;
	} // addTechStacks
	
	/**
	 * ��� ���� ����
	 * @param am_vo
	 * @return
	 */
	public boolean modifyTechStacks(TechStacksVO ts_vo) {
		boolean flag = false;
		
		flag = (p_dao.updateTechStacks(ts_vo) == 1);
		
		return flag;
	} // modifyTechStacks
	
	/**
	 * ��� ���� �ʱ�ȭ (�ش� ���̵��� ���̺� �����ϴ� ������ ����)
	 * @param user_id
	 * @return
	 */
	public boolean resetTechStacks(String user_id) {
		boolean flag = false;
		
		flag = (p_dao.resetTechStacks(user_id) == 1);
		
		return flag;
	} // resetTechStacks
	
	// -------------------- ��� ���� ���� -------------------- //
	
} // class
