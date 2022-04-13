package com.edu.goods.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.edu.goods.dao.GoodsDAO;
import com.edu.goods.vo.BookTypeVO;

//-----------------------------------------------------------------------------------------------------------
// public class GoodsServiceImpl implements GoodsService
//-----------------------------------------------------------------------------------------------------------
@Service
public class GoodsServiceImpl implements GoodsService {

	private static final Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);
	
	@Autowired
	private GoodsDAO goodsDAO;
	
	//-----------------------------------------------------------------------------------------------------------
	// 책 등록 폼 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<BookTypeVO> listBookTypes() throws DataAccessException {

		logger.info("GoodsServiceImpl listBookTypes() 시작");
		
		List<BookTypeVO> booksTypeList = null;
		booksTypeList = goodsDAO.listBookTypes();
		
		return booksTypeList;
	}

} // End - public class GoodsServiceImpl implements GoodsService
