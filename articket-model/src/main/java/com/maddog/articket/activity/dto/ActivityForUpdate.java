package com.maddog.articket.activity.dto;

import java.util.Date;

public class ActivityForUpdate {

    /**
     * 活動 ID
     */
    private Integer activityId;

    /**
     * 活動名稱
     */
    private String activityName;

    /**
     * 活動
     */
    private String activityTag;

    /**
     * 排程時間
     */
    private Date activityPostTime;

    /**
     * 起售日
     */
    private Date sellTime;

    /**
     * 活動介紹
     */
    private String activityContent;

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
     * @return activityName
     *          String
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     *
     * @param activityName
     *          String
     */
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    /**
     *
     * @return activityTag
     *          String
     */
    public String getActivityTag() {
        return activityTag;
    }

    /**
     *
     * @param activityTag
     *          String
     */
    public void setActivityTag(String activityTag) {
        this.activityTag = activityTag;
    }

    /**
     *
     * @return activityPostTime
     *          Date
     */
    public Date getActivityPostTime() {
        return activityPostTime;
    }

    /**
     *
     * @param activityPostTime
     *          Date
     */
    public void setActivityPostTime(Date activityPostTime) {
        this.activityPostTime = activityPostTime;
    }

    /**
     *
     * @return sellTime
     *          Date
     */
    public Date getSellTime() {
        return sellTime;
    }

    /**
     *
     * @param sellTime
     *          Date
     */
    public void setSellTime(Date sellTime) {
        this.sellTime = sellTime;
    }

    /**
     *
     * @return activityContent
     *          String
     */
    public String getActivityContent() {
        return activityContent;
    }

    /**
     *
     * @param activityContent
     *          String
     */
    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

}
