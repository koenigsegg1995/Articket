package com.maddog.articket.ticket.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 票券 DTO
 */
@Getter
@Setter
public class TicketForView {

    /**
     * 票券 ID
     */
    private Integer ticketId;

    /**
     * 活動名稱
     */
    private String activityName;

    /**
     * 日期
     */
    private Date activityTimeSlotDate;

    /**
     * 行
     */
    private Integer seatRow;

    /**
     * 號
     */
    private Integer seatNumber;

    /**
     * 活動區域價格
     */
    private BigDecimal activityAreaPrice;

}
