package com.maddog.articket.message.rowmapper;

import com.maddog.articket.message.entity.Message;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MessageRowMapper implements RowMapper<Message> {

	@Override
	public Message mapRow(ResultSet rs, int i) throws SQLException {
		
		Message message = new Message();
		
		message.setMessageID(rs.getInt("messageID"));
		message.setMemberIDRM(rs.getInt("memberID"));
		message.setArticleIDRM(rs.getInt("articleID"));
		message.setMemberNameRM(rs.getString("memberName"));
		message.setMessageContent(rs.getString("messageContent"));
		message.setMessageCreateTimeRM(rs.getTimestamp("messageCreateTime"));
		
		return message;
	}

}
