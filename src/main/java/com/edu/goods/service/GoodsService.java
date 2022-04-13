package com.edu.goods.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.edu.goods.vo.BookTypeVO;

//-----------------------------------------------------------------------------------------------------------
// public interface GoodsService
//-----------------------------------------------------------------------------------------------------------
public interface GoodsService {

	//-----------------------------------------------------------------------------------------------------------
	// 책 등록 폼 불러오기
	//-----------------------------------------------------------------------------------------------------------
	public List<BookTypeVO> listBookTypes() throws DataAccessException;
	
	
} // End - public interface GoodsService
