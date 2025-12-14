// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

package com.maddog.articket.articlecollection.dao;

import com.maddog.articket.articlecollection.entity.ArticleCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ArticleCollectionRepository extends JpaRepository<ArticleCollection, Integer> {

	@Transactional
	@Modifying
	@Query(value = "delete from articleCollection where articleCollectionID =?1", nativeQuery = true)
	void deleteByArticleCollectionID(int articleCollectionID);

	// ● (自訂)條件查詢
	@Query(value = "from ArticleCollection where articleCollectionID=?1 and articleID like ?2 and collectionCreateTime like ?3 order by articleCollectionID")
	List<ArticleCollection> findByOthers(int articleCollectionID, int articleID, java.util.Date collectionCreateTime);

	// 簡易查詢
	@Query("from ArticleCollection where articleCollectionID = ?1")
	Optional<ArticleCollection> findByArticleCollectionID(int articleCollectionID); // 使用Optional表示預期返回最多一個或沒有結果

	// 會員對特定文章的收藏記錄
	@Query("from ArticleCollection where generalMember.memberID = ?1 and article.articleID = ?2")
	List<ArticleCollection> findByMemberAndArticle(int memberID, int articleID);
	
	// 會員收藏的所有文章
	@Query("from ArticleCollection where generalMember.memberID = ?1")
	List<ArticleCollection> findByMemberAndArticleList(int memberID);
	
	@Query("SELECT ac FROM ArticleCollection ac JOIN FETCH ac.article WHERE ac.generalMember.memberID = :memberID")
    List<ArticleCollection> findByGeneralMemberMemberIDWithArticle(@Param("memberID") Integer memberID);

}