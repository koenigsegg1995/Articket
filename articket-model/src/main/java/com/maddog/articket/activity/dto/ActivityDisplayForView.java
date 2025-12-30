package com.maddog.articket.activity.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 廠商後臺活動資訊 VO
 */
@Setter
@Getter
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

}
