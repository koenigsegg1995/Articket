package com.maddog.articket.activitytimeslot.service.impl;

import com.maddog.articket.activitytimeslot.dao.ActivityTimeSlotDao;
import com.maddog.articket.activitytimeslot.entity.ActivityTimeSlot;
import com.maddog.articket.activitytimeslot.service.pri.ActivityTimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 活動時段 Implementation
 */
@Service("activityTimeSlotService")
public class ActivityTimeSlotServiceImpl implements ActivityTimeSlotService {

	/**
	 * 活動時段 DAO
	 */
	@Autowired
	private ActivityTimeSlotDao activityTimeSlotDao;

	/**
	 * 依時段 ID 查詢
	 *
	 * @param activityTimeSlotId
	 *          Integer
	 * @return 活動時段
	 *          ActivityTimeSlot
	 */
	@Override
	@Transactional(readOnly = true)
	public ActivityTimeSlot getActivityTimeSlotById(Integer activityTimeSlotId) {
		return activityTimeSlotDao.findById(activityTimeSlotId);
    }

}
