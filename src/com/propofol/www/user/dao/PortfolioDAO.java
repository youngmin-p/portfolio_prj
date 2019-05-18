package com.propofol.www.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.propofol.www.user.portfolio.domain.AboutMeSearch;
import com.propofol.www.user.portfolio.domain.ExperienceSearch;
import com.propofol.www.user.portfolio.domain.MyPortfolioSearch;
import com.propofol.www.user.portfolio.domain.PortfolioListSearch;
import com.propofol.www.user.portfolio.domain.PortfolioViewTitleSearch;
import com.propofol.www.user.portfolio.domain.TechStacksSearch;
import com.propofol.www.user.portfolio.domain.TellMeSearch;
import com.propofol.www.user.portfolio.vo.AboutMeVO;
import com.propofol.www.user.portfolio.vo.ExperienceResetVO;
import com.propofol.www.user.portfolio.vo.ExperienceSearchVO;
import com.propofol.www.user.portfolio.vo.ExperienceVO;
import com.propofol.www.user.portfolio.vo.MyPortfolioVO;
import com.propofol.www.user.portfolio.vo.PortfolioListIdx;
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
		
		// ������ ���̺� ��� ���� �ʿ�
		
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
		TechStacksSearch ts_search = null;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		List<String> selectList = ss.selectList("selectTechStacks", user_id);
		
		if (selectList.size() != 0) {
			ss.close();
			
			ts_search = new TechStacksSearch();
			
			String[] selectArr = selectList.toArray(new String[selectList.size()]);
			
			ts_search.setSelected_technique(selectArr);
		} // end if
		
		return ts_search;
	} // selectTechStacks
	
	/**
	 * ��� ���� ��� (����)
	 * @param ts_vo
	 * @return
	 */
	public int insertTechStacks(TechStacksVO ts_vo) {
		int result = 0;
		
		int cnt = ts_vo.getSelected_technique().length;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		result = ss.insert("insertTechStacks", ts_vo);
		
		if (cnt == result) {
			ss.commit();
			
			result = 1;
		} // end if
		
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
		
		if (result != 0) {
			ss.commit();
			
			result = 1;
		} // end if
		
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
		
		ss.commit();
		
		ss.close();
		
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
		
		System.out.println("----- dao ����" + exp_vo.getExp_cd());
		
		result = ss.update("updateExperience", exp_vo);
		
		ss.commit();
		
		System.out.println("----- commit �Ϸ�");
		
		ss.close();
		
		return result;
	} // updateExperience
	
	/**
	 * ���� ���� �ʱ�ȭ (����)
	 * @param exp_vo
	 * @return
	 */
	public int resetExperience(ExperienceResetVO expr_vo) {
		int result = 0;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		result = ss.delete("resetExperience", expr_vo);
		
		ss.commit();
		
		ss.close();
		
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
	
	// -------------------- ��Ʈ������ �Խ��� -------------------- //
	
	/**
	 * ��Ʈ������ �Խù� �� ��ȸ
	 * @return
	 */
	public int selectPortfolioTotalCount() {
		int cnt = 0;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		cnt = ss.selectOne("selectPortfolioTotalCount");
		
		ss.close();
		
		return cnt;
	} // selectPortfolioCount
	
	/**
	 * ��Ʈ������ �Խù� ��ȸ (���� 20��)
	 * @return
	 */
	public List<PortfolioListSearch> selectPortfolioList() {
		List<PortfolioListSearch> plsList = null;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		plsList = ss.selectList("selectPortfolioList");
		
		ss.close();
		
		return plsList;
	} // selectPortfolioList
	
	/**
	 * ��Ʈ������ �Խù� ��ȸ (10��, ajax call)
	 * @param idx
	 * @return
	 */
	public List<PortfolioListSearch> selectPortfolioListCall(PortfolioListIdx plIdx) {
		List<PortfolioListSearch> plsList = null;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		plsList = ss.selectList("selectPortfolioListCall", plIdx);
		
		ss.close();
		
		return plsList;
	} // selectPortfolioList
	
	/**
	 * ���� ���� ��ȸ (��Ʈ������ �Խñ�)
	 * @param target_id
	 * @return
	 */
	public List<ExperienceSearch> selectExperience(String target_id) {
		List<ExperienceSearch> exp_search = null;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		exp_search = ss.selectList("selectExperienceList", target_id);
		
		ss.close();
		
		return exp_search;
	} // selectExperience
	
	/**
	 * ��Ʈ������ �Խñ� Ÿ��Ʋ ��ȸ
	 * @param target_id
	 * @return
	 */
	public PortfolioViewTitleSearch selectPortfolioViewTitle(String target_id) {
		PortfolioViewTitleSearch pvt_search = null;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		pvt_search = ss.selectOne("selectPortfolioViewTitle", target_id);
		
		ss.close();
		
		return pvt_search;
	} // selectPortfolioViewTitle
	
	public int updatePortfolioHits(String target_id) {
		int result = 0;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		result = ss.update("updatePortfolioHits", target_id);
		
		ss.commit();
		
		ss.close();
		
		return result;
	} // updatePortfolioHits
	
	public List<PortfolioListSearch> searchPortfolioList(String keyword) {
		List<PortfolioListSearch> plsList = null;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		plsList = ss.selectList("searchPortfolioList", keyword);
		
		return plsList;
	} // searchPortfolioList
	
	public static void main(String[] args) {
		PortfolioDAO p_dao = new PortfolioDAO();
		
		String keyword = "��Ʈ������";
		
		List<PortfolioListSearch> pl_search = p_dao.searchPortfolioList(keyword);
		
		for (PortfolioListSearch pls : pl_search) {
			System.out.println(pls);
		}
		
	}
	
} // class
