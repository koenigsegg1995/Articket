package com.maddog.articket.controller.article;

import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.generalmember.service.impl.GeneralMemberService;
import com.maddog.articket.articlecollection.service.impl.ArticleCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/articleCollection")
public class ArticleCollectionController {
	
	@Autowired
    ArticleCollectionService articleCollectionSvc;
	
	@Autowired
    GeneralMemberService generalMemberSvc;
		
	
	/*檢查會員是否收藏此文章*/
   @GetMapping("/status/{articleID}")
    public ResponseEntity<?> getCollectionStatus(
            @PathVariable Integer articleID,
            HttpSession session) {
		String memberAccount = (String) session.getAttribute("memberAccount");
        if (memberAccount == null || memberAccount.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("請先登入");
        }
        GeneralMember member = generalMemberSvc.getByMemberAccount(memberAccount);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("無法找到會員信息");
        }
        boolean isCollected = articleCollectionSvc.isArticleCollectedByMember(articleID, member.getMemberID());
        return ResponseEntity.ok(isCollected);
    }
	
	//切換文章的收藏狀態
    @PostMapping("/toggle")
    public ResponseEntity<?> toggleArticleCollection( @RequestParam Integer articleID,
    		HttpSession session) {
    	
    	// 獲取當前登入的會員信息
        String memberAccount = (String) session.getAttribute("memberAccount");
        if (memberAccount == null || memberAccount.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("登入已過期，請重新登入");
        }

        GeneralMember member  = generalMemberSvc.getByMemberAccount(memberAccount);
        if (member  == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("無法找到會員信息，請重新登入");
        }    	
    	
    	boolean isCollected = articleCollectionSvc.toggleArticleCollection(member .getMemberID(), articleID);
        return ResponseEntity.ok(isCollected);
    }
    
    //獲取特定文章的收藏數
    @GetMapping("/count/{articleID}")
    public ResponseEntity<Long> getArticleCollectionCount(@PathVariable Integer articleID) {
        Long count = articleCollectionSvc.getArticleCollectionCount(articleID);
        return ResponseEntity.ok(count);
    }

	 

    
    //Redis 相關錯誤處理
//    @ExceptionHandler(RedisConnectionFailureException.class)
//    public ResponseEntity<String> handleRedisConnectionFailureException(RedisConnectionFailureException e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Redis 連接失敗: " + e.getMessage());
//    }
	
    //member或article相關錯誤
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body("參數錯誤: " + e.getMessage());
    }
	
    //未預期的異常處理
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("發生未預期的錯誤: " + e.getMessage());
    }

}
