package com.edu.basic.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.java.Log;

//-------------------------------------------------------------------------------------------------
// public class UploadController
//-------------------------------------------------------------------------------------------------
@Log
@Controller
public class UploadController {

    private static final int RESULT_EXCEED_SIZE = -2;
    private static final int RESULT_UNACCEPTED_EXTENSION = -1;
    private static final int RESULT_SUCCESS = 1;
    private static final long LIMIT_SIZE = 10 * 1024 * 1024;

	//-------------------------------------------------------------------------------------------------
	//로깅을 위한 코드
	//-------------------------------------------------------------------------------------------------
	private static final Logger logger
		= LoggerFactory.getLogger(UploadController.class);
	
	//-------------------------------------------------------------------------------------------------
	// servlet-context.xml에 선언한 String bean을 참조한다.
	//-------------------------------------------------------------------------------------------------
	@Resource(name="uploadPath")
	// String uploadPath="c:/data/upload"; <== 직접 기술해도 된다.
	String uploadPath; // <== 공통적으로 사용하기 위해서 선언한다.
	
	//-------------------------------------------------------------------------------------------------
	//업로드 폼 페이지로 이동
	//-------------------------------------------------------------------------------------------------
	@RequestMapping(value="/upload/uploadForm", method=RequestMethod.GET)
	public String uploadForm() {
		//  views/upload/uploadForm.jsp
		return "upload/uploadForm";
	}
	
	//-------------------------------------------------------------------------------------------------
	//업로드된 내용을 처리
	//-------------------------------------------------------------------------------------------------
	// MultipartFile file : 업로드한 파일이 저장됨	(임시경로)
	@RequestMapping(value="/upload/uploadForm" ,method=RequestMethod.POST)
	public ModelAndView uploadForm(MultipartFile file, ModelAndView mav) throws Exception {
		logger.info("파일이름:" + file.getOriginalFilename());
		logger.info("파일크기:" + file.getSize());
		logger.info("컨텐트 타입:" + file.getContentType());
		String savedName=file.getOriginalFilename();
		savedName = uploadFile(savedName, file.getBytes());
		
		mav.setViewName("upload/uploadResult"); //뷰의 이름 => 처리가 끝나고 돌아갈 페이지
		mav.addObject("savedName", savedName); //전달할 데이터
		return mav; // uploadResult.jsp로 포워딩된다.
	}
	
	//-------------------------------------------------------------------------------------------------
	// 파일 이름이 중복되지 않도록 처리한다.
	//-------------------------------------------------------------------------------------------------
	String uploadFile(String originalName, byte[] fileData) throws Exception {
		// Universal Unique IDentifier, 범용고유식별자 <== 코드를 랜덤으로 만들어 낸다.
		UUID uid=UUID.randomUUID();
		String savedName=uid.toString()+"_"+originalName;
		
		// new File(디렉토리, 파일이름)
		File target=new File(uploadPath, savedName);
		
		//첨부파일을 임시디렉토리에서 우리가 지정한 디렉토리로 복사
		FileCopyUtils.copy(fileData, target);
		return savedName; // 복사한 파일이름을 되돌려 준다.
	}
	
	
	//-------------------------------------------------------------------------------------------------
	// 다중 파일 올리기 폼
	//-------------------------------------------------------------------------------------------------
	@RequestMapping(value="/upload/uploadMultiForm", method=RequestMethod.GET)
	public String uploadMultiForm() {
		//  views/upload/uploadMultiForm.jsp
		return "/upload/uploadMultiForm";
	}
	
	//-------------------------------------------------------------------------------------------------
	// 다중 파일 올리기
	//-------------------------------------------------------------------------------------------------
    @RequestMapping(value = "upload/uploadMultiForm", method=RequestMethod.POST)
    public String uploadMulti(MultipartHttpServletRequest mtfRequest) {
        List<MultipartFile> fileList = mtfRequest.getFiles("file");
        String src = mtfRequest.getParameter("src");
        System.out.println("src value : " + src);

        String path = "C:\\data\\upload\\";

        for (MultipartFile mf : fileList) {
            String originFileName = mf.getOriginalFilename(); // 원본 파일 명
            long fileSize = mf.getSize(); // 파일 사이즈

            System.out.println("originFileName : " + originFileName);
            System.out.println("fileSize : " + fileSize);

            String safeFile = path + System.currentTimeMillis() + originFileName;
            try {
                mf.transferTo(new File(safeFile));
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return "redirect:/upload/uploadMultiForm";
    }

	//-------------------------------------------------------------------------------------------------
	// 다중 파일 올리기 폼
	//-------------------------------------------------------------------------------------------------
	@RequestMapping(value="/upload/uploadMultiForm2", method=RequestMethod.GET)
	public String uploadMultiForm2() {
		logger.info("==> uploadMultiForm2 GET 시작되었습니다. ==> ");
		//  views/upload/uploadMultiForm.jsp
		return "/upload/uploadMultiForm2";
	}
	
	//-------------------------------------------------------------------------------------------------
	// 다중 파일 올리기
	//-------------------------------------------------------------------------------------------------
    @ResponseBody
    @RequestMapping(value="/upload/uploadMultiForm2", method=RequestMethod.POST)
    public int uploadMulti2(@RequestParam("files")List<MultipartFile> images) {
    	logger.info("==> uploadMultiForm2 POST 시작되었습니다. ==> ");
        long sizeSum = 0;
        for(MultipartFile image : images) {
            String originalName = image.getOriginalFilename();
            logger.info("올라온 파일 ==> " + originalName);
            //확장자 검사
            if(!isValidExtension(originalName)){
                return RESULT_UNACCEPTED_EXTENSION;
            }
            
            //용량 검사
            sizeSum += image.getSize();
            if(sizeSum >= LIMIT_SIZE) {
                return RESULT_EXCEED_SIZE;
            }
            
            //TODO 저장..
        }
        
        //실제로는 저장 후 이미지를 불러올 위치를 콜백반환하거나,
        //특정 행위를 유도하는 값을 주는 것이 옳은 것 같다.
        return RESULT_SUCCESS;
    }

    private boolean isValidExtension(String originalName) {
        String originalNameExtension = originalName.substring(originalName.lastIndexOf(".") + 1);
        switch(originalNameExtension) {
        case "jpg":
        case "png":
        case "gif":
            return true;
        }
        return false;
    }

    

} // End - public class UploadController













