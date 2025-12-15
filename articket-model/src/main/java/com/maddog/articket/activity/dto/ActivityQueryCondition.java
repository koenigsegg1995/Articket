package com.maddog.articket.activity.dto;

import java.util.List;

/**
 * 封裝活動查詢條件
 */
public class ActivityQueryCondition {

    /**
     * 場館ID
     */
    private List<Integer> venueIdList;

    /**
     * 類型標籤
     */
    private List<String> activityTagList;

    /**
     *
     * @return venueIdList
     *          List<Integer>
     */
    public List<Integer> getVenueIdList() {
        return venueIdList;
    }

    /**
     *
     * @param venueIdList
     *          List<Integer>
     */
    public void setVenueIdList(List<Integer> venueIdList) {
        this.venueIdList = venueIdList;
    }

    /**
     *
     * @return activityTagList
     *          List<String>
     */
    public List<String> getActivityTagList() {
        return activityTagList;
    }

    /**
     *
     * @param activityTagList
     *          List<String>
     */
    public void setActivityTagList(List<String> activityTagList) {
        this.activityTagList = activityTagList;
    }

}
