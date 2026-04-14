package com.maddog.articket.heart.service.pri;

/**
 * 文章點讚 Service Interface
 */
public interface HeartService {

	/**
	 * 會員是否對文章點讚
	 *
	 * @param articleId
	 * 			文章 ID
	 * @param memberId
	 * 			會員 ID
	 * @return 是 / 否
	 */
	boolean isArticleLikedByMember(Integer articleId, Integer memberId);

	/**
	 * 檢查是否按讚過決定按讚或取消按讚
	 *
	 * @param memberId
	 * 			會員 ID
	 * @param articleId
	 * 			文章 ID
	 * @return 按讚後的狀態
	 */
	boolean toggleHeart(Integer memberId, Integer articleId);

	/**
	 * 獲取特定文章的按讚數
	 *
	 * @param articleId
	 * 			文章 ID
	 * @return 按讚數
	 */
	Long getHeartCount(Integer articleId);

}
