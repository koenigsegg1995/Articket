package com.maddog.articket.activitypicture.service.pri;

import com.maddog.articket.activitypicture.entity.ActivityPicture;

import java.util.Set;

/**
 * 活動圖片 Service Interface
 */
public interface ActivityPictureService {

	/**
	 * 新增活動圖片
	 *
	 * @param activityPicture
	 * 		 	ActivityPicture
	 * @return 成功筆數
	 * 		 	int
	 */
	int insertActivityPicture(ActivityPicture activityPicture);

    /**
	 * 依活動圖片 ID 查詢
	 *
	 * @param activityPictureId
	 * 		 	Integer
	 * @return 活動圖片
	 * 		 	ActivityPicture
	 */
	ActivityPicture getOneActivityPicture(Integer activityPictureId);

	/**
	 * 刪除圖片
	 *
	 * @param activityPictureId
	 * 		 	Set<Integer>
	 * @return 成功筆數
	 * 		 	int
	 */
	int delete(Set<Integer> activityPictureId);

}
