package com.maddog.articket.activity.dto;

import java.util.Date;
import java.util.List;

public class ActivityForView {

    /**
     * 活動 ID
     */
    private Integer activityId;

    /**
     * 活動名稱
     */
    private String activityName;

    /**
     * 排程時間
     */
    private Date activityPostTime;

    /**
     * 場館名稱
     */
    private String venueName;

    /**
     * 圖片 ID 清單
     */
    private List<Integer> activityPictureIds;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Date getActivityPostTime() {
        return activityPostTime;
    }

    public void setActivityPostTime(Date activityPostTime) {
        this.activityPostTime = activityPostTime;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public List<Integer> getActivityPictureIds() {
        return activityPictureIds;
    }

    public void setActivityPictureIds(List<Integer> activityPictureIds) {
        this.activityPictureIds = activityPictureIds;
    }
}
