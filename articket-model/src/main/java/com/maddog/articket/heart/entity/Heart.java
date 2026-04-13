package com.maddog.articket.heart.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 文章點讚 DO
 */
@Getter
@Setter
public class Heart implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 點讚 ID
	 */
	private Integer heartId;

	/**
	 * 會員 ID
	 */
	private Integer memberId;

	/**
	 * 文章 ID
	 */
	private Integer articleId;

	/**
	 * 點讚時間
	 */
	private Date heartCreateTime;

}