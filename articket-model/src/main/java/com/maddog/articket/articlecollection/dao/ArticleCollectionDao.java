package com.maddog.articket.articlecollection.dao;

import com.maddog.articket.articlecollection.dto.ArticleCollectionForView;
import com.maddog.articket.articlecollection.entity.ArticleCollection;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章收藏 DAO
 */
@Mapper
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
	ArticleCollection findById(Integer articleCollectionId);

	/**
	 * 依活動收藏 ID 刪除
	 *
	 * @param articleCollectionId
	 * 			活動收藏 ID
	 * @return 成功筆數
	 */
	int deleteById(Integer articleCollectionId);

	/**
	 * 會員對特定文章的收藏記錄
	 *
	 * @param memberId
	 * 			會員 ID
	 * @param articleId
	 * 			文章 ID
	 * @return 文章收藏 DO 清單
	 */
	List<ArticleCollection> findByMemberAndArticle(@Param("memberId") Integer memberId,
												   @Param("articleId") Integer articleId);

	/**
	 * 依文章 ID 查詢收藏數量
	 *
	 * @param articleId
	 * 			文章 ID
	 * @return 收藏數量
	 */
	int countByArticleId(Integer articleId);

	/**
	 * 依會員 ID 查詢文章收藏 VO 清單
	 *
	 * @param memberId
	 * 			會員 ID
	 * @return 文章收藏 VO 清單
	 */
    List<ArticleCollectionForView> findByMemberIdForView(Integer memberId);

}