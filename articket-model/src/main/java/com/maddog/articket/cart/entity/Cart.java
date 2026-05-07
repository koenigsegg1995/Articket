package com.maddog.articket.cart.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 購物車 DO
 */
@Getter
@Setter
public class Cart implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 購物車 ID
	 */
	private Integer cartId;

	/**
	 * 會員 ID
	 */
	private Integer memberId;

	/**
	 * 購物車總價
	 */
	private BigDecimal cartTotalPrice;

	/**
	 * 建立時間
	 */
	private Date cartCreateTime;

}
