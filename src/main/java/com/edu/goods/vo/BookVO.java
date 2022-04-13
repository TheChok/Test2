package com.edu.goods.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//-----------------------------------------------------------------------------------------------------------
// 책 정보
//-----------------------------------------------------------------------------------------------------------
@Getter
@Setter
@ToString
@Component(value = "bookVO")
public class BookVO {

	private	int		book_id;			// 책의 등록번호
	private	String	book_type;			// 책의 종류
	private	String	book_title;			// 책의 제목
	private	int		book_price;			// 책의 가격
	private	int		book_count;			// 책의 재고 수량
	
	private	String	book_writer;		// 책의 저자
	private	String	publishing_com;		// 출판사
	private	String	publishing_date;	// 출판일
	private	String	book_image;			// 책의 이미지명
	private	String	book_content;		// 책의 내용
	
	private	int		discount_rate;		// 책의 할인율
	private	Date	reg_date;			// 책의 등록일자
	
} // End - public class BookVO



















