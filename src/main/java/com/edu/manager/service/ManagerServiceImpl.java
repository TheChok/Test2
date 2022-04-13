package com.edu.manager.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.manager.dao.ManagerDAO;
import com.edu.manager.vo.ManagerVO;

//-----------------------------------------------------------------------------------------------------------
// public class ManagerServiceImpl implements ManagerService 
//-----------------------------------------------------------------------------------------------------------
@Service("managerService")
public class ManagerServiceImpl implements ManagerService {
	
	private static final Logger logger = LoggerFactory.getLogger(ManagerServiceImpl.class);

	@Autowired
	private ManagerDAO managerDAO;
	
	//-----------------------------------------------------------------------------------------------------------
	// 매니저 로그인 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public ManagerVO login(ManagerVO managerVO) throws Exception {
		logger.info("ManagerService login.....");
		return managerDAO.loginById(managerVO);
	}

} // End - public class ManagerServiceImpl implements ManagerService 





