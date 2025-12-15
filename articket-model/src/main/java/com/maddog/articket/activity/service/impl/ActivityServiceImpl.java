package com.maddog.articket.activity.service.impl;

import com.maddog.articket.activity.dao.ActivityDao;
import com.maddog.articket.activity.dto.ActivityForView;
import com.maddog.articket.activity.dto.ActivityQueryCondition;
import com.maddog.articket.hibernate.util.compositequery.HibernateUtil_CompositeQuery_Activity;
import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.activity.service.pri.ActivityService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	ActivityDao activityDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//新增
	public void addActivity(Activity activity) {
		// activity.insert(activity);
	}

	//修改
	public void updateActivity(Activity activity) {
		// activity.update(activity);
	}

	//刪除
	public void deleteActivity(Integer activityID) {
		// activity.delete(activityID);
	}

    /**
     * 依 ID 查詢活動
     *
     * @param activityId
     *          Integer
     * @return 活動
     *          Activity
     */
    @Override
    @Transactional(readOnly = true)
	public Activity getOneActivity(Integer activityId) {
		 return activityDao.findById(activityId);
	}

    /**
     * 查詢所有活動
     *
     * @return 活動清單
     *          List<Activity>
     */
    @Override
    @Transactional(readOnly = true)
	public List<Activity> getAll(){
		return activityDao.findAll();
	}

    /**
     * 依條件查詢
     *
     * @param condition
     *          ActivityQueryCondition
     * @return 活動清單
     *          List<ActivityForView>
     */
	public List<ActivityForView> findByCondition(ActivityQueryCondition condition) {
        // 依條件由 DAO 查詢
        List<ActivityForView> viewList = activityDao.findByCondition(condition);

        return activityDao.findByCondition(condition);
	}
	
}
