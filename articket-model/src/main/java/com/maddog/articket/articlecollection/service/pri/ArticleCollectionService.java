package com.maddog.articket.articlecollection.service.pri;

import com.maddog.articket.articlecollection.dto.ArticleCollectionForView;
import com.maddog.articket.articlecollection.entity.ArticleCollection;

import java.util.List;

/**
 * 文章收藏 Service Interface
 */
public interface ArticleCollectionService {

	/**
	 * 新增
	 *
	 * @param articleCollection
	 * 			文章收藏 DO
	 */
	void addArticleCollection(ArticleCollection articleCollection);

	/**
	 * 刪除
	 *
	 * @param articleCollectionId
	 * 			文章收藏 ID
	 */
	void deleteArticleCollection(Integer articleCollectionId);

	/**
	 * 檢查收藏狀態
	 *
	 * @param articleId
	 * 			文章 ID
	 * @param memberId
	 * 			會員 ID
	 * @return 是/否
	 */
    boolean isArticleCollectedByMember(Integer articleId, Integer memberId);

	/**
	 * 檢查是否收藏過決定收藏或取消收藏
	 *
	 * @param memberId
	 * 			會員 ID
	 * @param articleId
	 * 			文章 ID
	 * @return 是/否
	 */
	boolean toggleArticleCollection(Integer memberId, Integer articleId);

	/**
	 * 獲取特定文章的收藏數
	 *
	 * @param articleId
	 * 			文章 ID
	 * @return 收藏數
	 */
	Long getArticleCollectionCount(Integer articleId);

	/**
	 * 同步文章收藏數
	 *
	 * @param articleId
	 * 			文章 ID
	 */
	void syncArticleCollectionCount(Integer articleId);

	/**
	 * 依會員 ID 查詢文章收藏 VO 清單
	 *
	 * @param memberId
	 * 			會員 ID
	 * @return 文章收藏 VO 清單
	 */
	List<ArticleCollectionForView> getCollectionsByMemberId(Integer memberId);
	
}
