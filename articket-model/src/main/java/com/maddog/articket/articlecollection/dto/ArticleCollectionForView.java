package com.maddog.articket.articlecollection.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 文章收藏 - 前端顯示 VO
 */
@Getter
@Setter
public class ArticleCollectionForView implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer articleId;

    private Date collectionCreateTime;

    private String articleCategory;

    private String articleTitle;

    private String memberName;

    private String boardName;

}
