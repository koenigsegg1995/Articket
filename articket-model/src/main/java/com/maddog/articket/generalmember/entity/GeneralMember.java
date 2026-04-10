package com.maddog.articket.generalmember.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

/**
 * 會員 DO
 */
@Getter
@Setter
public class GeneralMember implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 會員ID
	 */
	private Integer memberId;

	/**
	 * 帳號(E-mail)
	 */
	private String memberAccount;

	/**
	 * 密碼
	 */
	private String memberPassword;

	/**
	 * 姓名
	 */
	private String memberName;

	/**
	 * 電話
	 */
	private String memberPhone;

	/**
	 * 暱稱
	 */
	private String memberNickName;

	/**
	 * 地址
	 */
	private String memberAddress;

	/**
	 * 身分證字號
	 */
	private String nationalId;

	/**
	 * 性別
	 */
	private String gender;

	/**
	 * 生日
	 */
	private Date birthday;

	/**
	 * 大頭照
	 */
	private byte[] memberPicture;

	/**
	 * 帳號狀態 0:帳號已黑單 1:帳號正常
	 */
	private Integer memberStatus = 1;

	/**
	 * 帳號建立時間
	 */
	private Date memberCreateTime;

}