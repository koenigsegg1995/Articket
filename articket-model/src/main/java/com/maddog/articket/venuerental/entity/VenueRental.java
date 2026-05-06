package com.maddog.articket.venuerental.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 場地申請資料 DO
 */
@Getter
@Setter
public class VenueRental implements Serializable {

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

}
