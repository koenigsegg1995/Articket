package com.maddog.articket.activity.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 活動
 */
public class Activity implements Serializable {

	/**
	 * 序列化
	 */
	@Serial
    private static final long serialVersionUID = 1L;

	/**
	 * 活動ID
	 */
	private Integer activityId;

	/**
	 * 廠商ID
	 */
	private Integer partnerId;

	/**
	 * 場館ID
	 */
	private Integer venueId;

	/**
	 * 場館申請資料ID
	 */
	private Integer venueRentalId;

	/**
	 * 名稱
	 */
	private String activityName;

	/**
	 * 內容
	 */
	private String activityContent;

	/**
	 * 建立時間
	 */
	private Date activityCreateTime;

	/**
	 * 排程時間
	 */
	private Date activityPostTime;

	/**
	 * 類型標籤
	 */
	private String activityTag;

	/**
	 * 設定狀態 0:未設定 1:已設定
	 */
	private Integer activityStatus = 0;

	/**
	 * 票券設定狀態 0:未設定 1:已設定
	 */
	private Integer ticketSetStatus = 0;

	/**
	 * 起售日
	 */
	private Date sellTime;

	/**
	 *
	 * @return activityId
	 * 			Integer
	 */
	public Integer getActivityId() {
		return activityId;
	}

	/**
	 *
	 * @param activityId
	 * 			Integer
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
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
	 * @return activityContent
	 * 			String
	 */
	public String getActivityContent() {
		return activityContent;
	}

	/**
	 *
	 * @param activityContent
	 * 			String
	 */
	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}

	/**
	 *
	 * @return activityCreateTime
	 * 			Date
	 */
	public Date getActivityCreateTime() {
		return activityCreateTime;
	}

	/**
	 *
	 * @param activityCreateTime
	 * 			Date
	 */
	public void setActivityCreateTime(Date activityCreateTime) {
		this.activityCreateTime = activityCreateTime;
	}

	/**
	 *
	 * @return activityPostTime
	 * 			Date
	 */
	public Date getActivityPostTime() {
		return activityPostTime;
	}

	/**
	 *
	 * @param activityPostTime
	 * 			Date
	 */
	public void setActivityPostTime(Date activityPostTime) {
		this.activityPostTime = activityPostTime;
	}

	/**
	 *
	 * @return activityTag
	 * 			String
	 */
	public String getActivityTag() {
		return activityTag;
	}

	/**
	 *
	 * @param activityTag
	 * 			String
	 */
	public void setActivityTag(String activityTag) {
		this.activityTag = activityTag;
	}

	/**
	 *
	 * @return activityStatus
	 * 			Integer
	 */
	public Integer getActivityStatus() {
		return activityStatus;
	}

	/**
	 *
	 * @param activityStatus
	 * 			Integer
	 */
	public void setActivityStatus(Integer activityStatus) {
		this.activityStatus = activityStatus;
	}

	/**
	 *
	 * @return ticketSetStatus
	 * 			Integer
	 */
	public Integer getTicketSetStatus() {
		return ticketSetStatus;
	}

	/**
	 *
	 * @param ticketSetStatus
	 * 			Integer
	 */
	public void setTicketSetStatus(Integer ticketSetStatus) {
		this.ticketSetStatus = ticketSetStatus;
	}

	/**
	 *
	 * @return sellTime
	 * 			Date
	 */
	public Date getSellTime() {
		return sellTime;
	}

	/**
	 *
	 * @param sellTime
	 * 			Date
	 */
	public void setSellTime(Date sellTime) {
		this.sellTime = sellTime;
	}

}