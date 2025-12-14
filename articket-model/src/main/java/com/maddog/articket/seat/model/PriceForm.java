package com.maddog.articket.seat.model;

import java.math.BigDecimal;

public class PriceForm {
    private BigDecimal vipPrice;
    private BigDecimal aPrice;
    private BigDecimal bPrice;

    // 構造函數
    public PriceForm() {}

    public PriceForm(BigDecimal vipPrice, BigDecimal aPrice, BigDecimal bPrice) {
        this.vipPrice = vipPrice;
        this.aPrice = aPrice;
        this.bPrice = bPrice;
    }

    // Getters and Setters
    public BigDecimal getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(BigDecimal vipPrice) {
        this.vipPrice = vipPrice;
    }

    public BigDecimal getAPrice() {
        return aPrice;
    }

    public void setAPrice(BigDecimal aPrice) {
        this.aPrice = aPrice;
    }

    public BigDecimal getBPrice() {
        return bPrice;
    }

    public void setBPrice(BigDecimal bPrice) {
        this.bPrice = bPrice;
    }

    @Override
    public String toString() {
        return "PriceForm{" +
                "vipPrice=" + vipPrice +
                ", aPrice=" + aPrice +
                ", bPrice=" + bPrice +
                '}';
    }
}
