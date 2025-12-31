package com.maddog.articket.article.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 文章查詢條件 DTO
 */
@Getter
@Setter
public class ArticleQueryCondition {

    /**
     * 文章標題
     */
    private String articleTitle;

    /**
     * 文章類別
     */
    private String articleCategory;

    /**
     * 討論板 ID
     */
    private Integer boardId;

}
