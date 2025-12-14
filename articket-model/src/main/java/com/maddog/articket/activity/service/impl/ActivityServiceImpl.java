package com.maddog.articket.activity.service.impl;

import com.maddog.articket.activity.dao.ActivityDao;
import com.maddog.articket.hibernate.util.compositequery.HibernateUtil_CompositeQuery_Activity;
import com.maddog.articket.activity.dao.ActivityRepository;
import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.activity.service.pri.ActivityService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	ActivityRepository repository;

	@Autowired
	ActivityDao activityDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//新增
	public void addActivity(Activity activity) {
		repository.save(activity);
		// activity.insert(activity);
	}
	
	//修改
	public void updateActivity(Activity activity) {
		repository.save(activity);
		// activity.update(activity);
	}
	
	//刪除
	public void deleteActivity(Integer activityID) {
		if(repository.existsById(activityID)) {
			repository.deleteByActivityID(activityID);
		}
		// activity.delete(activityID);
	}
	
	//查詢 (單一)
	public Activity getOneActivity(Integer activityID) {
		Optional<Activity> optional = repository.findById(activityID);
		
		return optional.orElse(null);
		// return activity.findById(activityID);
	}
	
	//查詢 (全部)
	public List<Activity> getAll(){
		return repository.findAll();
//		return activity.findAll();
	}
	
	//查詢 (複合)
	public List<Activity> getAll(Map<String, String[]> map) {
		return HibernateUtil_CompositeQuery_Activity.getAllC(map,sessionFactory.openSession());
//		return activity.findByCondition(condition);
	}
	
}
