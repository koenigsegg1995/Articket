package com.maddog.articket.message.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 文章留言
 */
@Getter
@Setter
public class Message implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 留言 ID
	 */
	private Integer messageId;

	/**
	 * 會員 ID
	 */
	private Integer memberId;

	/**
	 * 文章 ID
	 */
	private Integer articleId;

	/**
	 * 留言內容
	 */
	private String messageContent;

	/**
	 * 留言狀態 0.不顯示 1.顯示
	 */
	private Integer messageStatus = 1;

	/**
	 * 留言時間
	 */
	private Date  messageCreateTime;

}