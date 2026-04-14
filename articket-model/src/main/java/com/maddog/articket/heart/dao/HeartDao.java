package com.maddog.articket.heart.dao;

import com.maddog.articket.heart.entity.Heart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 文章點讚 DAO
 */
@Mapper
public interface HeartDao {

	/**
	 * 點讚
	 *
	 * @param heart
	 * 			點讚資訊
	 * @return 成功筆數
	 */
	int insert(Heart heart);

	/**
	 * 取消點讚
	 *
	 * @param memberId
	 * 			會員 ID
	 * @param articleId
	 * 			文章 ID
	 * @return 成功筆數
	 */
	int deleteByArticleIdAndMemberId(@Param("memberId") Integer memberId, @Param("articleId") Integer articleId);

	/**
	 * 會員是否對文章點讚
	 *
	 * @param articleId
	 * 			文章 ID
	 * @param memberId
	 * 			會員 ID
	 * @return 是 / 否
	 */
	boolean isArticleLikedByMember(@Param("articleId") Integer articleId, @Param("memberId") Integer memberId);

	/**
	 * 查詢文章點讚數
	 *
	 * @param articleId
	 * 			文章 ID
	 * @return 點讚數
	 */
	int countByArticleId(Integer articleId);

}