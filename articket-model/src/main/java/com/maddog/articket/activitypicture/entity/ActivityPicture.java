package com.maddog.articket.activitypicture.entity;

import java.io.Serial;
import java.io.Serializable;

/**
 * 活動圖片 DO
 */
public class ActivityPicture implements Serializable {

	/**
	 * 序列化
	 */
	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 活動圖片 ID
	 */
	private Integer activityPictureId;

	/**
	 * 活動 ID
	 */
	private Integer activityId;

	/**
	 * 活動圖片
	 */
	private byte[] activityPicture;

	/**
	 *
	 * @return activityPictureId
	 * 			Integer
	 */
	public Integer getActivityPictureId() {
		return activityPictureId;
	}

	/**
	 *
	 * @param activityPictureId
	 * 			Integer
	 */
	public void setActivityPictureId(Integer activityPictureId) {
		this.activityPictureId = activityPictureId;
	}

	/**
	 *
	 * @return activityId
	 * 			Integer
	 */
	public Integer getActivityId() {
		return activityId;
	}

	/**
	 *
	 * @param activityId
	 * 			Integer
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	/**
	 *
	 * @return activityPicture
	 * 			byte[]
	 */
	public byte[] getActivityPicture() {
		return activityPicture;
	}

	/**
	 *
	 * @param activityPicture
	 * 			byte[]
	 */
	public void setActivityPicture(byte[] activityPicture) {
		this.activityPicture = activityPicture;
	}

}
