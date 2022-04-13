package com.edu.manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.manager.vo.ManagerVO;

//-----------------------------------------------------------------------------------------------------------
// public interface ManagerController
//-----------------------------------------------------------------------------------------------------------
public interface ManagerController {

	//-----------------------------------------------------------------------------------------------------------
	// 관리자 로그인
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView login(@ModelAttribute("manager") ManagerVO manager,
			RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception;

	//-----------------------------------------------------------------------------------------------------------
	// 관리자 로그아웃
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView managerLogout(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	

} // End - public interface ManagerController


















