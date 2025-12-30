package com.maddog.articket.activitypicture.dao;

import com.maddog.articket.activitypicture.entity.ActivityPicture;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * 活動圖片 DAO
 */
@Mapper
public interface ActivityPictureDao {

	/**
	 * 新增活動圖片
	 *
	 * @param activityPicture
	 * 			ActivityPicture
	 * @return 成功筆數
	 * 			int
	 */
	int insert(ActivityPicture activityPicture);

	/**
	 * 依活動圖片 ID 查詢活動圖片
	 *
	 * @param activityPictureId
	 * 			Integer
	 * @return 活動圖片
	 * 			ActivityPicture
	 */
	ActivityPicture findById(Integer activityPictureId);

	/**
	 * 刪除活動圖片
	 *
	 * @param activityPictureId
	 * 		 	Set<Integer>
	 * @return 成功筆數
	 * 		 	int
	 */
	int delete(Set<Integer> activityPictureId);

}
