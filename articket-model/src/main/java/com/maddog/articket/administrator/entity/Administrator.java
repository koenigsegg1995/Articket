package com.maddog.articket.administrator.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 管理員 DO
 */
@Getter
@Setter
public class Administrator implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 管理員 ID
	 */
	private Integer administratorId;

	/**
	 * 帳號
	 */
	private String administratorAccount;

	/**
	 * 密碼
	 */
	private String administratorPassword;

	/**
	 * 帳號建立時間
	 */
	private Date administratorCreateTime;

	/**
	 * 帳號狀態 0:帳號正常 1:帳號停用
	 */
	private Integer administratorStatus;

}
