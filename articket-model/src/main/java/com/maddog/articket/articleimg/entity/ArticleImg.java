package com.maddog.articket.articleimg.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * 文章圖片 DO
 */
@Getter
@Setter
public class ArticleImg implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 文章圖片 ID
	 */
	private Integer articleImgId;

	/**
	 * 文章 ID
	 */
	private Integer articleId;

	/**
	 * 圖片
	 */
	private byte[] articlePic;

	/**
	 * 圖片建立時間
	 */
	private Date  articleImgCreateTime;
	
	@Override
	public String toString() {
		return "ArticleImg [articleImgID=" + articleImgId + ", articleID=" + articleId + ", articlePic="
				+ Arrays.toString(articlePic) + ", articleImgCreateTime=" + articleImgCreateTime + "]";
	}

}
