package com.edu.manager.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//-----------------------------------------------------------------------------------------------------------
// 관리자 클래스
//-----------------------------------------------------------------------------------------------------------
@Getter
@Setter
@ToString
@Component(value = "managerVO")
public class ManagerVO {
	
	private	String	id;
	private	String	pwd;
	private	String	name;
	private	String	email;
	private	Date	joinDate;

	public ManagerVO() {
		
	}
	
	public ManagerVO(String id, String pwd, String name, String email) {
		this.id		= id;
		this.pwd	= pwd;
		this.name	= name;
		this.email	= email;
	}

	
	
} // End - public class ManagerVO
