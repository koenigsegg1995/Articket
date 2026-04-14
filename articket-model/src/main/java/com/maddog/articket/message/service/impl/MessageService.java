package com.maddog.articket.message.service.impl;

import com.maddog.articket.message.dao.MessageDao;
import com.maddog.articket.message.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("messageService")
public class MessageService {
	
	@Autowired
	private MessageDao messageDao;

	public Message addMessage(Message message) {
		// 在 insert 之前處理換行符
        String formattedContent = formatMessageContent(message.getMessageContent());
        message.setMessageContent(formattedContent);
		messageDao.insert(message);

		// 回傳 insert 後的物件
		return messageDao.findById(message.getMessageId());
	}

	public Message updateMessage(Message message) {
		messageDao.update(message);

		return messageDao.findById(message.getMessageId());
	}

	public int deleteMessage(Integer message) {
		return messageDao.deleteById(message);
	}

	public Message getOneMessage(Integer messageId) {
		return messageDao.findById(messageId);
	}

	public List<Message> getAll() {
		return messageDao.findAll();
	}

    public List<Message> getMessagesByArticleId(Integer articleId) {
    	return messageDao.getMessagesByArticleId(articleId);
    }

	/**
	 * 處理留言換行內容
	 */
    private String formatMessageContent(String content) {
        if (content == null) {
            return "";
        }

        return content.replace("\n", "<br>");
    }

}