package com.maddog.articket.activityareaprice.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 活動區域價格 DO
 */
@Getter
@Setter
public class ActivityAreaPrice implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 活動區域價格 ID
	 */
	private Integer activityAreaPriceId;

	/**
	 * 區域 ID
	 */
	private Integer venueAreaId;

	/**
	 * 活動 ID
	 */
	private Integer activityId;

	/**
	 * 活動區域價格
	 */
	private BigDecimal activityAreaPrice;

}
