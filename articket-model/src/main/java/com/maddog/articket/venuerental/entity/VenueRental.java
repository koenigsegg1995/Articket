package com.maddog.articket.venuerental.entity;

import java.io.Serial;
import java.util.Date;

public class VenueRental implements java.io.Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 場地申請資料 ID
	 */
	private Integer venueRentalId;

	/**
	 * 場館 ID
	 */
	private Integer venueId;

	/**
	 * 廠商會員 ID
	 */
	private Integer partnerId ;

	/**
	 * 活動名稱
	 */
	private String activityName;

	/**
	 * 企劃書
	 */
	private byte[] proposal;

	/**
	 * 申請狀態 0:不通過 1:通過 3:審核中 4:取消中 5:已取消
	 */
	private Integer venueRentalStatus;

	/**
	 * 租用開始日期
	 */
	private Date venueRentalStartDate;

	/**
	 * 租用結束日期
	 */
	private Date venueRentalEndDate;

	/**
	 * 申請建立時間
	 */
	private Date venueRentalCreateTime;

	/**
	 * 場地申請編號
	 */
	private String venueRentalCode;

	/**
	 *
	 * @return venueRentalId
	 * 			Integer
	 */
	public Integer getVenueRentalId() {
		return venueRentalId;
	}

	/**
	 *
	 * @param venueRentalId
	 * 			Integer
	 */
	public void setVenueRentalId(Integer venueRentalId) {
		this.venueRentalId = venueRentalId;
	}

	/**
	 *
	 * @return venueId
	 * 			Integer
	 */
	public Integer getVenueId() {
		return venueId;
	}

	/**
	 *
	 * @param venueId
	 * 			Integer
	 */
	public void setVenueId(Integer venueId) {
		this.venueId = venueId;
	}

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
	 * @return activityName
	 * 			String
	 */
	public String getActivityName() {
		return activityName;
	}

	/**
	 *
	 * @param activityName
	 * 			String
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	/**
	 *
	 * @return proposal
	 * 			byte[]
	 */
	public byte[] getProposal() {
		return proposal;
	}

	/**
	 *
	 * @param proposal
	 * 			byte[]
	 */
	public void setProposal(byte[] proposal) {
		this.proposal = proposal;
	}

	/**
	 *
	 * @return venueRentalStatus
	 * 			Integer
	 */
	public Integer getVenueRentalStatus() {
		return venueRentalStatus;
	}

	/**
	 *
	 * @param venueRentalStatus
	 * 			Integer
	 */
	public void setVenueRentalStatus(Integer venueRentalStatus) {
		this.venueRentalStatus = venueRentalStatus;
	}

	/**
	 *
	 * @return venueRentalStartDate
	 * 			Date
	 */
	public Date getVenueRentalStartDate() {
		return venueRentalStartDate;
	}

	/**
	 *
	 * @param venueRentalStartDate
	 * 			Date
	 */
	public void setVenueRentalStartDate(Date venueRentalStartDate) {
		this.venueRentalStartDate = venueRentalStartDate;
	}

	/**
	 *
	 * @return venueRentalEndDate
	 * 			Date
	 */
	public Date getVenueRentalEndDate() {
		return venueRentalEndDate;
	}

	/**
	 *
	 * @param venueRentalEndDate
	 * 			Date
	 */
	public void setVenueRentalEndDate(Date venueRentalEndDate) {
		this.venueRentalEndDate = venueRentalEndDate;
	}

	/**
	 *
	 * @return venueRentalCreateTime
	 * 			Date
	 */
	public Date getVenueRentalCreateTime() {
		return venueRentalCreateTime;
	}

	/**
	 *
	 * @param venueRentalCreateTime
	 * 			Date
	 */
	public void setVenueRentalCreateTime(Date venueRentalCreateTime) {
		this.venueRentalCreateTime = venueRentalCreateTime;
	}

	/**
	 *
	 * @return venueRentalCode
	 * 			String
	 */
	public String getVenueRentalCode() {
		return venueRentalCode;
	}

	/**
	 *
	 * @param venueRentalCode
	 * 			String
	 */
	public void setVenueRentalCode(String venueRentalCode) {
		this.venueRentalCode = venueRentalCode;
	}

}
