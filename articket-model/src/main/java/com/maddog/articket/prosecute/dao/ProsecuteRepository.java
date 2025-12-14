// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

package com.maddog.articket.prosecute.dao;

import com.maddog.articket.article.entity.Article;
import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.message.entity.Message;
import com.maddog.articket.prosecute.entity.Prosecute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface ProsecuteRepository extends JpaRepository<Prosecute, Integer> {

	@Transactional
	@Modifying
	@Query(value = "delete from prosecute where prosecuteID =?1", nativeQuery = true)
	void deleteByProsecuteID(int prosecuteID);

	//● (自訂)條件查詢
	@Query(value = "from Prosecute where prosecuteID=?1 and memberID=?2 and articleID=?3 and prosecuteReason like ?4 and messageID=?5 and prosecuteStatus=?6 and prosecuteCreateTime=?7 order by prosecuteID")
	List<Prosecute> findByCustomConditions(
			Integer prosecuteID, GeneralMember memberID, Article articleID, String prosecuteReason, Message messageID, Integer prosecuteStatus, Date prosecuteCreateTime);
}