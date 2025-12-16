package com.maddog.articket.activity.service.pri;

import com.maddog.articket.activity.dto.ActivityForView;
import com.maddog.articket.activity.dto.ActivityQueryCondition;
import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.activitytimeslot.entity.ActivityTimeSlot;

import java.util.List;

public interface ActivityService {

	/**
	 * 新增
	 */
	void addActivity(Activity activity);

	/**
	 * 修改
	 */
	void updateActivity(Activity activity);

	/**
	 * 刪除
	 */
	void deleteActivity(Integer activityID);

    /**
     * 依 ID 查詢活動 (deprecated)
     *
     * @param activityId
     *          Integer
     * @return 活動
     *          Activity
     */
	Activity getOneActivity(Integer activityId);

    /**
     * 依 ID 查詢活動 VO
     *
     * @param activityId
     *          Integer
     * @return 活動
     *          ActivityForView
     */
    ActivityForView findByIdForView(Integer activityId);

    /**
     * 查詢所有活動
     *
     * @return 活動清單
     *          List<Activity>
     */
	List<Activity> getAll();

	/**
	 * 依條件查詢
     *
     * @param condition
     *          ActivityQueryCondition
     * @return 活動清單
     *          List<ActivityForView>
	 */
	List<ActivityForView> findByConditionForView(ActivityQueryCondition condition);

	/**
	 * 依活動 ID 查詢活動圖片 ID 清單
	 *
	 * @param activityId
	 * 			Integer
	 * @return 活動圖片 ID 清單
	 * 			List<Integer>
	 */
	List<Integer> findActivityPictureIdByActivityId(Integer activityId);

    /**
     * 依活動 ID 查詢活動時段清單
     *
     * @param activityId
     * 			Integer
     * @return 活動時段清單
     * 			List<ActivityTimeSlot>
     */
    List<ActivityTimeSlot> findActivityTimeSlotByActivityId(Integer activityId);
	
}
