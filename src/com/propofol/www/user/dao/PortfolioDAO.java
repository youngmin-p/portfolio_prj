package com.propofol.www.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.propofol.www.user.portfolio.domain.MyPortfolioSearch;
import com.propofol.www.user.portfolio.vo.MyPortfolioVO;

@Component
public class PortfolioDAO {
	
	@Autowired(required=false)
	private MyBatisDAO mb_dao;
	
	private static PortfolioDAO p_dao;
	
	private PortfolioDAO() {
		mb_dao = MyBatisDAO.getInstance();
		
	} // PortfolioDAO
	
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
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		result = ss.selectOne("writeState", user_id);
		
		ss.close();
		
		return result;
	} // selectWriteState
	
	/**
	 * 내 포트폴리오 조회
	 * @param user_id
	 * @return
	 */
	public MyPortfolioSearch selectMyPortfolio(String user_id) {
		MyPortfolioSearch mp_search = null;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		mp_search = ss.selectOne("myPortfolio", user_id);
		
		ss.close();
		
		return mp_search;
	} // selectMyPortfolio
	
	/**
	 * 내 포트폴리오 등록
	 * @param mp_vo
	 * @return
	 */
	public int insertMyPortfolio(MyPortfolioVO mp_vo) {
		int result = 0;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		result = ss.insert("insertMyPortfolio", mp_vo);
		
		ss.commit();
		
		ss.close();
		
		return result;
	} // insertMyPortfolio
	
	/**
	 * 내 포트폴리오 수정
	 * @param mp_vo
	 * @return
	 */
	public int updateMyPortfolio(MyPortfolioVO mp_vo) {
		int result = 0;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		result = ss.update("updateMyPortfolio", mp_vo);
		
		ss.commit();
		
		ss.close();
		
		return result;
	} // updateMyPortfolio
	
	/**
	 * 내 포트폴리오 삭제
	 * @param user_id
	 * @return
	 */
	public int deleteMyPortfolio(String user_id) {
		int result = 0;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		result = ss.delete("deleteMyPortfolio", user_id);
		
		ss.commit();
		
		ss.close();
		
		return result;
	} // deleteMyPortfolio
	
//	/**
//	 * unit test
//	 * @param args
//	 */
//	public static void main(String[] args) {
////		int result = PortfolioDAO.getInstance().selectWriteState("young");
////		System.out.println(result);
//		
////		MyPortfolioSearch mp_search = PortfolioDAO.getInstance().selectMyPortfolio("young");
////		System.out.println(mp_search.getThumbnail_img() + " / " + mp_search.getTitle() + " / " + mp_search.getPermit_st());
//		
////		int result;
////		MyPortfolioVO mp_vo = new MyPortfolioVO("young", "hello.png", "수정 완료!", "Y");
////		PortfolioDAO p_dao = new PortfolioDAO();
////		result = p_dao.updateMyPortfolio(mp_vo);
////		result = PortfolioDAO.getInstance().updateMyPortfolio(mp_vo);
////		System.out.println("---- update result = " + result);
//		
////		PortfolioDAO p_dao = new PortfolioDAO();
////		MyPortfolioVO mp_vo = new MyPortfolioVO("jung", "king.png", "나는 김정윤이야.", "N");
////		int result = p_dao.insertMyPortfolio(mp_vo);
////		System.out.println("결과 성공 시 1 반환 : " + result);
//		
////		PortfolioDAO p_dao = new PortfolioDAO();
////		String user_id = "jung";
////		int result = p_dao.deleteMyPortfolio(user_id);
////		System.out.println("결과 성공 시 1 반환 : " + result);
//		
//	} // main
	
} // class
