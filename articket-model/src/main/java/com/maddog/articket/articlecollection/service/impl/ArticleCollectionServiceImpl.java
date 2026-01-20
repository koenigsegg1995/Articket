package com.maddog.articket.articlecollection.service.impl;

import com.maddog.articket.articlecollection.dao.ArticleCollectionDao;
import com.maddog.articket.articlecollection.dto.ArticleCollectionForView;
import com.maddog.articket.articlecollection.entity.ArticleCollection;
import com.maddog.articket.articlecollection.service.pri.ArticleCollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文章收藏 Service Implementation
 */
@Slf4j
@Service("articleCollectionService")
public class ArticleCollectionServiceImpl implements ArticleCollectionService {

	/**
	 * 文章收藏 DAO
	 */
	@Autowired
	private ArticleCollectionDao articleCollectionDao;

//	@Autowired
//	private RedisTemplate<String, String> redisTemplate;

	/**
	 * 確保文章收藏統計的一致性和安全性，同時提高程式碼的可維護性
	 */
	private static final String ARTICLE_COLLECTION_COUNT_KEY = "article:collection:count:";

	/**
	 * 新增
	 *
	 * @param articleCollection
	 * 			文章收藏 DO
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addArticleCollection(ArticleCollection articleCollection) {
		articleCollectionDao.insert(articleCollection);
		incrementRedisArticleCollectionCount(articleCollection.getArticleId());
		syncArticleCollectionCount(articleCollection.getArticleId());
	}

	/**
	 * 刪除
	 *
	 * @param articleCollectionId
	 * 			文章收藏 ID
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteArticleCollection(Integer articleCollectionId) {
		ArticleCollection articleCollection = articleCollectionDao.findById(articleCollectionId);

		articleCollectionDao.deleteById(articleCollectionId);
		decrementRedisArticleCollectionCount(articleCollection.getArticleId());
		syncArticleCollectionCount(articleCollection.getArticleId());
	}

	/**
	 * 檢查收藏狀態
	 *
	 * @param articleId
	 * 			文章 ID
	 * @param memberId
	 * 			會員 ID
	 * @return 是/否
	 */
	@Override
	@Transactional(readOnly = true)
    public boolean isArticleCollectedByMember(Integer articleId, Integer memberId) {
        List<ArticleCollection> articleCollections = articleCollectionDao.findByMemberAndArticle(memberId, articleId);
        return !articleCollections.isEmpty();
    }

	/**
	 * 檢查是否收藏過決定收藏或取消收藏
	 *
	 * @param memberId
	 * 			會員 ID
	 * @param articleId
	 * 			文章 ID
	 * @return 是/否
	 */
	@Override
	@Transactional(readOnly = true)
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

	/**
	 * 獲取特定文章的收藏數
	 *
	 * @param articleId
	 * 			文章 ID
	 * @return 收藏數
	 */
	@Override
	public Long getArticleCollectionCount(Integer articleId) {
//		String key = ARTICLECOLLECTION_COUNT_KEY + articleId;
//		String count = redisTemplate.opsForValue().get(key);
//		return count != null ? Long.parseLong(count) : 0L;
		return  0L;
	}

	/**
	 * 增加特定文章的收藏統計
	 *
	 * @param articleId
	 * 			文章 ID
	 */
	private void incrementRedisArticleCollectionCount(Integer articleId) {
//		String key = ARTICLECOLLECTION_COUNT_KEY + articleId;
//		redisTemplate.opsForValue().increment(key);
	}

	/**
	 * 減少特定文章的收藏統計
	 *
	 * @param articleId
	 * 			文章 ID
 	 */
	private void decrementRedisArticleCollectionCount(Integer articleId) {
		String key = ARTICLE_COLLECTION_COUNT_KEY + articleId;
//		redisTemplate.opsForValue().decrement(key);
	}

	/**
	 * 同步文章收藏數
	 *
	 * @param articleId
	 * 			文章 ID
	 */
	@Override
	@Transactional(readOnly = true)
	public void syncArticleCollectionCount(Integer articleId) {
	    try {
	        int count = articleCollectionDao.countByArticleId(articleId);
	        String key = ARTICLE_COLLECTION_COUNT_KEY + articleId;
//	        redisTemplate.opsForValue().set(key, String.valueOf(count));
	    } catch (Exception e) {
	        log.error("同步文章 {} 的收藏數時發生錯誤: {}", articleId, e.getMessage());
	    }
	}

	/**
	 * 依會員 ID 查詢文章收藏 VO 清單
	 *
	 * @param memberId
	 * 			會員 ID
	 * @return 文章收藏 VO 清單
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ArticleCollectionForView> getCollectionsByMemberId(Integer memberId) {
        return articleCollectionDao.findByMemberIdForView(memberId);
    }
	
}
