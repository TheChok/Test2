package com.edu.goods.vo;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//-----------------------------------------------------------------------------------------------------------
// public class BookTypeVO
//-----------------------------------------------------------------------------------------------------------
@Getter
@Setter
@ToString
@Component(value = "bookTypeVO")
public class BookTypeVO {

	private	String	type;	// 책의 종류 코드
	private	String	name;	// 책의 종류 내용
	
	
	
} // End - public class BookTypeVO
