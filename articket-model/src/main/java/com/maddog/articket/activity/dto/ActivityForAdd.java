package com.maddog.articket.activity.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 廠商後臺新增活動 DTO
 */
@Setter
@Getter
public class ActivityForAdd {

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

}
