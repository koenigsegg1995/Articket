package com.maddog.articket.activitypicture.service.pri;

import com.maddog.articket.activitypicture.entity.ActivityPicture;

public interface ActivityPictureService {

    /**
	 * 依活動圖片 ID 查詢
	 */
	ActivityPicture getOneActivityPicture(Integer activityPictureID);

}
