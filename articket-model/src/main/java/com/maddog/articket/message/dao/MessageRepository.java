// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

package com.maddog.articket.message.dao;

import com.maddog.articket.article.entity.Article;
import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

	@Transactional
	@Modifying
	@Query(value = "delete from message where messageID =?1", nativeQuery = true) //原生SQL
	void deleteByMessageID(int messageID);

	//● (自訂)條件查詢, 自定義的JPQL
	@Query(value = "from Message where messageID=?1 and memberID=?2 and articleID=?3 and messageContent like ?4 and messageStatus=?5 and messageCreateTime =?6 order by messageID")
	List<Message> findByCustomConditions(Integer messageID, GeneralMember memberID, Article articleID, String messageContent, Integer messageStatus, Date startDate, Date endDate);
	
	//JPA 簡易查詢,根據ArticleID查詢留言
	List<Message> findByArticle_ArticleID(Integer articleID);
	
}