package com.maddog.articket.message.dao;

import com.maddog.articket.message.entity.Message;
import com.maddog.articket.message.rowmapper.MessageRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Component
public class MessageDao {
	
	@Autowired
	private NamedParameterJdbcTemplate npjt;
	
	public List<Message> getMessagesByArticleIdDao(Integer articleID){
		String sql = "SELECT m1.messageID, m1.articleID, m1.memberID, m2.memberName, m1.messageContent, m1.messageCreateTime\r\n"
				+ "FROM message as m1 \r\n"
				+ "LEFT JOIN generalmember as m2 ON m1.memberid = m2.memberid\r\n"
				+ "WHERE m1.articleID = :articleID";
		
		Map<String, Object> map = new HashMap<>();
		map.put("articleID", articleID);
		
		List<Message> messages = npjt.query(sql, map, new MessageRowMapper());
		
		return messages;
	}
}
