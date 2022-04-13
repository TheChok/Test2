package com.edu.manager.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.edu.manager.vo.ManagerVO;

//-----------------------------------------------------------------------------------------------------------
// public class ManagerDAOImpl implements ManagerDAO
//-----------------------------------------------------------------------------------------------------------
@Repository("managerDAO")
public class ManagerDAOImpl implements ManagerDAO {

	private static final Logger logger = LoggerFactory.getLogger(ManagerDAOImpl.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	//-----------------------------------------------------------------------------------------------------------
	// 매니저 로그인 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public ManagerVO loginById(ManagerVO managerVO) throws DataAccessException {
		logger.info("ManagerDAOImpl loginById.....");
		ManagerVO vo = sqlSession.selectOne("mapper.manager.loginById", managerVO);
		return vo;
	}

} // End - public class ManagerDAOImpl implements ManagerDAO
