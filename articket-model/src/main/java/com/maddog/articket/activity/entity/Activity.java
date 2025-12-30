package com.maddog.articket.activity.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 活動 DO
 */
@Setter
@Getter
public class Activity implements Serializable {

	/**
	 * 序列化
	 */
	@Serial
    private static final long serialVersionUID = 1L;

	/**
	 * 活動 ID
     */
	private Integer activityId;

	/**
	 * 廠商 ID
     */
	private Integer partnerId;

	/**
	 * 場館 ID
     */
	private Integer venueId;

	/**
	 * 場館申請資料 ID
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

}