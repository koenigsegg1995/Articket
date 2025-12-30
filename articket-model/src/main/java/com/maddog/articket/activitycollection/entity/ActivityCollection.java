package com.maddog.articket.activitycollection.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 活動收藏 DO
 */
@Setter
@Getter
public class ActivityCollection implements Serializable{

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 活動收藏 ID
     */
	private Integer activityCollectionId;

	/**
	 * 一般會員 ID
     */
	private Integer memberId;

	/**
	 * 活動 ID
     */
	private Integer activityId;

	/**
	 * 活動收藏時間
     */
	private Date activityCollectionTime;

}
