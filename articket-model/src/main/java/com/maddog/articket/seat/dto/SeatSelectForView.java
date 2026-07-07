package com.maddog.articket.seat.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * seatSelect.html 顯示 VO
 */
@Getter
@Setter
public class SeatSelectForView {

    /**
     * 活動名稱
     */
    private String activityName;

    /**
     * 活動時段字串
     */
    private String activityTimeSlotDate;

    /**
     * 場館名稱
     */
    private String venueName;

    /**
     * 區域價格1
     */
    private BigDecimal activityAreaPrice1;

    /**
     * 區域價格2
     */
    private BigDecimal activityAreaPrice2;

    /**
     * 區域價格3
     */
    private BigDecimal activityAreaPrice3;

}
