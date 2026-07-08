package com.maddog.articket.bookticket.dto;

import com.maddog.articket.activitytimeslot.entity.ActivityTimeSlot;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 票券結帳 VO
 */
@Getter
@Setter
public class BookTicketForView {

    /**
     * 活動名稱
     */
    private String activityName;

    /**
     * 活動時段
     */
    private ActivityTimeSlot activityTimeSlot;

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

    /**
     * 座位狀態 ID (僅供 TicketController comfirm() 取用)
     */
    private Integer seatStatusId;

    /**
     * 活動區域價格 ID (僅供 TicketController comfirm() 取用)
     */
    private Integer activityAreaPriceId;

}
