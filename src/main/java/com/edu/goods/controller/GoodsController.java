package com.edu.goods.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.edu.goods.vo.BookVO;

//-----------------------------------------------------------------------------------------------------------
// public interface GoodsController
//-----------------------------------------------------------------------------------------------------------
public interface GoodsController {

	//-----------------------------------------------------------------------------------------------------------
	// 책 등록 폼 불러오기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView bookRegisterForm(HttpServletRequest request, HttpServletResponse response) throws Exception;

	//-----------------------------------------------------------------------------------------------------------
	// 책 정보 등록하기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView bookRegister(@ModelAttribute("bookInfo") BookVO bookVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	
} // End - public interface GoodsController







