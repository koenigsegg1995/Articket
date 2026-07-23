package com.maddog.articket.article.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 文章 VO
 */
@Getter
@Setter
public class ArticleForView implements Serializable {

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
     * 文章建立時間
     */
    private Date articleCreateTime;

    /**
     * 文章各板 ID
     */
    private Integer boardId;

    /**
     * 文章各板名稱
     */
    private String boardName;

    /**
     * 會員暱稱
     */
    private String memberNickname;

}
