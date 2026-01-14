package com.maddog.articket.articlecollection.dao;

import com.maddog.articket.articlecollection.entity.ArticleCollection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleCollectionDao {

	/**
	 * 新增
	 *
	 * @param articleCollection
	 * 			文章收藏 DO
	 * @return 成功筆數
	 */
	int insert(ArticleCollection articleCollection);

	/**
	 * 依活動收藏 ID 查詢
	 *
	 * @param articleCollectionId
	 * 			活動收藏 ID
	 * @return 文章收藏 DO
	 */
	ArticleCollection findById(int articleCollectionId);

	/**
	 * 依活動收藏 ID 刪除
	 *
	 * @param articleCollectionId
	 * 			活動收藏 ID
	 * @return 成功筆數
	 */
	int deleteById(int articleCollectionId);

	/**
	 * 會員對特定文章的收藏記錄
	 *
	 * @param memberId
	 * 			會員 ID
	 * @param articleId
	 * 			文章 ID
	 * @return 文章收藏 DO 清單
	 */
	List<ArticleCollection> findByMemberAndArticle(@Param("memberId") int memberId,
												   @Param("articleId") int articleId);

	/**
	 * 依文章 ID 查詢收藏數量
	 *
	 * @param articleId
	 * 			文章 ID
	 * @return 收藏數量
	 */
	int countByArticleId(Integer articleId);

	/**
	 * 依會員 ID 查詢文章收藏 DO 清單
	 *
	 * @param memberId
	 * 			會員 ID
	 * @return 文章收藏 DO 清單
	 */
	// TODO: 需修改正確 SQL
    List<ArticleCollection> findByMemberIdWithArticle(Integer memberId);

}