package com.maddog.articket.message.service.impl;

import com.maddog.articket.message.dao.MessageDao;
import com.maddog.articket.message.dto.MessageForView;
import com.maddog.articket.message.entity.Message;
import com.maddog.articket.message.service.pri.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文章留言 Service
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {

	/**
	 * 文章留言 DAO
	 */
	@Autowired
	private MessageDao messageDao;

	/**
	 * 留言
	 *
	 * @param message
	 * 			留言
	 * @return 新增後的留言
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Message addMessage(Message message) {
		// 在 insert 之前處理換行符
        String formattedContent = formatMessageContent(message.getMessageContent());
        message.setMessageContent(formattedContent);
		messageDao.insert(message);

		// 回傳 insert 後的物件
		return messageDao.findById(message.getMessageId());
	}

	/**
	 * 更新
	 *
	 * @param message
	 * 			留言
	 * @return 更新後的留言
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Message updateMessage(Message message) {
		messageDao.update(message);

		return messageDao.findById(message.getMessageId());
	}

	/**
	 * 依 ID 刪除
	 *
	 * @param messageId
	 * 			留言 ID
	 * @return 成功筆數
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int deleteMessage(Integer messageId) {
		return messageDao.deleteById(messageId);
	}

	/**
	 * 依 ID 查詢
	 *
	 * @param messageId
	 * 			留言 ID
	 * @return 留言
	 */
	@Override
	@Transactional(readOnly = true)
	public Message getOneMessage(Integer messageId) {
		return messageDao.findById(messageId);
	}

	/**
	 * 查詢全部
	 *
	 * @return 全部留言清單
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Message> getAll() {
		return messageDao.findAll();
	}

	/**
	 * 依文章 ID 查詢留言清單
	 *
	 * @param articleId
	 * 			文章 ID
	 * @return 留言清單
	 */
	@Override
	@Transactional(readOnly = true)
    public List<MessageForView> getMessagesByArticleId(Integer articleId) {
    	return messageDao.getMessagesByArticleId(articleId);
    }

	/**
	 * 處理留言換行內容
	 *
	 * @param content
	 * 			留言內容
	 * @return 處理後的留言內容
	 */
    private String formatMessageContent(String content) {
        if (content == null) {
            return "";
        }

        return content.replace("\n", "<br>");
    }

}