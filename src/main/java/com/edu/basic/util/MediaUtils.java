package com.edu.basic.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

public class MediaUtils {

	//static 으로 되어 있기 때문에 프로그램이 실행될 때 
	//private static Map<String, MediaType> mediaMap;와 static{}은
	//바로 메모리에 로딩된다.
	private static Map<String,MediaType> mediaMap;
	
	//클래스를 로딩할 때 제일 먼저 실행되는 코드
	static {
		mediaMap = new HashMap<>();
		mediaMap.put("JPG",MediaType.IMAGE_JPEG);
		mediaMap.put("GIF",MediaType.IMAGE_GIF);
		mediaMap.put("PNG",MediaType.IMAGE_PNG);
	}
	
	// 이미지 파일들은 업로드하면 썸네일 파일을 만들고,
	// 나머지 파일들은 그냥 업로드 한다.
	// 이미지 파일인지 아닌지를 구분하게 한다.
	// getMediaType(String type)을 호출하여 
	// 위의 static {}에 있으면 이미지파일, 없으면 일반파일이다.
	public static MediaType getMediaType(String type) {
		// toUpperCase() 대문자로 변경
		return mediaMap.get(type.toUpperCase());
	}
}



