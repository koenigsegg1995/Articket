package com.maddog.articket.articlecollection.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 文章收藏 DO
 */
@Getter
@Setter
public class ArticleCollection implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 文章收藏 ID
	 */
	private Integer articleCollectionId;

	/**
	 * 會員 ID
	 */
	private Integer memberId;

	/**
	 * 文章 ID
	 */
	private Integer articleId;

	/**
	 * 收藏時間
	 */
	private Date collectionCreateTime;

}
