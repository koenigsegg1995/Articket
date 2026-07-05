package com.maddog.articket.commoditypicture.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 商品圖片 DO
 */
@Getter
@Setter
public class CommodityPicture implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 商品圖片ID
	 */
	private Integer commodityPictureId;

	/**
	 * 商品ID
	 */
	private Integer commodityId;

	/**
	 * 商品圖片
	 */
	private byte[] commodityPicture;

}