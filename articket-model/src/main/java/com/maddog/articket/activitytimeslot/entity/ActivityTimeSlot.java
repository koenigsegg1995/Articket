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
     * 時段ID
     */
	private Integer activityTimeSlotID;

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

    public Integer getActivityTimeSlotID() {
        return activityTimeSlotID;
    }

    public void setActivityTimeSlotID(Integer activityTimeSlotID) {
        this.activityTimeSlotID = activityTimeSlotID;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Date getActivityTimeSlotDate() {
        return activityTimeSlotDate;
    }

    public void setActivityTimeSlotDate(Date activityTimeSlotDate) {
        this.activityTimeSlotDate = activityTimeSlotDate;
    }

    public Integer getActivityTimeSlot() {
        return activityTimeSlot;
    }

    public void setActivityTimeSlot(Integer activityTimeSlot) {
        this.activityTimeSlot = activityTimeSlot;
    }

    public Integer getActivityTimeSlotSeatAmount() {
        return activityTimeSlotSeatAmount;
    }

    public void setActivityTimeSlotSeatAmount(Integer activityTimeSlotSeatAmount) {
        this.activityTimeSlotSeatAmount = activityTimeSlotSeatAmount;
    }

}
