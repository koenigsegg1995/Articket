package com.maddog.articket.seatstatus.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 座位狀態 DO
 */
@Getter
@Setter
public class SeatStatus implements java.io.Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 座位狀態 ID
	 */
	private Integer seatStatusId;

	/**
	 * 活動時段 ID
	 */
	private Integer activityTimeSlotId;

	/**
	 * 座位 ID
	 */
	private Integer seatId;

	/**
	 * 座位狀態 0: 空位 1: 已經購買 2: 廠商保留位 3: 不可使用
	 */
	private Integer seatStatus;

}
