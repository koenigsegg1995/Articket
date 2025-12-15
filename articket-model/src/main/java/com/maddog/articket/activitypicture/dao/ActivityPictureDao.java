package com.maddog.articket.activitypicture.dao;

import com.maddog.articket.activitypicture.entity.ActivityPicture;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityPictureDao {

	/**
	 * 依活動圖片 ID 查詢活動圖片
	 *
	 * @param activityPictureId
	 * 			Integer
	 * @return 活動圖片
	 * 			ActivityPicture
	 */
	ActivityPicture findById(Integer activityPictureId);

}
