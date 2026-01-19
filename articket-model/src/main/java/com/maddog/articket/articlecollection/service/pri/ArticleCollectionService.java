package com.maddog.articket.articlecollection.service.pri;

import com.maddog.articket.articlecollection.dto.ArticleCollectionForView;
import com.maddog.articket.articlecollection.entity.ArticleCollection;

import java.util.List;

/**
 * 文章收藏 Service Interface
 */
public interface ArticleCollectionService {

	void addArticleCollection(ArticleCollection articleCollection);

	void deleteArticleCollection(Integer articleCollectionId);

	/**
	 * 檢查收藏狀態
	 */
    boolean isArticleCollectedByMember(Integer articleId, Integer memberId);

	/**
	 * 檢查是否收藏過決定收藏或取消收藏
	 */
	boolean toggleArticleCollection(Integer memberId, Integer articleId);

	/**
	 * 獲取特定文章的收藏數
	 */
	Long getArticleCollectionCount(Integer articleId);
	
	void syncArticleCollectionCount(Integer articleId);

	List<ArticleCollectionForView> getCollectionsByMemberId(Integer memberId);
	
}
