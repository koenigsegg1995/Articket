package com.maddog.articket.cartitem.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 購物車明細 DO
 */
@Getter
@Setter
public class CartItem implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 購物車明細ID
	 */
	private Integer cartItemId;

	/**
	 * 購物車ID
	 */
	private Integer cartId;

	/**
	 * 商品ID
	 */
	private Integer commodityId;

	/**
	 * 購買商品數量
	 */
	private Integer checkedQuantity;

}