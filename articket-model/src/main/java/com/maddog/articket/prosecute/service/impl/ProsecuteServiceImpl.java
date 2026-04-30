package com.maddog.articket.prosecute.service.impl;

import com.maddog.articket.prosecute.dao.ProsecuteDao;
import com.maddog.articket.prosecute.entity.Prosecute;
import com.maddog.articket.prosecute.service.pri.ProsecuteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 檢舉 Service Implementation
 */
@Slf4j
@Service("prosecuteService")
public class ProsecuteServiceImpl implements ProsecuteService {

	private static final String PROSECUTE_HASH_KEY = "prosecute:";
	private static final String REPORTED_ARTICLES_SET = "reported:articles";
	private static final String REPORTED_MESSAGES_SET = "reported:messages";

	/**
	 * 檢舉 DAO
	 */
	@Autowired
	private ProsecuteDao prosecuteDao;

//	@Autowired
//	private StringRedisTemplate redisTemplate;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void prosecuteContent(Prosecute prosecute) {
	    if (prosecute == null) {
	        throw new IllegalArgumentException("檢舉對象不能為空");
	    }
	    if (prosecute.getMemberId() == null) {
	        throw new IllegalArgumentException("檢舉者信息不完整");
	    }
	    // 檢查是檢舉文章還是留言
	       if (prosecute.getArticleId() != null) {
	            if (isArticleReported(prosecute.getArticleId())) {
	                throw new IllegalStateException("此文章已被檢舉");
	            }
	        } else if (prosecute.getMessageId() != null) {
	            if (isMessageReported(prosecute.getMessageId())) {
	                throw new IllegalStateException("此留言已被檢舉");
	            }
	        } else {
	            throw new IllegalArgumentException("必須指定檢舉的文章或留言");
	        }

		// 設置檢舉創建時間
	    if (prosecute.getProsecuteCreateTime() == null) {
	        prosecute.setProsecuteCreateTime(new Date(System.currentTimeMillis()));
	    }
	    
		try {
			prosecuteDao.insert(prosecute);
		} catch (DataIntegrityViolationException e) {
			throw new RuntimeException("檢舉保存失敗", e);
		}
		syncProsecuteToRedis(prosecute); 
		

		String prosecuteKey = PROSECUTE_HASH_KEY + prosecute.getProsecuteId();
//		redisTemplate.opsForHash().putAll(prosecuteKey, convertProsecuteToMap(prosecute));

		// 更新 Redis 集合
	    if (prosecute.getArticleId() != null) {
//	        redisTemplate.opsForSet().add(REPORTED_ARTICLES_SET, prosecute.getArticle().getArticleID().toString());
	    } else if (prosecute.getMessageId() != null) {
//	        redisTemplate.opsForSet().add(REPORTED_MESSAGES_SET, prosecute.getMessage().getMessageID().toString());
	    }
	}

	// 被檢舉的是文章
	@Override
	public boolean isArticleReported(Integer articleID) {
//		return redisTemplate.opsForSet().isMember(REPORTED_ARTICLES_SET, articleID.toString());
		return false;
	}

	// 被檢舉的是留言
	@Override
	public boolean isMessageReported(Integer messageID) {
//		return redisTemplate.opsForSet().isMember(REPORTED_MESSAGES_SET, messageID.toString());
		return false;
	}

	private Map<String, String> convertProsecuteToMap(Prosecute prosecute) {
		Map<String, String> map = new HashMap<>();
		map.put("prosecuteID", prosecute.getProsecuteId().toString());
		map.put("memberID", prosecute.getMemberId().toString());
		map.put("prosecuteReason", prosecute.getProsecuteReason());
		map.put("prosecuteStatus", prosecute.getProsecuteStatus().toString());

		if (prosecute.getArticleId() != null) {
			map.put("articleID", prosecute.getArticleId().toString());
		}
		if (prosecute.getMessageId() != null) {
			map.put("messageID", prosecute.getMessageId().toString());
		}
		return map;
	}

	// 同步單個檢舉到 Redis
	private void syncProsecuteToRedis(Prosecute prosecute) {
	    String prosecuteKey = PROSECUTE_HASH_KEY + prosecute.getProsecuteId();
//	    redisTemplate.opsForHash().putAll(prosecuteKey, convertProsecuteToMap(prosecute));

	    if (prosecute.getArticleId() != null) {
//	        redisTemplate.opsForSet().add(REPORTED_ARTICLES_SET, prosecute.getArticle().getArticleID().toString());
	    } else if (prosecute.getMessageId() != null) {
//	        redisTemplate.opsForSet().add(REPORTED_MESSAGES_SET, prosecute.getMessage().getMessageID().toString());
	    }
	}

//	// 同步所有檢舉到 Redis
//	private void syncAllProsecutesToRedis() {
//	    List<Prosecute> allProsecutes = prosecuteDao.findAll();
//
//		for (Prosecute prosecute : allProsecutes) {
//	        syncProsecuteToRedis(prosecute);
//	    }
//
//	    log.info("所有檢舉同步完成");
//	}

}