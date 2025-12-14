package com.maddog.articket.activitypicture.service.impl;

import com.maddog.articket.activitypicture.dao.ActivityPictureRepository;
import com.maddog.articket.activitypicture.entity.ActivityPicture;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("activityPictureService")
public class ActivityPictureService {

	@Autowired
	ActivityPictureRepository repository;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//新增
	public void addActivityPicture(ActivityPicture activityPicture) {
		repository.save(activityPicture);
	}
	
	//修改
	public void updateActivityPicture(ActivityPicture activityPicture) {
		repository.save(activityPicture);
	}
	
	//刪除
	public void deleteActivityPicture(Integer activityPictureID) {
		if(repository.existsById(activityPictureID)) {
			repository.deleteByActivityPictureID(activityPictureID);
		}
	}
	
	//查詢 (單一)
	public ActivityPicture getOneActivityPicture(Integer activityPictureID) {
		Optional<ActivityPicture> optional = repository.findById(activityPictureID);
		
		return optional.orElse(null);
	}
	
	//查詢 (全部)
	public List<ActivityPicture> getAll(){
		return repository.findAll();
	}
	
//	//查詢 (複合)
//	public List<ActivityPicture> getAll(Map<String, String[]> map) {
//		return HibernateUtil_CompositeQuery_ActivityPicture.getAllC(map,sessionFactory.openSession());
//	}
	
}
