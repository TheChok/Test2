package com.edu.goods.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.edu.goods.vo.BookTypeVO;

//-----------------------------------------------------------------------------------------------------------
// public interface GoodsDAO
//-----------------------------------------------------------------------------------------------------------
public interface GoodsDAO {

	//-----------------------------------------------------------------------------------------------------------
	// 책 등록 폼 불러오기
	//-----------------------------------------------------------------------------------------------------------
	public List<BookTypeVO> listBookTypes() throws DataAccessException;



} // End - public interface GoodsDAO
