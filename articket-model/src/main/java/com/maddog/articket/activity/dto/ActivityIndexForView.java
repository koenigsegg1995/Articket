package com.maddog.articket.activity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * index.html 顯示用 VO
 */
@Getter
@Setter
public class ActivityIndexForView {

    /**
     * 活動 ID
     */
    private Integer activityId;

    /**
     * 活動圖片 ID 清單
     */
    private List<Integer> activityPictureIdList;

    /**
     * 活動名稱
     */
    private String activityName;

    /**
     * 起售日
     */
    private Date sellTime;

    /**
     * 場館名稱
     */
    private String venueName;

}
