package com.maddog.articket.articlecollection.dao;

import com.maddog.articket.articlecollection.entity.ArticleCollection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleCollectionDao {

	int insert(ArticleCollection articleCollection);

	@Query(value = "delete from articleCollection where articleCollectionID =?1", nativeQuery = true)
	void deleteById(int articleCollectionID);

	@Query("from ArticleCollection where articleCollectionID = ?1")
	ArticleCollection findById(int articleCollectionId);

	// 會員對特定文章的收藏記錄
	@Query("from ArticleCollection where generalMember.memberID = ?1 and article.articleID = ?2")
	List<ArticleCollection> findByMemberAndArticle(int memberID, int articleID);

	int countByArticleId(Integer articleId);
	
	@Query("SELECT ac FROM ArticleCollection ac JOIN FETCH ac.article WHERE ac.generalMember.memberID = :memberID")
    List<ArticleCollection> findByGeneralMemberMemberIDWithArticle(@Param("memberID") Integer memberID);

}