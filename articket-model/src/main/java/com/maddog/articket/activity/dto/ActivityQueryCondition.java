package com.maddog.articket.activity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 活動查詢條件 DTO
 */
@Setter
@Getter
public class ActivityQueryCondition {

    /**
     * 場館 ID
     */
    private List<Integer> venueIdList;

    /**
     * 類型標籤
     */
    private List<String> activityTagList;

}
