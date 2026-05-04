package com.maddog.articket.seat.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class Seat implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 座位ID
	 */
	private Integer seatId;

	/**
	 * 場館ID
	 */
	private Integer venueId;

	/**
	 * 場館區域ID
	 */
	private Integer venueAreaId;

	/**
	 * 座位代號 如NA001, NA002, NA003, NA004
	 */
	private String seatName;

	/**
	 * 行
	 */
	private Integer seatRow;

	/**
	 * 號
	 */
	private Integer seatNumber;

}
