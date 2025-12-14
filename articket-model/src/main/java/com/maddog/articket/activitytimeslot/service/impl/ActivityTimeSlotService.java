package com.maddog.articket.activitytimeslot.service.impl;

import com.maddog.articket.activitytimeslot.dao.ActivityTimeSlotRepository;
import com.maddog.articket.activitytimeslot.entity.ActivityTimeSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ActivityTimeSlotService {

	@Autowired
	private ActivityTimeSlotRepository activityTimeSlotRepository;

	public ActivityTimeSlot getActivityTimeSlotById(Integer id) {
        return activityTimeSlotRepository.findById(id).orElseThrow(() -> new RuntimeException("ActivityTimeSlot not found with id: " + id));
    }

	public Optional<ActivityTimeSlot> findActivityTimeSlotById(Integer id) {
		return activityTimeSlotRepository.findById(id);
	}

	public ActivityTimeSlot saveActivityTimeSlot(ActivityTimeSlot activityTimeSlot) {
		return activityTimeSlotRepository.save(activityTimeSlot);
	}

	public void deleteActivityTimeSlot(Integer id) {
		activityTimeSlotRepository.deleteById(id);
	}

	public ActivityTimeSlot getActivityTimeSlotWithActivity(Integer id) {
		ActivityTimeSlot activityTimeSlot = getActivityTimeSlotById(id);
		if (activityTimeSlot.getActivity() == null) {
			throw new RuntimeException("Activity not found for ActivityTimeSlot with id: " + id);
		}
		return activityTimeSlot;
	}

}
