package com.maddog.articket.activity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 廠商後臺活動更新 DTO
 */
@Setter
@Getter
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

}
