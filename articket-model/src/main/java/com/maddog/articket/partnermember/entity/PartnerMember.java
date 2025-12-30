package com.maddog.articket.partnermember.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 廠商會員 DO
 */
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

	/**
	 *
	 * @return partnerId
	 * 			Integer
	 */
	public Integer getPartnerId() {
		return partnerId;
	}

	/**
	 *
	 * @param partnerId
	 * 			Integer
	 */
	public void setPartnerId(Integer partnerId) {
		this.partnerId = partnerId;
	}

	/**
	 *
	 * @return taxId
	 * 			String
	 */
	public String getTaxId() {
		return taxId;
	}

	/**
	 *
	 * @param taxId
	 * 			String
	 */
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	/**
	 *
	 * @return partnerName
	 * 			String
	 */
	public String getPartnerName() {
		return partnerName;
	}

	/**
	 *
	 * @param partnerName
	 * 			String
	 */
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	/**
	 *
	 * @return partnerHeading
	 * 			String
	 */
	public String getPartnerHeading() {
		return partnerHeading;
	}

	/**
	 *
	 * @param partnerHeading
	 * 			String
	 */
	public void setPartnerHeading(String partnerHeading) {
		this.partnerHeading = partnerHeading;
	}

	/**
	 *
	 * @return partnerAddress
	 * 			String
	 */
	public String getPartnerAddress() {
		return partnerAddress;
	}

	/**
	 *
	 * @param partnerAddress
	 * 			String
	 */
	public void setPartnerAddress(String partnerAddress) {
		this.partnerAddress = partnerAddress;
	}

	/**
	 *
	 * @return partnerPhone
	 * 			String
	 */
	public String getPartnerPhone() {
		return partnerPhone;
	}

	/**
	 *
	 * @param partnerPhone
	 * 			String
	 */
	public void setPartnerPhone(String partnerPhone) {
		this.partnerPhone = partnerPhone;
	}

	/**
	 *
	 * @return partnerContactPerson
	 * 			String
	 */
	public String getPartnerContactPerson() {
		return partnerContactPerson;
	}

	/**
	 *
	 * @param partnerContactPerson
	 * 			String
	 */
	public void setPartnerContactPerson(String partnerContactPerson) {
		this.partnerContactPerson = partnerContactPerson;
	}

	/**
	 *
	 * @return partnerPassword
	 * 			String
	 */
	public String getPartnerPassword() {
		return partnerPassword;
	}

	/**
	 *
	 * @param partnerPassword
	 * 			String
	 */
	public void setPartnerPassword(String partnerPassword) {
		this.partnerPassword = partnerPassword;
	}

	/**
	 *
	 * @return partnerEmail
	 * 			String
	 */
	public String getPartnerEmail() {
		return partnerEmail;
	}

	/**
	 *
	 * @param partnerEmail
	 * 			String
	 */
	public void setPartnerEmail(String partnerEmail) {
		this.partnerEmail = partnerEmail;
	}

	/**
	 *
	 * @return partnerCreateTime
	 * 			Date
	 */
	public Date getPartnerCreateTime() {
		return partnerCreateTime;
	}

	/**
	 *
	 * @param partnerCreateTime
	 * 			Date
	 */
	public void setPartnerCreateTime(Date partnerCreateTime) {
		this.partnerCreateTime = partnerCreateTime;
	}

	/**
	 *
	 * @return partnerAccountStatus
	 * 			Integer
	 */
	public Integer getPartnerAccountStatus() {
		return partnerAccountStatus;
	}

	/**
	 *
	 * @param partnerAccountStatus
	 * 			Integer
	 */
	public void setPartnerAccountStatus(Integer partnerAccountStatus) {
		this.partnerAccountStatus = partnerAccountStatus;
	}

}
