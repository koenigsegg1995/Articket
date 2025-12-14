package com.maddog.articket.heart.service.impl;

import com.maddog.articket.article.entity.Article;
import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.heart.dao.HeartRepository;
import com.maddog.articket.heart.entity.Heart;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("heartService")
public class HeartService {

	@Autowired
	HeartRepository repository;

//	@Autowired
//	private RedisTemplate<String, String> redisTemplate;

	//會員是否按讚
	public boolean isArticleLikedByMember(Integer articleID, Integer memberID) {
	    return !repository.findByMemberAndArticle(memberID, articleID).isEmpty();
	}
	
	
	// 確保文章按讚統計的一致性和安全性，同時提高程式碼的可維護性
	private static final String HEART_COUNT_KEY = "article:heart:count:";

	@Transactional
	public void addHeart(Heart heart) {
		repository.save(heart);
		incrementRedisHeartCount(heart.getArticle().getArticleID());
		syncHeartCount(heart.getArticle().getArticleID());
	}


	@Transactional
	public void deleteHeart(Integer heartID) {
		Heart heart = repository.findByHeartID(heartID)
				.orElseThrow(() -> new IllegalArgumentException("heartID " + heartID + " not found"));// Optional
																										// 類和lambda
																										// 表達式的組合
		repository.deleteByHeartID(heartID);
		decrementRedisHeartCount(heart.getArticle().getArticleID());
		syncHeartCount(heart.getArticle().getArticleID());
	}

	public Heart getOneHeart(Integer heartID) {
		return repository.findByHeartID(heartID)
				.orElseThrow(() -> new IllegalArgumentException("heartID " + heartID + " not found"));
	}

	public List<Heart> getAll() {
		return repository.findAll();
	}

	// 檢查是否按讚過決定按讚或取消按讚
	@Transactional
	public boolean toggleHeart(Integer memberID, Integer articleID) {
		List<Heart> hearts = repository.findByMemberAndArticle(memberID, articleID);

		if (hearts.isEmpty()) {
			Heart heart = new Heart();

			GeneralMember generalmember = new GeneralMember();
			generalmember.setMemberID(memberID);

			Article article = new Article();
			article.setArticleID(articleID);

			heart.setGeneralMember(generalmember);
			heart.setArticle(article);

			addHeart(heart);
			syncHeartCount(articleID);
			return true;
		} else {
			deleteHeart(hearts.get(hearts.size() - 1).getHeartID());
			syncHeartCount(articleID);
			return false;
		}
	}

	// 獲取特定文章的按讚數
	public Long getHeartCount(Integer articleID) {
//		String key = HEART_COUNT_KEY + articleID;
//		String count = redisTemplate.opsForValue().get(key);
//		return count != null ? Long.parseLong(count) : 0L;
		return 0L;
	}

	// 增加特定文章的按讚統計
	private void incrementRedisHeartCount(Integer articleID) {
//		String key = HEART_COUNT_KEY + articleID;
//		redisTemplate.opsForValue().increment(key);
	}

	// 減少特定文章的按讚統計
	private void decrementRedisHeartCount(Integer articleID) {
//		String key = HEART_COUNT_KEY + articleID;
//		redisTemplate.opsForValue().decrement(key);
	}
	
    public void syncHeartCount(Integer articleID) {
        try {
            List<Heart> hearts = repository.findAll().stream()
                .filter(heart -> heart.getArticle().getArticleID().equals(articleID))
                .toList();
            long sqlCount = hearts.size();
            String key = HEART_COUNT_KEY + articleID;
//            redisTemplate.opsForValue().set(key, String.valueOf(sqlCount));
            System.out.println("同步文章 " + articleID + " 的按讚數: " + sqlCount);
        } catch (Exception e) {
            System.err.println("同步文章 " + articleID + " 的按讚數時發生錯誤: " + e.getMessage());
        }
    }


}
