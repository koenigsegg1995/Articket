package com.maddog.articket.news.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 最新消息 DO
 */
@Getter
@Setter
public class News implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 消息ID
	 */
	private Integer newsId;

	/**
	 * 管理員ID
	 */
	private Integer administratorId;

	/**
	 * 標題
	 */
	private String newsTitle;

	/**
	 * 內容
	 */
	private String newsContent;

	/**
	 * 狀態 0:隱藏 1:正常顯示 2:置頂
	 */
	private Integer newsStatus;

	/**
	 * 發布時間
	 */
	private Date newsCreateTime;

}