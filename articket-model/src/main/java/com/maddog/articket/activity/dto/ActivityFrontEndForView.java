package com.maddog.articket.activity.dto;

import com.maddog.articket.activitytimeslot.entity.ActivityTimeSlot;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 前臺活動 VO
 */
@Setter
@Getter
public class ActivityFrontEndForView {

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
     * 活動內容
     */
    private String activityContent;

    /**
     * 活動標籤
     */
    private String activityTag;

    /**
     * 場館名稱
     */
    private String venueName;

    /**
     * 廠商名稱
     */
    private String partnerName;

    /**
     * 活動時段清單
     */
    private List<ActivityTimeSlot> activityTimeSlots;

    /**
     * 圖片 ID 清單
     */
    private List<Integer> activityPictureIds;

}
