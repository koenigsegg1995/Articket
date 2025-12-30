package com.maddog.articket.activitycollection.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 活動收藏 DO
 */
public class ActivityCollection implements Serializable{

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 活動收藏ID
	 */
	private Integer activityCollectionId;

	/**
	 * 一般會員ID
	 */
	private Integer memberId;

	/**
	 * 活動ID
	 */
	private Integer activityId;

	/**
	 * 活動收藏時間
	 */
	private Date activityCollectionTime;

	/**
	 *
	 * @return activityCollectionId
	 * 			Integer
	 */
	public Integer getActivityCollectionId() {
		return activityCollectionId;
	}

	/**
	 *
	 * @param activityCollectionId
	 * 			Integer
	 */
	public void setActivityCollectionId(Integer activityCollectionId) {
		this.activityCollectionId = activityCollectionId;
	}

	/**
	 *
	 * @return memberId
	 * 			Integer
	 */
	public Integer getMemberId() {
		return memberId;
	}

	/**
	 *
	 * @param memberId
	 * 			Integer
	 */
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

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
	 * @return activityCollectionTime
	 * 			Date
	 */
	public Date getActivityCollectionTime() {
		return activityCollectionTime;
	}

	/**
	 *
	 * @param activityCollectionTime
	 * 			Date
	 */
	public void setActivityCollectionTime(Date activityCollectionTime) {
		this.activityCollectionTime = activityCollectionTime;
	}

}
