package com.maddog.articket.commodity.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 商品 DTO
 */
@Getter
@Setter
public class CommodityForView {

    /**
     * 商品 ID
     */
    private Integer commodityId;

    /**
     * 活動名稱
     */
    private String activityName;

    /**
     * 商品價格
     */
    private BigDecimal commodityPrice;

    /**
     * 商品名稱
     */
    private String commodityName;

}
