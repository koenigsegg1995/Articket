package com.maddog.articket.activity.service.pri;

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
	 * 查詢 (單一)
	 */
	Activity getOneActivity(Integer activityID);

	/**
	 * 查詢 (全部)
	 */
	List<Activity> getAll();

	/**
	 * 查詢 (複合)
	 */
	List<Activity> getAll(Map<String, String[]> map);
	
}
