package com.maddog.articket.orderitem.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 訂單明細 DO
 */
@Getter
@Setter
public class OrderItem implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 訂單明細ID
	 */
	private Integer orderItemId;

	/**
	 * 訂單ID
	 */
	private Integer orderId;

	/**
	 * 商品ID
	 */
	private Integer commodityId;

	/**
	 * 商品下訂價格
	 */
	private BigDecimal commodityOrderPrice;

	/**
	 * 商品數量
	 */
	private Integer orderItemQuantity;

	/**
	 * 單一商品總價
	 */
	private BigDecimal orderItemTotalPrice;

	/**
	 * 建立時間
	 */
	private Date orderItemCreateTime;

}

