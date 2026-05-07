package com.maddog.articket.venuetimeslot.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 場館時段 DO
 */
@Getter
@Setter
public class VenueTimeSlot implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 場館時段 ID
	 */
	private Integer venueTimeSlotId;

	/**
	 * 場地申請資料 ID
	 */
	private Integer venueRentalId;

	/**
	 * 日期
	 */
	private Date venueTimeSlotDate;

	/**
	 * 時段 1:早 2:午 3:晚
	 */
	private Integer venueTimeSlot;

	/**
	 * 時段狀態 0: 未被租借 1: 已被租借 2: 不提供租借 3: 活動
	 */
	private Integer venueTimeSlotStatus;

}
