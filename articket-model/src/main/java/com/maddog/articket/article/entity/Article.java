package com.maddog.articket.article.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 文章 DO
 */
@Getter
@Setter
public class Article implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 文章 ID
	 */
	private Integer articleId;

	/**
	 * 文章類別
	 */
	private String articleCategory;

	/**
	 * 文章標題
	 */
	private String articleTitle;

	/**
	 * 會員 ID
	 */
	private Integer memberId;

	/**
	 * 文章內容
	 */
	private String articleContent;

	/**
	 * 各板 ID
	 */
	private Integer boardId;

	/**
	 * 文章狀態 0.不顯示 1.顯示
	 */
	private Integer articleStatus = 1;

	/**
	 * 文章建立時間
	 */
	private Date  articleCreateTime;

}
