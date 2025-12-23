package com.maddog.articket.activity.dto;

public class ActivityDisplayForView {

    /**
     * 編號
     */
    private Integer activityId;

    /**
     * 名稱
     */
    private String activityName;

    /**
     * 場館
     */
    private String venueName;

    /**
     * 類型
     */
    private String activityTag;

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
     * @return venueName
     *          String
     */
    public String getVenueName() {
        return venueName;
    }

    /**
     *
     * @param venueName
     *          String
     */
    public void setVenueName(String venueName) {
        this.venueName = venueName;
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

}
