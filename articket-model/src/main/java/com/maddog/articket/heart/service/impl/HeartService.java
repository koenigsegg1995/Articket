package com.maddog.articket.heart.service.impl;

import com.maddog.articket.article.entity.Article;
import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.heart.dao.HeartDao;
import com.maddog.articket.heart.entity.Heart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service("heartService")
public class HeartService {

	@Autowired
	private HeartDao heartDao;

//	@Autowired
//	private RedisTemplate<String, String> redisTemplate;

	// 確保文章按讚統計的一致性和安全性，同時提高程式碼的可維護性
	private static final String HEART_COUNT_KEY = "article:heart:count:";

	//會員是否按讚
	public boolean isArticleLikedByMember(Integer articleId, Integer memberId) {
	    return heartDao.isArticleLikedByMember(memberId, articleId);
	}

	// 檢查是否按讚過決定按讚或取消按讚
	@Transactional
	public boolean toggleHeart(Integer memberId, Integer articleId) {
		boolean liked = heartDao.isArticleLikedByMember(memberId, articleId);

		if (liked) { // 若尚未點讚
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

	// 獲取特定文章的按讚數
	public Long getHeartCount(Integer articleId) {
//		String key = HEART_COUNT_KEY + articleId;
//		String count = redisTemplate.opsForValue().get(key);
//		return count != null ? Long.parseLong(count) : 0L;

		return 0L;
	}

	private void addHeart(Heart heart) {
		heartDao.insert(heart);
		incrementRedisHeartCount(heart.getArticleId());
		syncHeartCount(heart.getArticleId());
	}

	private void deleteHeart(Integer memberId, Integer articleId) {
		heartDao.deleteByArticleIdAndMemberId(memberId, articleId);
		decrementRedisHeartCount(articleId);
		syncHeartCount(articleId);
	}

	// 增加特定文章的按讚統計
	private void incrementRedisHeartCount(Integer articleId) {
//		String key = HEART_COUNT_KEY + articleId;
//		redisTemplate.opsForValue().increment(key);
	}

	// 減少特定文章的按讚統計
	private void decrementRedisHeartCount(Integer articleId) {
//		String key = HEART_COUNT_KEY + articleId;
//		redisTemplate.opsForValue().decrement(key);
	}

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
