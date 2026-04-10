package com.maddog.articket.generalmember.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 會員查詢條件 DTO
 */
@Getter
@Setter
public class GeneralMemberQueryCondition implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 會員編號
	 */
	private Integer memberId;

	/**
	 * 會員姓名
	 */
	private String memberName;

	/**
	 * 會員居住地
	 */
	private String memberAddress;

	/**
	 * 會員性別
	 */
	private String gender;
	
}