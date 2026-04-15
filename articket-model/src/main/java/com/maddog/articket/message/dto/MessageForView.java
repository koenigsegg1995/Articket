package com.maddog.articket.message.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class MessageForView implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 留言 ID
     */
    private Integer messageId;

    /**
     * 文章 ID
     */
    private Integer articleId;

    /**
     * 會員 ID
     */
    private Integer memberId;

    /**
     * 會員名稱
     */
    private String memberName;

    /**
     * 留言內容
     */
    private String messageContent;

    /**
     * 留言時間
     */
    private Date messageCreateTime;

}
