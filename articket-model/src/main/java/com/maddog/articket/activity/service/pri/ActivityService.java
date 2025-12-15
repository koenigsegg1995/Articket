package com.maddog.articket.activity.service.pri;

import com.maddog.articket.activity.dto.ActivityForView;
import com.maddog.articket.activity.dto.ActivityQueryCondition;
import com.maddog.articket.activity.entity.Activity;

import java.util.List;
import java.util.Map;

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
     * 依 ID 查詢活動
     *
     * @param activityId
     *          Integer
     * @return 活動
     *          Activity
     */
	Activity getOneActivity(Integer activityId);

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
	List<ActivityForView> findByCondition(ActivityQueryCondition condition);
	
}
