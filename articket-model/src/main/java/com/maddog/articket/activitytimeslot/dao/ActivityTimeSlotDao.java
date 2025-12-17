package com.maddog.articket.activitytimeslot.dao;

import com.maddog.articket.activitytimeslot.entity.ActivityTimeSlot;
import org.apache.ibatis.annotations.Mapper;

/**
 * 活動時段 DAO
 */
@Mapper
public interface ActivityTimeSlotDao {

    /**
     * 依時段 ID 查詢
     *
     * @param activityTimeSlotId
     *          Integer
     * @return 活動時段
     *          ActivityTimeSlot
     */
    ActivityTimeSlot findById(Integer activityTimeSlotId);

}
