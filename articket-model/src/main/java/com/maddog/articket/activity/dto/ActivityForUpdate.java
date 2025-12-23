package com.maddog.articket.activity.dto;

public class ActivityForUpdate {

    /**
     * 場地申請資料 ID
     */
    private Integer venueRentalId;

    /**
     * 活動名稱
     */
    private String activityName;

    /**
     * 活動
     */
    private String activityTag;

    /**
     * 活動介紹
     */
    private String activityContent;

    /**
     *
     * @return venueRentalId
     *          Integer
     */
    public Integer getVenueRentalId() {
        return venueRentalId;
    }

    /**
     *
     * @param venueRentalId
     *          Integer
     */
    public void setVenueRentalId(Integer venueRentalId) {
        this.venueRentalId = venueRentalId;
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
