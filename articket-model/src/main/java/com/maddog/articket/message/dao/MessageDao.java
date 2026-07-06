package com.maddog.articket.message.dao;

import com.maddog.articket.message.dto.MessageForView;
import com.maddog.articket.message.entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文章留言 DAO
 */
@Mapper
public interface MessageDao {

	/**
	 * 留言
	 *
	 * @param message
	 * 			留言
	 * @return 成功筆數
	 */
	int insert(Message message);

	/**
	 * 更新
	 *
	 * @param message
	 * 			留言
	 * @return 成功筆數
	 */
	int update(Message message);

	/**
	 * 刪除
	 *
	 * @param messageId
	 * 			留言 ID
	 * @return 成功筆數
	 */
	int deleteById(Integer messageId);

	/**
	 * 依留言 ID 查詢
	 *
	 * @param messageId
	 * 			留言 ID
	 * @return 留言
	 */
	Message findById(Integer messageId);

	/**
	 * 查詢全部
	 *
	 * @return 全部留言清單
	 */
	List<Message> findAll();

	/**
	 * 依文章 ID 查詢留言清單
	 *
	 * @param articleId
	 * 			文章 ID
	 * @return 留言清單
	 */
	List<MessageForView> getMessagesByArticleId(Integer articleId);

}
