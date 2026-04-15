package com.maddog.articket.message.service.pri;

import com.maddog.articket.message.dto.MessageForView;
import com.maddog.articket.message.entity.Message;

import java.util.List;

/**
 * 文章留言 Service
 */
public interface MessageService {

	/**
	 * 留言
	 *
	 * @param message
	 * 			留言
	 * @return 新增後的留言
	 */
	Message addMessage(Message message);

	/**
	 * 更新
	 *
	 * @param message
	 * 			留言
	 * @return 更新後的留言
	 */
	Message updateMessage(Message message);

	/**
	 * 依 ID 刪除
	 *
	 * @param messageId
	 * 			留言 ID
	 * @return 成功筆數
	 */
	int deleteMessage(Integer messageId);
	/**
	 * 依 ID 查詢
	 *
	 * @param messageId
	 * 			留言 ID
	 * @return 留言
	 */
	Message getOneMessage(Integer messageId);

	/**
	 * 查詢全部
	 *
	 * @return 全部留言清單
	 */
	List<Message> getAll();

	/**
	 * 依文章 ID 查詢留言清單
	 *
	 * @param articleId
	 * 			文章 ID
	 * @return 留言清單
	 */
    List<MessageForView> getMessagesByArticleId(Integer articleId);

}