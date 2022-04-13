package com.edu.goods.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.edu.goods.service.GoodsService;
import com.edu.goods.vo.BookTypeVO;
import com.edu.goods.vo.BookVO;

//-----------------------------------------------------------------------------------------------------------
// public class GoodsControllerImpl implements GoodsController 
//-----------------------------------------------------------------------------------------------------------
@Controller
public class GoodsControllerImpl implements GoodsController {
	
	private static final Logger logger = LoggerFactory.getLogger(GoodsControllerImpl.class);
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	BookTypeVO bookTypeVO;
	
	@Autowired
	BookVO bookVO;
	
	private static String BOOK_IMAGE_REPO = "C:\\data\\book_image";
	
	//-----------------------------------------------------------------------------------------------------------
	// 책 등록 폼 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value = "/goods/bookRegisterForm.do", method = RequestMethod.GET)
	public ModelAndView bookRegisterForm(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("GoodsControllerImpl bookRegisterForm 시작");

		List<BookTypeVO> goodsList = goodsService.listBookTypes();	// 도서 종류 정보를 모두 가져온다.
		System.out.println("goodsList ==> " + goodsList);
		
		String	viewName	= "/goods/bookRegisterForm";
		ModelAndView mav	= new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("bookTypList", goodsList);
		return mav;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 책 정보 등록하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value = "/goods/bookRegister.do", method = RequestMethod.POST)
	public ModelAndView bookRegister(@ModelAttribute("bookInfo") BookVO bookVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		Map<String, String> bookInfoMap = upload(request, response);
		System.out.println("bookInfoMap ==> " + bookInfoMap);
		
		
		
		

		return null;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 파일 올리기 메서드
	//-----------------------------------------------------------------------------------------------------------
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		Map<String, String> bookInfoMap = new HashMap<String, String>();
		String encoding = "utf-8";
		
		// 업로드할 파일의 경로를 지정한다.
		File				currentDirPath	= new File(BOOK_IMAGE_REPO);
		DiskFileItemFactory	factory			= new DiskFileItemFactory();
		
		// 파일 경로를 설정한다.
		factory.setRepository(currentDirPath);
		
		// 업로드될 파일의 최대 크기를 설정한다.
		factory.setSizeThreshold(1024*1024*1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			// request객체에서 매개 변수를 List로 가져온다.
			List items = upload.parseRequest(request);
			
			for(int i = 0; i < items.size(); i++) {
				// 파일 업로드 창에서 업로드된 항목들을 하나씩 가져와서 작업을 한다.
				FileItem fileItem = (FileItem) items.get(i);
				
				// 폼 필드이면 전송된 매개 변수의 값을 출력한다.
				if(fileItem.isFormField()) {
					bookInfoMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				} else { // 폼 필드가 아니면 파일 업로드 기능을 실행한다.
					// 업로드한 파일의 이름을 가져온다.
					// 파일의 사이즈가 0보다 큰 경우만 업로드를 한다.
					if(fileItem.getSize() > 0) {
						// 변수.lastIndexOf(검색값) => 변수에서 검색값 들 중에서 마지막 것을 말한다.
						int idx = fileItem.getName().lastIndexOf("\\");
						if(idx == -1) { // 이런(\\) 경로가 아니라면 / 경로의 마지막에서 파일이름을 찾는다.
							idx = fileItem.getName().lastIndexOf("/");
						}
						// 경로에서 파일 이름을 추출한다.
						// "ABCDEFGHIJ"
						// substring(4) = > 인덱스번호 4이상 모든 값 => EFGHIJ
						// substring(3, 7) => 인덱스번호 3번 부터 7번 전까지 => DEFG
						String fileName = fileItem.getName().substring(idx+1);
						
						// 업로드한 파일의 이름으로 저장소(currentDirPath)에 파일을 업로드 한다.
						// File uploadFile = new File(currentDirPath + "\\" + fileName);
						// 파일이름이 중복되면 마지막에 올린 파일만 존재하게 되므로 임시 파일에 저장시키고,
						// 책 번호를 부여받게 되면 책 번호의 폴더를 만들어서 저장시키도록 한다.
						// upload()를 호출한 곳으로 bookInfoMap에 파일 정보를 넣어준다.
						bookInfoMap.put(fileItem.getFieldName(), fileName);
						
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
						fileItem.write(uploadFile);
					}
				} // End - if
				
			} // End - for
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bookInfoMap;
		
	} // End - private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response)
	
	
	
	
} // End - public class GoodsControllerImpl implements GoodsController 














