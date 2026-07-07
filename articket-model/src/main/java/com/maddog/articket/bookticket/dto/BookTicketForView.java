package com.maddog.articket.bookticket.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 票券訂單 VO
 */
@Getter
@Setter
public class BookTicketForView implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 票券訂單 ID
     */
    private Integer bookTicketId;

    /**
     * 活動名稱
     */
    private String activityName;

    /**
     * 日期
     */
    private Date activityTimeSlotDate;

    /**
     * 時段 1:早 2:午 3:晚
     */
    private Integer activityTimeSlot;

    /**
     * 數量
     */
    private Integer ticketQuantity;

    /**
     * 總金額
     */
    private BigDecimal ticketPrice;

    /**
     * 場館名稱
     */
    private String venueName;

    /**
     * 座位代號
     */
    private String seatName;

    /**
     * 活動區域價格
     */
    private BigDecimal activityAreaPrice;

}
