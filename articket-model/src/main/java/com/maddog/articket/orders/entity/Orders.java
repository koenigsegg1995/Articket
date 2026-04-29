package com.maddog.articket.orders.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 訂單 DO
 */
@Getter
@Setter
public class Orders implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 訂單ID
	 */
	private Integer orderId;

	/**
	 * 會員ID
	 */
	private Integer memberId;

	/**
	 * 收件人姓名
	 */
	private String recipient;

	/**
	 * 收件人電話
	 */
	private String recipientPhone;

	/**
	 * 收件人E-mail
	 */
	private String recipientEmail;

	/**
	 * 收件地址
	 */
	private String recipientAddress;

	/**
	 * 會員優惠券ID
	 */
	private Integer memberCouponId;

	/**
	 * 實付金額
	 */
	private BigDecimal actualAmount;

	/**
	 * 訂單狀態 0:取消 1:未出貨 2:已出貨 3:完成訂單 4:退貨中 5:完成退貨
	 */
	private Integer orderStatus;

	/**
	 * 支付狀態
	 */
	private Integer payStatus;

	/**
	 * 付款時間
	 */
	private Date payTime;

	/**
	 * 出貨時間
	 */
	private Date shipTime;

}