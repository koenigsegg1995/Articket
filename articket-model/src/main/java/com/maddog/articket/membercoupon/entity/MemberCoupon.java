package com.maddog.articket.membercoupon.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 會員優惠券 DO
 */
@Getter
@Setter
public class MemberCoupon implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 會員優惠券 ID
 	 */
	private Integer memberCouponId;

	/**
	 * 會員 ID
 	 */
	private Integer memberId;

	/**
	 * 優惠券類型 ID
 	 */
	private Integer couponTypeId;

	/**
	 * 有效期限
 	 */
	private Date memberCouponExpirationDate;

	/**
	 * 使用狀態 0:未使用 1:已使用
 	 */
	private Integer memberCouponStatus;

	/**
	 * 建立時間
 	 */
	private Date memberCouponCreateTime;
	
}
