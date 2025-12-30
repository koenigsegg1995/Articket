package com.maddog.articket.activitytimeslot.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 活動時段 DO
 */
@Getter
@Setter
public class ActivityTimeSlot implements Serializable {

    /**
     * 序列化
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 時段 ID
     */
	private Integer activityTimeSlotId;

    /**
     * 活動 ID
     */
	private Integer activityId;

    /**
     * 日期
     */
	private Date activityTimeSlotDate;

    /**
     * 時段 1:早 2:午 3:晚
     */
	private Integer activityTimeSlot;

    /**
     * 時段剩餘座位數
     */
	private Integer activityTimeSlotSeatAmount;

}
