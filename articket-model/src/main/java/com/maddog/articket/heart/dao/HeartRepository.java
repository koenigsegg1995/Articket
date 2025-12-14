// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

package com.maddog.articket.heart.dao;

import com.maddog.articket.article.entity.Article;
import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.heart.entity.Heart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Integer> {

	@Transactional
	@Modifying
	@Query(value = "delete from heart where heartID =?1", nativeQuery = true)
	void deleteByHeartID(int heartID);

	//● (自訂)條件查詢
	@Query(value = "from Heart where heartID=?1 and memberID =?2 and articleID =?3 and heartCreateTime =?4 order by heartID")
	List<Heart> findByOthers(int heartID , GeneralMember memberID , Article articleID , Date heartCreateTime);

	//簡易查詢
	@Query("from Heart where heartID = ?1")
	Optional<Heart> findByHeartID(int heartID); //使用Optional表示預期返回最多一個或沒有結果
	
	//會員對特定文章的按讚記錄
	@Query("from Heart where generalMember.memberID = ?1 and article.articleID = ?2")
	List<Heart> findByMemberAndArticle(int memberID, int articleID);
	
}