package com.maddog.articket.activitypicture.service.impl;

import com.maddog.articket.activitypicture.dao.ActivityPictureDao;
import com.maddog.articket.activitypicture.entity.ActivityPicture;
import com.maddog.articket.activitypicture.service.pri.ActivityPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("activityPictureService")
public class ActivityPictureServiceImpl implements ActivityPictureService {

	@Autowired
	ActivityPictureDao activityPictureDao;

	/**
	 * 依活動圖片 ID 查詢
	 */
	@Override
	@Transactional(readOnly=true)
	public ActivityPicture getOneActivityPicture(Integer activityPictureID) {
		return activityPictureDao.findById(activityPictureID);
	}
	
}
