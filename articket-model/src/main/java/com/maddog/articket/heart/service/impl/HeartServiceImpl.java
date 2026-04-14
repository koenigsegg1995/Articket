package com.maddog.articket.heart.service.impl;

import com.maddog.articket.heart.dao.HeartDao;
import com.maddog.articket.heart.entity.Heart;
import com.maddog.articket.heart.service.pri.HeartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文章點讚 Service Implementation
 */
@Slf4j
@Service("heartService")
public class HeartServiceImpl implements HeartService {

	/**
	 * 文章點讚 DAO
	 */
	@Autowired
	private HeartDao heartDao;

//	@Autowired
//	private RedisTemplate<String, String> redisTemplate;

	/**
	 * 確保文章按讚統計的一致性和安全性，同時提高程式碼的可維護性
 	 */
	private static final String HEART_COUNT_KEY = "article:heart:count:";

	/**
	 * 會員是否對文章點讚
	 *
	 * @param articleId
	 * 			文章 ID
	 * @param memberId
	 * 			會員 ID
	 * @return 是 / 否
	 */
	@Override
	@Transactional(readOnly = true)
	public boolean isArticleLikedByMember(Integer articleId, Integer memberId) {
	    return heartDao.isArticleLikedByMember(memberId, articleId);
	}

	/**
	 * 檢查是否按讚過決定按讚或取消按讚
	 *
	 * @param memberId
	 * 			會員 ID
	 * @param articleId
	 * 			文章 ID
	 * @return 按讚後的狀態
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean toggleHeart(Integer memberId, Integer articleId) {
		boolean isLiked = heartDao.isArticleLikedByMember(memberId, articleId);

		if (!isLiked) { // 若尚未點讚
			Heart heart = new Heart();
			heart.setMemberId(memberId);
			heart.setArticleId(articleId);

			addHeart(heart);

			return true;
		} else { // 若點過讚
			deleteHeart(memberId, articleId);

			return false;
		}
	}

	/**
	 * 獲取特定文章的按讚數
	 *
	 * @param articleId
	 * 			文章 ID
	 * @return 按讚數
 	 */
	@Override
	@Transactional(readOnly = true)
	public Long getHeartCount(Integer articleId) {
//		String key = HEART_COUNT_KEY + articleId;
//		String count = redisTemplate.opsForValue().get(key);

//		return count != null ? Long.parseLong(count) : 0L;
		return (long) heartDao.countByArticleId(articleId);
	}

	/**
	 * 按讚
	 *
	 * @param heart
	 * 			按讚資訊
	 */
	private void addHeart(Heart heart) {
		heartDao.insert(heart);
//		incrementRedisHeartCount(heart.getArticleId());
//		syncHeartCount(heart.getArticleId());
	}

	/**
	 * 取消讚
	 *
	 * @param memberId
	 * 			會員 ID
	 * @param articleId
	 * 			文章 ID
	 */
	private void deleteHeart(Integer memberId, Integer articleId) {
		heartDao.deleteByArticleIdAndMemberId(memberId, articleId);
//		decrementRedisHeartCount(articleId);
//		syncHeartCount(articleId);
	}

//	/**
//	 * 增加特定文章的按讚統計
//	 *
//	 * @param articleId
//	 * 			文章 ID
// 	 */
//	private void incrementRedisHeartCount(Integer articleId) {
//		String key = HEART_COUNT_KEY + articleId;
//		redisTemplate.opsForValue().increment(key);
//	}

//	/**
//	 * 減少特定文章的按讚統計
//	 *
//	 * @param articleId
//	 * 			文章 ID
// 	 */
//	private void decrementRedisHeartCount(Integer articleId) {
//		String key = HEART_COUNT_KEY + articleId;
//		redisTemplate.opsForValue().decrement(key);
//	}

	/**
	 * 同步文章按讚數
	 *
	 * @param articleId
	 * 			文章 ID
	 */
	private void syncHeartCount(Integer articleId) {
		try {
			int sqlCount = heartDao.countByArticleId(articleId);
			String key = HEART_COUNT_KEY + articleId;
//            redisTemplate.opsForValue().set(key, String.valueOf(sqlCount));

			log.info("同步文章 {} 的按讚數: {}", articleId, sqlCount);
		} catch (Exception e) {
			log.error("同步文章 {} 的按讚數時發生錯誤: {}", articleId, e.getMessage());
		}
	}

}
