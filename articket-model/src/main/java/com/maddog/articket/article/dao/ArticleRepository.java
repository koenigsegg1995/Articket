// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

package com.maddog.articket.article.dao;

import com.maddog.articket.article.entity.Article;
import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

	@Transactional
	@Modifying
	@Query(value = "delete from article where articleID =?1", nativeQuery = true) //原生SQL
	void deleteByArticleID(int articleID);

	//● (自訂)條件查詢 自定義的JPQL
	@Query(value = "FROM Article WHERE articleID=?1 AND articleCategory LIKE ?2 AND articleTitle LIKE ?3 AND memberID = ?4 AND articleContent LIKE ?5 AND boardID = ?6 AND articleStatus = ?7 AND articleCreateTime = ?8 ORDER BY articleID")
	List<Article> findByOthers(Integer articleID, String articleCategory, String articleTitle, GeneralMember memberID, String articleContent, Board boardID, Integer articleStatus, Date articleCreateTime);

	 // JPA 簡易查詢,獲取所有不同的文章分類
    @Query("SELECT DISTINCT a.articleCategory FROM Article a WHERE a.articleCategory IS NOT NULL")
    List<String> findAllDistinctCategories();

}