package com.maddog.articket.activitypicture.service.pri;

import com.maddog.articket.activitypicture.entity.ActivityPicture;

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
	 * @param activityPictureID
	 * 		 	Integer
	 * @return 活動圖片
	 * 		 	ActivityPicture
	 */
	ActivityPicture getOneActivityPicture(Integer activityPictureID);

}
