// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

package com.maddog.articket.articleimg.entity;

import com.maddog.articket.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface ArticleImgRepository extends JpaRepository<ArticleImg, Integer> {

	@Transactional
	@Modifying
	@Query("DELETE FROM ArticleImg ai WHERE ai.article.articleID = :articleID")
    void deleteByArticle_ArticleID(@Param("articleID") int articleID);

	//● (自訂)條件查詢
	@Query(value = "from ArticleImg where articleImgID=?1 and article like ?2 and articlePic like ?3 and articleImgCreateTime like ?4 order by articleImgID")
	List<ArticleImg> findByOthers(Integer articleImgID, Article article, byte[] articlePic, Date articleImgCreateTime);


	// 根據文章 ID 查詢所有圖片
	List<ArticleImg> findByArticle_ArticleID(Integer articleID);
	

}