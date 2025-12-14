package com.maddog.articket.activity.dto;

/**
 * 封裝活動查詢條件
 */
public class ActivityQueryCondition {

    /**
     * 場館ID
     */
    private Integer venueId;

    /**
     * 類型標籤
     */
    private String activityTag;

    /**
     *
     * @return venueId
     *          Integer
     */
    public Integer getVenueId() {
        return venueId;
    }

    /**
     *
     * @param venueId
     *          Integer
     */
    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
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
