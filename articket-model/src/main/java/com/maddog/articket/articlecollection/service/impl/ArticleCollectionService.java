package com.maddog.articket.articlecollection.service.impl;

import com.maddog.articket.article.entity.Article;
import com.maddog.articket.articlecollection.dao.ArticleCollectionRepository;
import com.maddog.articket.articlecollection.entity.ArticleCollection;
import com.maddog.articket.generalmember.entity.GeneralMember;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("articleCollectionService")
public class ArticleCollectionService {


	@Autowired
	ArticleCollectionRepository repository;


//	@Autowired
//	private RedisTemplate<String, String> redisTemplate;

	//測試會員收藏 測試之後刪掉
//	public boolean isArticleCollectionByMember(Integer articleID, Integer memberID) {
//	    return !repository.findByMemberAndArticle(memberID, articleID).isEmpty();
//	}
//	
	
	// 確保文章收藏統計的一致性和安全性，同時提高程式碼的可維護性
	private static final String ARTICLECOLLECTION_COUNT_KEY = "article:collection:count:";

	public void addArticleCollection(ArticleCollection articleCollection) {
		repository.save(articleCollection);
		incrementRedisArticleCollectionCount(articleCollection.getArticle().getArticleID());
		syncArticleCollectionCount(articleCollection.getArticle().getArticleID());
	}


	public void deleteArticleCollection(Integer articleCollectionID) {
		ArticleCollection articleCollection = repository.findByArticleCollectionID(articleCollectionID)
				.orElseThrow(() -> new IllegalArgumentException("articleCollectionID " + articleCollectionID + " not found"));
				// Optional類和lambda表達式的組合
		
		repository.deleteByArticleCollectionID(articleCollectionID);
		decrementRedisArticleCollectionCount(articleCollection.getArticle().getArticleID());
		syncArticleCollectionCount(articleCollection.getArticle().getArticleID());
	}

	public ArticleCollection getOneArticleCollection(Integer articleCollectionID) {
		return repository.findByArticleCollectionID(articleCollectionID)
				.orElseThrow(() -> new IllegalArgumentException("articleCollectionID " + articleCollectionID + " not found"));
	}

	public List<ArticleCollection> getAll() {
		return repository.findAll();
	}
	
	/*檢查收藏狀態*/
    public boolean isArticleCollectedByMember(Integer articleID, Integer memberID) {
        List<ArticleCollection> articleCollections = repository.findByMemberAndArticle(memberID, articleID);
        return !articleCollections.isEmpty();
    }

	/*檢查是否收藏過決定收藏或取消收藏*/
	public boolean toggleArticleCollection(Integer memberID, Integer articleID) {
		List<ArticleCollection> articleCollections = repository.findByMemberAndArticle(memberID, articleID);

		if (articleCollections.isEmpty()) {
			ArticleCollection articleCollection = new ArticleCollection();

			GeneralMember generalmember = new GeneralMember();
			generalmember.setMemberID(memberID);

			Article article = new Article();
			article.setArticleID(articleID);

			articleCollection.setGeneralMember(generalmember);
			articleCollection.setArticle(article);

			addArticleCollection(articleCollection);
			syncArticleCollectionCount(articleID);
			return true;
		} else {
			deleteArticleCollection(articleCollections.get(articleCollections.size() - 1).getArticleCollectionID());
			syncArticleCollectionCount(articleID);
			return false;
		}
	}

	// 獲取特定文章的收藏數
	public Long getArticleCollectionCount(Integer articleID) {
//		String key = ARTICLECOLLECTION_COUNT_KEY + articleID;
//		String count = redisTemplate.opsForValue().get(key);
//		return count != null ? Long.parseLong(count) : 0L;
		return  0L;
	}

	// 增加特定文章的收藏統計
	private void incrementRedisArticleCollectionCount(Integer articleID) {
//		String key = ARTICLECOLLECTION_COUNT_KEY + articleID;
//		redisTemplate.opsForValue().increment(key);
	}

	// 減少特定文章的收藏統計
	private void decrementRedisArticleCollectionCount(Integer articleID) {
		String key = ARTICLECOLLECTION_COUNT_KEY + articleID;
//		redisTemplate.opsForValue().decrement(key);
	}
	
	public void syncArticleCollectionCount(Integer articleID) {
	    try {
	        List<ArticleCollection> collections = repository.findAll().stream()
	            .filter(collection -> collection.getArticle().getArticleID().equals(articleID))
	            .toList();
	        long sqlCount = collections.size();
	        String key = ARTICLECOLLECTION_COUNT_KEY + articleID;
//	        redisTemplate.opsForValue().set(key, String.valueOf(sqlCount));
	    } catch (Exception e) {
	        System.err.println("同步文章 " + articleID + " 的收藏數時發生錯誤: " + e.getMessage());
	    }
	}

	public List<ArticleCollection> getCollectionsByMemberID(Integer memberID) {
        return repository.findByGeneralMemberMemberIDWithArticle(memberID);
    }
	
}
