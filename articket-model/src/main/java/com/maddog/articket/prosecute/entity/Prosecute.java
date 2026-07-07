package com.maddog.articket.prosecute.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Prosecute implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 檢舉ID
	 */
	private Integer prosecuteId;

	/**
	 * 檢舉人ID
	 */
	private Integer memberId;

	/**
	 * 被檢舉文章ID
	 */
	private Integer articleId;

	/**
	 * 被檢舉原因
	 */
	private String prosecuteReason;

	/**
	 * 被檢舉留言ID
	 */
	private Integer messageId;

	/**
	 * 檢舉日期
	 */
	private Date prosecuteCreateTime;

	/**
	 * 檢舉狀態 0: 正常 1: 不顯示
	 */
	private Integer prosecuteStatus = 1;

}