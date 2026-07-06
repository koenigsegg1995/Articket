package com.maddog.articket.coupontype.entity;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 優惠券 DO
 */
public class CouponType implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 優惠券類型ID
	 */
	private Integer couponTypeId;

	/**
	 * 優惠券類型名稱
	 */
	private String couponTypeName;

	/**
	 * 使用規則
	 */
	private String couponTypeRegulation;

	/**
	 * 折扣數
	 */
	private BigDecimal couponTypeDiscount;

}
