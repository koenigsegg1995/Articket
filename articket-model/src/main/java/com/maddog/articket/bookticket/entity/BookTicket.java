package com.maddog.articket.bookticket.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 票券訂單
 */
@Getter
@Setter
public class BookTicket implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 票券訂單 ID
	 */
	private Integer bookTicketId;

	/**
	 * 一般會員 ID(買家)
	 */
	private Integer memberId;

	/**
	 * 活動 ID
	 */
	private Integer activityId;

	/**
	 * 活動時段 ID
	 */
	private Integer activityTimeSlotId;

	/**
	 * 會員優惠券 ID
	 */
	private Integer memberCouponId;

	/**
	 * 訂購日期
	 */
	private Date bookTime;

	/**
	 * 數量
	 */
	private Integer ticketQuantity;

	/**
	 * 總金額
	 */
	private BigDecimal totalPrice;

}
