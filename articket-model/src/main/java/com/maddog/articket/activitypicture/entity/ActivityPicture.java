package com.maddog.articket.activitypicture.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 活動圖片 DO
 */
@Getter
@Setter
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

}
