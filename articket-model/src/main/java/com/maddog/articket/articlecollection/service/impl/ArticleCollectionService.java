package com.maddog.articket.articlecollection.service.impl;

import com.maddog.articket.articlecollection.dao.ArticleCollectionDao;
import com.maddog.articket.articlecollection.entity.ArticleCollection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("articleCollectionService")
public class ArticleCollectionService {

	@Autowired
	private ArticleCollectionDao articleCollectionDao;

//	@Autowired
//	private RedisTemplate<String, String> redisTemplate;

	//測試會員收藏 測試之後刪掉
//	public boolean isArticleCollectionByMember(Integer articleID, Integer memberID) {
//	    return !articleCollectionDao.findByMemberAndArticle(memberID, articleID).isEmpty();
//	}
//	
	
	// 確保文章收藏統計的一致性和安全性，同時提高程式碼的可維護性
	private static final String ARTICLECOLLECTION_COUNT_KEY = "article:collection:count:";

	public void addArticleCollection(ArticleCollection articleCollection) {
		articleCollectionDao.insert(articleCollection);
		incrementRedisArticleCollectionCount(articleCollection.getArticleId());
		syncArticleCollectionCount(articleCollection.getArticleId());
	}


	public void deleteArticleCollection(Integer articleCollectionId) {
		ArticleCollection articleCollection = articleCollectionDao.findById(articleCollectionId);

		articleCollectionDao.deleteById(articleCollectionId);
		decrementRedisArticleCollectionCount(articleCollection.getArticleId());
		syncArticleCollectionCount(articleCollection.getArticleId());
	}

	/*檢查收藏狀態*/
    public boolean isArticleCollectedByMember(Integer articleId, Integer memberId) {
        List<ArticleCollection> articleCollections = articleCollectionDao.findByMemberAndArticle(memberId, articleId);
        return !articleCollections.isEmpty();
    }

	/*檢查是否收藏過決定收藏或取消收藏*/
	public boolean toggleArticleCollection(Integer memberId, Integer articleId) {
		List<ArticleCollection> articleCollections = articleCollectionDao.findByMemberAndArticle(memberId, articleId);

		if (articleCollections.isEmpty()) {
			ArticleCollection articleCollection = new ArticleCollection();

			articleCollection.setMemberId(memberId);
			articleCollection.setArticleId(articleId);

			addArticleCollection(articleCollection);
			syncArticleCollectionCount(articleId);

			return true;
		} else {
			deleteArticleCollection(articleCollections.getLast().getArticleCollectionId());
			syncArticleCollectionCount(articleId);

			return false;
		}
	}

	// 獲取特定文章的收藏數
	public Long getArticleCollectionCount(Integer articleId) {
//		String key = ARTICLECOLLECTION_COUNT_KEY + articleId;
//		String count = redisTemplate.opsForValue().get(key);
//		return count != null ? Long.parseLong(count) : 0L;
		return  0L;
	}

	// 增加特定文章的收藏統計
	private void incrementRedisArticleCollectionCount(Integer articleId) {
//		String key = ARTICLECOLLECTION_COUNT_KEY + articleId;
//		redisTemplate.opsForValue().increment(key);
	}

	// 減少特定文章的收藏統計
	private void decrementRedisArticleCollectionCount(Integer articleId) {
		String key = ARTICLECOLLECTION_COUNT_KEY + articleId;
//		redisTemplate.opsForValue().decrement(key);
	}
	
	public void syncArticleCollectionCount(Integer articleId) {
	    try {
	        int count = articleCollectionDao.countByArticleId(articleId);
	        String key = ARTICLECOLLECTION_COUNT_KEY + articleId;
//	        redisTemplate.opsForValue().set(key, String.valueOf(count));
	    } catch (Exception e) {
	        log.error("同步文章 {} 的收藏數時發生錯誤: {}", articleId, e.getMessage());
	    }
	}

	public List<ArticleCollection> getCollectionsByMemberId(Integer memberId) {
        return articleCollectionDao.findByMemberIdWithArticle(memberId);
    }
	
}
