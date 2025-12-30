package com.maddog.articket.commodity.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 商品 DO
 */
@Getter
@Setter
public class Commodity implements Serializable{

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 商品 ID
	 */
    private Integer commodityId;

	/**
	 * 商品名稱
	 */
    private String commodityName;

	/**
	 * 商品價格
	 */
    private BigDecimal commodityPrice;

	/**
	 * 商品庫存
	 */
	private Integer commodityStock;

	/**
	 * 商品內容
	 */
    private String commodityContent;

	/**
	 * 活動 ID
	 */
	private Integer activityId;

	/**
	 * 廠商 ID
	 */
    private Integer partnerId;

	/**
	 * 商品狀態 0:下架 1:在售
	 */
    private Integer commodityStatus;

	/**
	 * 上架時間
	 */
    private Date commodityPostTime;

	/**
	 * 下架時間
	 */
    private Date commodityRemoveTime;

	/**
	 * 更新時間
	 */
    private Date commodityUpdateTime;

	/**
	 * 建立時間
	 */
    private Date commodityCreateTime;
    
}
