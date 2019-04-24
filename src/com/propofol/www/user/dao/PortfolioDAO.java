package com.propofol.www.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.propofol.www.user.portfolio.domain.MyPortfolioSearch;

@Component
public class PortfolioDAO {
	
	@Autowired(required=false)
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
		
		result = ss.selectOne("writeState", user_id);
		
		ss.close();
		
		return result;
	} // selectWriteState
	
	public MyPortfolioSearch selectMyPortfolio(String user_id) {
		MyPortfolioSearch mp_search = null;
		
		SqlSession ss = MyBatisDAO.getInstance().getSessionFactory().openSession();
		
		mp_search = ss.selectOne("myPortfolio", user_id);
		
		ss.close();
		
		return mp_search;
	} // selectMyPortfolio
	
	
//	/**
//	 * unit test
//	 * @param args
//	 */
//	public static void main(String[] args) {
////		int result = PortfolioDAO.getInstance().selectWriteState("young");
////		System.out.println(result);
//		
//		MyPortfolioSearch mp_search = PortfolioDAO.getInstance().selectMyPortfolio("young");
//		
//		System.out.println(mp_search.getThumbnail_img() + " / " + mp_search.getTitle());
//		
//	} // main
	
} // class
