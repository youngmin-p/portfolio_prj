package com.propofol.www.user.dao;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyBatisDAO {
	
	@Autowired(required=false)
	private static MyBatisDAO mb_dao;
	
	private SqlSessionFactory ssf = null;
	
	private MyBatisDAO() { } // MyBatisDAO
	
	public static MyBatisDAO getInstance() {
		if (mb_dao == null) {
			mb_dao = new MyBatisDAO();
		} // end if
		
		return mb_dao;
	} // getInstance
	
	public synchronized SqlSessionFactory getSessionFactory() {
		if (ssf == null) {
			org.apache.ibatis.logging.LogFactory.useLog4JLogging();
		} // end if
		
		Reader reader = null;
		
		try {
			// 1. 설정용 xml 로딩
			reader = Resources.getResourceAsReader("com/propofol/www/user/dao/mybatis_config.xml");
			
			// 2. MyBatis Framework 생성
			SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
			
			// 3. DB와 연동된 객체 얻기
			ssf = ssfb.build(reader);
			
			if (reader != null) { reader.close(); } // end if
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} // end catch
		
		return ssf;
	} // getSessionFactory
	
} // class
