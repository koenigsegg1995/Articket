package com.maddog.articket.activitytimeslot.service.pri;

import com.maddog.articket.activitytimeslot.entity.ActivityTimeSlot;

/**
 * 活動時段 Service interface
 */
public interface ActivityTimeSlotService {

	/**
	 * 依時段 ID 查詢
	 *
	 * @param activityTimeSlotId
	 *          Integer
	 * @return 活動時段
	 *          ActivityTimeSlot
	 */
	ActivityTimeSlot getActivityTimeSlotById(Integer activityTimeSlotId);

}
