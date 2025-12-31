package com.maddog.articket.announcement.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Announcement implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 公告 ID
	 */
	private Integer announcementId;

	/**
	 * 管理員 ID
	 */
	private Integer administratorId;

	/**
	 * 標題
	 */
	private String announcementTitle;

	/**
	 * 內容
	 */
	private String announcementContent;

	/**
	 * 狀態 0:隱藏 1:正常顯示 2:置頂
	 */
	private Integer announcementStatus;

	/**
	 * 發布時間
	 */
	private Date announcementCreateTime;

}
