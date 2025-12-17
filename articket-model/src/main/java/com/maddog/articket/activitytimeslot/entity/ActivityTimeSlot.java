package com.maddog.articket.activitytimeslot.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 活動時段
 */
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

    /**
     *
     * @return activityTimeSlotId
     *          Integer
     */
    public Integer getActivityTimeSlotId() {
        return activityTimeSlotId;
    }

    /**
     *
     * @param activityTimeSlotId
     *          Integer
     */
    public void setActivityTimeSlotId(Integer activityTimeSlotId) {
        this.activityTimeSlotId = activityTimeSlotId;
    }

    /**
     *
     * @return activityId
     *          Integer
     */
    public Integer getActivityId() {
        return activityId;
    }

    /**
     *
     * @param activityId
     *          Integer
     */
    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    /**
     *
     * @return activityTimeSlotDate
     *          Date
     */
    public Date getActivityTimeSlotDate() {
        return activityTimeSlotDate;
    }

    /**
     *
     * @param activityTimeSlotDate
     *          Date
     */
    public void setActivityTimeSlotDate(Date activityTimeSlotDate) {
        this.activityTimeSlotDate = activityTimeSlotDate;
    }

    /**
     *
     * @return activityTimeSlot
     *          Integer
     */
    public Integer getActivityTimeSlot() {
        return activityTimeSlot;
    }

    /**
     *
     * @param activityTimeSlot
     *          Integer
     */
    public void setActivityTimeSlot(Integer activityTimeSlot) {
        this.activityTimeSlot = activityTimeSlot;
    }

    /**
     *
     * @return activityTimeSlotSeatAmount
     *          Integer
     */
    public Integer getActivityTimeSlotSeatAmount() {
        return activityTimeSlotSeatAmount;
    }

    /**
     *
     * @param activityTimeSlotSeatAmount
     *          Integer
     */
    public void setActivityTimeSlotSeatAmount(Integer activityTimeSlotSeatAmount) {
        this.activityTimeSlotSeatAmount = activityTimeSlotSeatAmount;
    }

}
