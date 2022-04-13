package com.edu.manager.dao;

import org.springframework.dao.DataAccessException;

import com.edu.manager.vo.ManagerVO;

//-----------------------------------------------------------------------------------------------------------
// public interface ManagerDAO
//-----------------------------------------------------------------------------------------------------------
public interface ManagerDAO {

	//-----------------------------------------------------------------------------------------------------------
	// 매니저 로그인 처리
	//-----------------------------------------------------------------------------------------------------------
	public ManagerVO loginById(ManagerVO managerVO) throws DataAccessException;

} // End - public interface ManagerDAO
