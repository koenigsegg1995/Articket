package com.maddog.articket.activitypicture.service.impl;

import com.maddog.articket.activitypicture.dao.ActivityPictureDao;
import com.maddog.articket.activitypicture.entity.ActivityPicture;
import com.maddog.articket.activitypicture.service.pri.ActivityPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * 活動圖片 Service Implementation
 */
@Service("activityPictureService")
public class ActivityPictureServiceImpl implements ActivityPictureService {

	/**
	 * 活動圖片 DAO
	 */
	@Autowired
	private ActivityPictureDao activityPictureDao;

	/**
	 * 新增活動圖片
	 *
	 * @param activityPicture
	 * 		 	ActivityPicture
	 * @return 成功筆數
	 * 		 	int
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int insertActivityPicture(ActivityPicture activityPicture){
		return activityPictureDao.insert(activityPicture);
	}

	/**
	 * 依活動圖片 ID 查詢
	 *
	 * @param activityPictureID
	 * 		 	Integer
	 * @return 活動圖片
	 * 		 	ActivityPicture
	 */
	@Override
	@Transactional(readOnly=true)
	public ActivityPicture getOneActivityPicture(Integer activityPictureID) {
		return activityPictureDao.findById(activityPictureID);
	}

	/**
	 * 刪除圖片
	 *
	 * @param activityPictureId
	 * 		 	Set<Integer>
	 * @return 成功筆數
	 * 		 	int
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int delete(Set<Integer> activityPictureId){
		return activityPictureDao.delete(activityPictureId);
	}
	
}
