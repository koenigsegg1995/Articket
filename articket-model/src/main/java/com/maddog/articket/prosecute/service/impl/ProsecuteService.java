package com.maddog.articket.prosecute.service.impl;

import com.maddog.articket.article.entity.Article;
import com.maddog.articket.article.service.impl.ArticleService;
import com.maddog.articket.message.entity.Message;
import com.maddog.articket.message.service.impl.MessageService;
import com.maddog.articket.prosecute.dao.ProsecuteRepository;
import com.maddog.articket.prosecute.entity.Prosecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("prosecuteService")
public class ProsecuteService {
	private static final String PROSECUTE_HASH_KEY = "prosecute:";
	private static final String REPORTED_ARTICLES_SET = "reported:articles";
	private static final String REPORTED_MESSAGES_SET = "reported:messages";

	@Autowired
	ProsecuteRepository repository;

//	@Autowired
//	private StringRedisTemplate redisTemplate;
	
	@Autowired
	ArticleService articleSvc;
	
	@Autowired
	MessageService messageSvc;
	


	@Transactional
	public void prosecuteContent(Prosecute prosecute) {
	    if (prosecute == null) {
	        throw new IllegalArgumentException("檢舉對象不能為空");
	    }
	    if (prosecute.getGeneralMember() == null || prosecute.getGeneralMember().getMemberID() == null) {
	        throw new IllegalArgumentException("檢舉者信息不完整");
	    }
	    // 檢查是檢舉文章還是留言
	       if (prosecute.getArticle() != null) {
	            if (prosecute.getArticle().getArticleID() == null) {
	                throw new IllegalArgumentException("被檢舉文章信息不完整");
	            }
	            if (isArticleReported(prosecute.getArticle().getArticleID())) {
	                throw new IllegalStateException("此文章已被檢舉");
	            }
	        } else if (prosecute.getMessage() != null) {
	            if (prosecute.getMessage().getMessageID() == null) {
	                throw new IllegalArgumentException("被檢舉留言信息不完整");
	            }
	            if (isMessageReported(prosecute.getMessage().getMessageID())) {
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
			repository.save(prosecute);
		} catch (DataIntegrityViolationException e) {
			throw new RuntimeException("檢舉保存失敗", e);
		}
		syncProsecuteToRedis(prosecute); 
		

		String prosecuteKey = PROSECUTE_HASH_KEY + prosecute.getProsecuteID();
//		redisTemplate.opsForHash().putAll(prosecuteKey, convertProsecuteToMap(prosecute));

		// 更新 Redis 集合
	    if (prosecute.getArticle() != null) {
//	        redisTemplate.opsForSet().add(REPORTED_ARTICLES_SET, prosecute.getArticle().getArticleID().toString());
	    } else if (prosecute.getMessage() != null) {
//	        redisTemplate.opsForSet().add(REPORTED_MESSAGES_SET, prosecute.getMessage().getMessageID().toString());
	    }
	}

	// 被檢舉的是文章
	public boolean isArticleReported(Integer articleID) {
//		return redisTemplate.opsForSet().isMember(REPORTED_ARTICLES_SET, articleID.toString());
		return false;
	}

	// 被檢舉的是留言
	public boolean isMessageReported(Integer messageID) {
//		return redisTemplate.opsForSet().isMember(REPORTED_MESSAGES_SET, messageID.toString());
		return false;
	}

	private Map<String, String> convertProsecuteToMap(Prosecute prosecute) {
		Map<String, String> map = new HashMap<>();
		map.put("prosecuteID", prosecute.getProsecuteID().toString());
		map.put("memberID", prosecute.getGeneralMember().getMemberID().toString());
		map.put("prosecuteReason", prosecute.getProsecuteReason());
		map.put("prosecuteStatus", prosecute.getProsecuteStatus().toString());

		if (prosecute.getArticle() != null) {
			map.put("articleID", prosecute.getArticle().getArticleID().toString());
		}
		if (prosecute.getMessage() != null) {
			map.put("messageID", prosecute.getMessage().getMessageID().toString());
		}
		return map;
	}

	// 同步單個檢舉到 Redis
	private void syncProsecuteToRedis(Prosecute prosecute) {
	    String prosecuteKey = PROSECUTE_HASH_KEY + prosecute.getProsecuteID();
//	    redisTemplate.opsForHash().putAll(prosecuteKey, convertProsecuteToMap(prosecute));

	    if (prosecute.getArticle() != null) {
//	        redisTemplate.opsForSet().add(REPORTED_ARTICLES_SET, prosecute.getArticle().getArticleID().toString());
	    } else if (prosecute.getMessage() != null) {
//	        redisTemplate.opsForSet().add(REPORTED_MESSAGES_SET, prosecute.getMessage().getMessageID().toString());
	    }
	}
	
	//獲得所有檢舉
    public List<Prosecute> getAllProsecutes() {
        return repository.findAll();
    }
    
    //處理被檢舉文章或留言
    @Transactional
    public Prosecute processProsecute(Integer prosecuteID) {
        Prosecute prosecute = repository.findById(prosecuteID)
            .orElseThrow(() -> new EntityNotFoundException("檢舉不存在"));
        
        if (prosecute.getArticle() != null) {
            Article article = prosecute.getArticle();
            article.setArticleStatus(2); // 將文章狀態設為2
            articleSvc.updateArticle(article);
        } else if (prosecute.getMessage() != null) {
            Message message = prosecute.getMessage();
            message.setMessageStatus(2); // 將留言狀態設為2
            messageSvc.updateMessage(message);
        }else {
            throw new IllegalStateException("檢舉既不是針對文章也不是針對留言，ID: " + prosecuteID);
        }

        syncProsecuteToRedis(prosecute); 
        return repository.save(prosecute);
    }
    
    //刪除單筆檢舉資料
    @Transactional
    public void deleteProsecute(Integer prosecuteID) {
        Prosecute prosecute = repository.findById(prosecuteID)
            .orElseThrow(() -> new EntityNotFoundException("檢舉不存在，ID: " + prosecuteID));

        repository.delete(prosecute);
        syncProsecuteToRedis(prosecute);
    }

	// 同步所有檢舉到 Redis
//	public void syncAllProsecutesToRedis() {
//	    List<Prosecute> allProsecutes = repository.findAll();
//	    for (Prosecute prosecute : allProsecutes) {
//	        syncProsecuteToRedis(prosecute);
//	    }
//	    System.out.println("所有檢舉同步完成");
//	}

}