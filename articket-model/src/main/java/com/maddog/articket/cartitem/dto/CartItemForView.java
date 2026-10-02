package com.maddog.articket.cartitem.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CartItemForView {

    /**
     * 商品編號
     */
    private Integer commodityId;

    /**
     * 商品名稱
     */
    private String commodityName;

    /**
     * 商品金額
     */
    private BigDecimal commodityPrice;

    /**
     * 數量
     */
    private Integer checkedQuantity;

    /**
     * 小計
     */
    @Getter(AccessLevel.NONE)
    private BigDecimal total;

    public BigDecimal getTotal(){
        return commodityPrice.multiply(BigDecimal.valueOf(checkedQuantity));
    }

}
