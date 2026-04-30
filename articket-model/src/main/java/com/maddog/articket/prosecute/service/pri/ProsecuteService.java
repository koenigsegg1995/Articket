package com.maddog.articket.prosecute.service.pri;

import com.maddog.articket.prosecute.entity.Prosecute;

/**
 * 檢舉 Service Interface
 */
public interface ProsecuteService {

	void prosecuteContent(Prosecute prosecute);

	// 被檢舉的是文章
	boolean isArticleReported(Integer articleID);

	// 被檢舉的是留言
	boolean isMessageReported(Integer messageID);

}