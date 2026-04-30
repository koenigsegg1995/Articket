package com.maddog.articket.partnermember.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 廠商會員 DO
 */
@Getter
@Setter
public class PartnerMember implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 廠商 ID
	 */
	private Integer partnerId;

	/**
	 * 統一編號
	 */
	private String taxId;

	/**
	 * 公司名稱
	 */
	private String partnerName;

	/**
	 * 抬頭
	 */
	private String partnerHeading;

	/**
	 * 地址
	 */
	private String partnerAddress;

	/**
	 * 連絡電話
	 */
	private String partnerPhone;

	/**
	 * 聯絡人
	 */
	private String partnerContactPerson;

	/**
	 * 密碼
	 */
	private String partnerPassword;

	/**
	 * 電子信箱
	 */
	private String partnerEmail;

	/**
	 * 帳號建立時間
	 */
	private Date partnerCreateTime;

	/**
	 * 帳號狀態 0:黑名單 1.使用中 2.申請中
	 */
	private Integer partnerAccountStatus = 2;

}
