package com.maddog.articket.seat.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PriceForm {

    private BigDecimal vipPrice;
    private BigDecimal aPrice;
    private BigDecimal bPrice;

    @Override
    public String toString() {
        return "PriceForm{" +
                "vipPrice=" + vipPrice +
                ", aPrice=" + aPrice +
                ", bPrice=" + bPrice +
                '}';
    }

}
