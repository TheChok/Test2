package com.edu.manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.manager.service.ManagerService;
import com.edu.manager.vo.ManagerVO;

//-----------------------------------------------------------------------------------------------------------
// public class ManagerControllerImpl implements ManagerController
//-----------------------------------------------------------------------------------------------------------
@Controller("managerControllerImpl")
public class ManagerControllerImpl implements ManagerController {

	private static final Logger logger = LoggerFactory.getLogger(ManagerControllerImpl.class);
	
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private ManagerVO managerVO;
	
	//-----------------------------------------------------------------------------------------------------------
	// 매니저 로그인 화면
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/manager/loginForm.go", method = RequestMethod.GET)
	public ModelAndView managerLoginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String	viewName = "/manager/logon/managerLoginForm";
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 매니저 로그인 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value = "/manager/managerLogin.go", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute(value="manager") ManagerVO manager, RedirectAttributes rAttr, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.info("ManagerController login.....");

		String	viewName = "";
		ModelAndView mav = new ModelAndView();
		
		
		managerVO = managerService.login(manager);
		
		//System.out.println("managerVO ====> " + managerVO);
		//System.out.println("managerVO ====> " + managerVO.getId() + ":" + managerVO.getPwd());
		System.out.println("manager  ====> " + manager.getId() + ":" + manager.getPwd());
		
		// 아이디가 존재하지 않으면
		if(managerVO == null) {
			// 로그인 정보에 해당하는 자료가 존재하지 않는다면
			rAttr.addAttribute("result", "loginFailed");
			viewName = "redirect:/manager/loginForm.go";
			
		} else {
			// 아이디와 비밀번호 모두가 맞으면
			if(manager.getPwd().equals(managerVO.getPwd())) {
				// 세션을 발급하고, 관리자 메인 화면으로 이동한다.
				HttpSession session = request.getSession();
				session.setAttribute("managerId", 	managerVO.getId());
				session.setAttribute("isLogOn", 	true);
				
				viewName = "/manager/managerMain";
			} else {	// 아이디는 맞지만 비밀번호가 틀린 경우
				rAttr.addAttribute("result", "passwordFailed");
				viewName = "redirect:/manager/loginForm.go";
			}
		}

		mav.setViewName(viewName);
		return mav;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 매니저 로그아웃
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value = "/manager/logout.do", method = RequestMethod.GET)
	public ModelAndView managerLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		session.removeAttribute("managerId");
		session.removeAttribute("isLogOn");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/manager/loginForm.go");
		return mav;
	}

	
	
	
	
} // End - public class ManagerControllerImpl implements ManagerController









