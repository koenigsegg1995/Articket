package com.maddog.articket.controller.article;

import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.generalmember.service.pri.GeneralMemberService;
import com.maddog.articket.heart.service.impl.HeartServiceImpl;
import com.maddog.articket.heart.service.pri.HeartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

/**
 * 文章點讚 Controller
 */
@Controller
@RequestMapping("/heart")
public class HeartController {

    /**
     * 文章點讚 Service
     */
	@Autowired
    private HeartService heartSvc;

    /**
     * 會員 Service
     */
	@Autowired
    private GeneralMemberService generalMemberSvc;
	
	/**
     * 檢查會員是否對此文章按過讚
     *
     * @param articleID
     *          文章 ID
     * @param session
     *          HTTP Session
     * @return 會員是否對此文章按過讚
     */
	@GetMapping("/status/{articleID}")
    public ResponseEntity<?> getHeartStatus(@PathVariable Integer articleID, HttpSession session) {
		String memberAccount = (String) session.getAttribute("memberAccount");
        if (memberAccount == null || memberAccount.isBlank()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("請先登入");
        }

        GeneralMember member = generalMemberSvc.getByMemberAccount(memberAccount);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("無法找到會員信息");
        }

        boolean isLiked = heartSvc.isArticleLikedByMember(articleID, member.getMemberId());

        return ResponseEntity.ok(isLiked);
    }
	
	
	/**
     * 切換文章的按讚狀態
     *
     * @param articleID
     *          文章 ID
     * @param session
     *          HTTP Session
     * @return 切換後的按讚狀態
     */
	@PostMapping("/toggle")
	public ResponseEntity<?> toggleHeart( @RequestParam Integer articleID, HttpSession session) {
    	// 獲取當前登入的會員信息
        String memberAccount = (String) session.getAttribute("memberAccount");
        if (memberAccount == null || memberAccount.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("登入已過期，請重新登入");
        }

        GeneralMember member  = generalMemberSvc.getByMemberAccount(memberAccount);
        if (member  == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("無法找到會員，請重新登入");
        }
        
        boolean isLiked = heartSvc.toggleHeart(member.getMemberId(), articleID);

        return ResponseEntity.ok(isLiked);
    }

    /**
     * 獲取特定文章的按讚數
     *
     * @param articleID
     *          文章 ID
     * @return 特定文章的按讚數
     */
    @GetMapping("/count/{articleID}")
    public ResponseEntity<Long> getHeartCount(@PathVariable Integer articleID) {
        Long count = heartSvc.getHeartCount(articleID);

        return ResponseEntity.ok(count);
    }

//    /**
//     * Redis 相關錯誤處理
//     *
//     * @param e
//     *          Redis 連接失敗異常
//     * @return 錯誤訊息
//     */
//    @ExceptionHandler(RedisConnectionFailureException.class)
//    public ResponseEntity<String> handleRedisConnectionFailureException(RedisConnectionFailureException e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Redis 連接失敗: " + e.getMessage());
//    }

    /**
     * member或article相關錯誤
     *
     * @param e
     *          參數錯誤異常
     * @return 錯誤訊息
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body("參數錯誤: " + e.getMessage());
    }

    /**
     * 未預期的異常處理
     *
     * @param e
     *          未預期的異常
     * @return 錯誤訊息
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("發生未預期的錯誤: " + e.getMessage());
    }
	
}
