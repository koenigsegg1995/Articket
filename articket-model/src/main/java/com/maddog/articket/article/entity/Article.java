package com.maddog.articket.article.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.Date;

@Getter
@Setter
public class Article implements java.io.Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	
	private Integer articleId;

	private String articleCategory;

	private String articleTitle;

	private Integer memberId;

	private String articleContent;

	private Integer boardId;

	private Integer articleStatus = 1;

	private Date  articleCreateTime;

}
