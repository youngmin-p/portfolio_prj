package com.propofol.www.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.propofol.www.user.member.vo.LoginVO;

@Component
public class MemberDAO {
	
	@Autowired(required=false)
	private MyBatisDAO mb_dao;
	
	private static MemberDAO m_dao;
	
	private MemberDAO() {
		mb_dao = MyBatisDAO.getInstance();
		
	} // MemberDAO
	
	public static MemberDAO getInstance() {
		if (m_dao == null) {
			m_dao = new MemberDAO();
		} // end if
		
		return m_dao;
	} // getInstance
	
	/**
	 * 로그인 체크
	 * @param l_vo
	 * @return
	 */
	public int selectLogin(LoginVO l_vo) {
		int result = 0;
		
		SqlSession ss = mb_dao.getSessionFactory().openSession();
		
		result = ss.selectOne("selectLogin", l_vo);
		
		ss.close();
		
		return result;
	} // selectLogin
	
//	// unit test
//	public static void main(String[] args) {
//		MemberDAO m_dao = new MemberDAO();
//		
//		LoginVO l_vo = new LoginVO("young", "1111");
//		
//		int result = m_dao.selectLogin(l_vo);
//		
//		System.out.println(result + " / " + m_dao.selectLogin(l_vo));
//		
//	}
	
} // class
