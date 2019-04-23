package com.propofol.www.dao;

import org.apache.ibatis.session.SqlSession;

public class PortfolioDAO {
	private static PortfolioDAO p_dao;
	
	private PortfolioDAO() { } // PortfolioDAO
	
	public static PortfolioDAO getInstance() {
		if (p_dao == null) {
			p_dao = new PortfolioDAO();
		} // end if
		
		return p_dao;
	} // getInstance
	
	/**
	 * 포트폴리오 등록 여부 조회
	 * @param user_id
	 * @return
	 */
	public int selectWriteState(String user_id) {
		int result = 0;
		
		SqlSession ss = MyBatisDAO.getInstance().getSessionFactory().openSession();
		
		result = ss.selectOne("loginCheck", user_id);
		
		ss.close();
		
		return result;
	} // selectWriteState
	
	
	
	
	/**
	 * unit test
	 * @param args
	 */
	public static void main(String[] args) {
		PortfolioDAO pf_dao = new PortfolioDAO();
		
	} // main
	
} // class
