package com.propofol.www.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.propofol.www.user.portfolio.domain.AboutMeSearch;
import com.propofol.www.user.portfolio.domain.ExperienceSearch;
import com.propofol.www.user.portfolio.domain.MyPortfolioSearch;
import com.propofol.www.user.portfolio.domain.TechStacksSearch;
import com.propofol.www.user.portfolio.domain.TellMeSearch;
import com.propofol.www.user.portfolio.vo.AboutMeVO;
import com.propofol.www.user.portfolio.vo.ExperienceSearchVO;
import com.propofol.www.user.portfolio.vo.ExperienceVO;
import com.propofol.www.user.portfolio.vo.MyPortfolioVO;
import com.propofol.www.user.portfolio.vo.TechStacksVO;
import com.propofol.www.user.portfolio.vo.TellMeVO;

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
	 * ��Ʈ������ ��� ���� ��ȸ
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
	
	// -------------------- �� ��Ʈ������ ���� -------------------- //
	
	/**
	 * �� ��Ʈ������ ��ȸ
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
	 * �� ��Ʈ������ ���
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
	 * �� ��Ʈ������ ����
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
	 * �� ��Ʈ������ ����
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
	
	// -------------------- ��Ʈ������ �ڱ�Ұ� -------------------- //
	
	/**
	 * �ڱ�Ұ� ��ȸ
	 * @param user_id
	 * @return
	 */
	public AboutMeSearch selectAboutMe(String user_id) {
		AboutMeSearch am_search = null;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		am_search = ss.selectOne("selectAboutMe", user_id);
		
		ss.close();
		
		return am_search;
	} // selectAboutMe
	
	/**
	 * �ڱ�Ұ� ��� (���� 1ȸ)
	 * @param user_id
	 * @return
	 */
	public int insertAboutMe(AboutMeVO am_vo) {
		int result = 0;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		result = ss.insert("insertAboutMe", am_vo);
		
		ss.commit();
		
		ss.close();
		
		return result;
	} // insertAboutMe
	
	/**
	 * �ڱ�Ұ� ���� (����)
	 * @param am_vo
	 * @return
	 */
	public int updateAboutMe(AboutMeVO am_vo) {
		int result = 0;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		result = ss.update("updateAboutMe", am_vo);
		
		ss.commit();
		
		ss.close();
		
		return result;
	} // updateAboutMe
	
	/**
	 * �ڱ�Ұ� �ʱ�ȭ
	 * @param user_id
	 * @return
	 */
	public int resetAboutMe(String user_id) {
		int result = 0;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		result = ss.delete("resetAboutMe", user_id);
		
		ss.commit();
		
		ss.close();
		
		return result;
	} // resetAboutMe
	
	// -------------------- ��Ʈ������ ��� ���� -------------------- //
	
	/**
	 * ��� ���� ��ȸ
	 * @param user_id
	 * @return
	 */
	public TechStacksSearch selectTechStacks(String user_id) {
		TechStacksSearch ts_search = new TechStacksSearch();
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		List<String> selectList = ss.selectList("selectTechStacks", user_id);
		
		ss.close();
		
		String[] selectArr = selectList.toArray(new String[selectList.size()]);
		
		ts_search.setSelected_technique(selectArr);
		
		return ts_search;
	} // selectTechStacks
	
	/**
	 * ��� ���� ��� (����)
	 * @param ts_vo
	 * @return
	 */
	public int insertTechStacks(TechStacksVO ts_vo) {
		int result = 0;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		result = ss.insert("insertTechStacks", ts_vo);
		
		ss.commit();
		
		ss.close();
		
		return result;
	} // insertTechStacks
	
	/**
	 * ��� ���� ����
	 * @param ts_vo
	 * @return
	 */
	public int updateTechStacks(TechStacksVO ts_vo) {
		int result = 0;
		int cnt = 0;
		
		cnt = ts_vo.getSelected_technique().length;
		
		// reset -> insert ����
		resetTechStacks(ts_vo.getUser_id());
		
		result = insertTechStacks(ts_vo);
		
		if (result == cnt) {
			result = 1;
		} // end if
		
		return result;
	} // updateTechStacks
	
	/**
	 * ��� ���� �ʱ�ȭ (����)
	 * @param user_id
	 * @return
	 */
	public int resetTechStacks(String user_id) {
		int result = 0;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		result = ss.delete("resetTechStacks", user_id);
		
		ss.commit();
		
		ss.close();
		
		return result;
	} // resetTechStacks
	
	// -------------------- ��Ʈ������ ���� ���� -------------------- //
	
	/**
	 * ���� ���� ��ȸ
	 * @param user_id
	 * @return
	 */
	public ExperienceSearch selectExperience(ExperienceSearchVO es_vo) {
		ExperienceSearch exp_search = null;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		exp_search = ss.selectOne("selectExperience", es_vo);
		
		ss.close();
		
		return exp_search;
	} // selectExperience
	
	/**
	 * ���� ���� ��� (����)
	 * @param exp_vo
	 * @return
	 */
	public int insertExperience(ExperienceVO exp_vo) {
		int result = 0;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		result = ss.insert("insertExperience", exp_vo);
		
		return result;
	} // insertExperience
	
	/**
	 * ���� ���� ����
	 * @param exp_vo
	 * @return
	 */
	public int updateExperience(ExperienceVO exp_vo) {
		int result = 0;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		result = ss.update("updateExperience", exp_vo);
		
		return result;
	} // updateExperience
	
	/**
	 * ���� ���� �ʱ�ȭ (����)
	 * @param exp_vo
	 * @return
	 */
	public int resetExperience(String user_id) {
		int result = 0;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		result = ss.delete("resetExperience", user_id);
		
		return result;
	} // resetExperience
	
	// -------------------- ��Ʈ������ ����ó -------------------- //
	
	/**
	 * ����ó ��ȸ
	 * @param user_id
	 * @return
	 */
	public TellMeSearch selectTellMe(String user_id) {
		TellMeSearch tm_search = null;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		tm_search = ss.selectOne("selectTellMe", user_id);
		
		ss.close();
		
		return tm_search;
	} // selectTellMe
	
	/**
	 * ����ó ���
	 * @param tm_vo
	 * @return
	 */
	public int insertTellMe(TellMeVO tm_vo) {
		int result = 0;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		result = ss.insert("insertTellMe", tm_vo);
		
		ss.commit();
		
		ss.close();
		
		return result;
	} // insertTellMe
	
	/**
	 * ����ó ����
	 * @param tm_vo
	 * @return
	 */
	public int updateTellMe(TellMeVO tm_vo) {
		int result = 0;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		result = ss.update("updateTellMe", tm_vo);
		
		ss.commit();
		
		ss.close();
		
		return result;
	} // updateTellMe
	
	/**
	 * ����ó �ʱ�ȭ
	 * @param user_id
	 * @return
	 */
	public int resetTellMe(String user_id) {
		int result = 0;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		result = ss.delete("resetTellMe", user_id);
		
		ss.commit();
		
		ss.close();
		
		return result;
	} // resetTellMe
	
	// unit test
	public static void main(String[] args) {
		PortfolioDAO p_dao = new PortfolioDAO();
		
		
		
	} // main
	
} // class
