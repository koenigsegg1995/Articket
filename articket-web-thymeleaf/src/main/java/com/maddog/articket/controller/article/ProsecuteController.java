package com.maddog.articket.controller.article;

import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.generalmember.service.pri.GeneralMemberService;
import com.maddog.articket.prosecute.entity.Prosecute;
import com.maddog.articket.prosecute.service.pri.ProsecuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

/**
 * 檢舉 Controller
 */
@RestController
@RequestMapping("/prosecutes")
public class ProsecuteController {

	@Autowired
    private ProsecuteService prosecuteSvc;
	
	@Autowired
    private GeneralMemberService generalMemberSvc;

	@PostMapping
	public ResponseEntity<?> prosecuteContent(@RequestBody Prosecute prosecute,
			HttpSession session) {
		
    	// 獲取當前登入的會員信息
        String memberAccount = (String) session.getAttribute("memberAccount");
        if (memberAccount == null || memberAccount.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("請先登入");
        }

        GeneralMember generalMember  = generalMemberSvc.getByMemberAccount(memberAccount);
        if (generalMember  == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("請先登入");
        } 
        
        // 檢查是檢舉文章還是留言
        if (prosecute.getArticleId() != null) {
            // 文章檢舉
            if (prosecuteSvc.isArticleReported(prosecute.getArticleId())) {
                return ResponseEntity.badRequest().body("此文章已被檢舉過，無法重複檢舉");
            }
        } else if (prosecute.getMessageId() != null) {
            // 留言檢舉
            if (prosecuteSvc.isMessageReported(prosecute.getMessageId())) {
                return ResponseEntity.badRequest().body("此留言已被檢舉過，無法重複檢舉");
            }
        } else {
            return ResponseEntity.badRequest().body("無效的檢舉內容");
        }
        
        // 設置檢舉人ID
        prosecute.setMemberId(generalMember.getMemberId());
		
		prosecuteSvc.prosecuteContent(prosecute);

        return ResponseEntity.ok().build();
	}

    @GetMapping("/article/{id}")
    public ResponseEntity<Boolean> isArticleReported(@PathVariable Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Article ID is required");
        }
        boolean isReported = prosecuteSvc.isArticleReported(id);
        return ResponseEntity.ok(isReported);
    }

    @GetMapping("/message/{id}")
    public ResponseEntity<?> isMessageReported(@PathVariable Integer id, HttpSession session) {
        String memberAccount = (String) session.getAttribute("memberAccount");
        if (memberAccount == null || memberAccount.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("請先登入");
        }

        GeneralMember generalMember = generalMemberSvc.getByMemberAccount(memberAccount);
        if (generalMember == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("請先登入");
        }
        boolean isReported = prosecuteSvc.isMessageReported(id);
        return ResponseEntity.ok(isReported);
    }

    
    //獲取檢舉資料
//    @GetMapping
//    public ResponseEntity<List<Prosecute>> getAllProsecutes() {
//        List<Prosecute> prosecutes = ProsecuteSvc.getAllProsecutes();
//        return ResponseEntity.ok(prosecutes);
//    }
    
//    //更新檢舉
//    @PostMapping("/{id}/process")
//    public ResponseEntity<Prosecute> processProsecute(@PathVariable Integer id) {
//        Prosecute processedProsecute = ProsecuteSvc.processProsecute(id);
//        return ResponseEntity.ok(processedProsecute);
//    }
//    
//    //刪除檢舉
//    @PostMapping("/{id}/delete")
//    public ResponseEntity<Void> deleteProsecute(@PathVariable Integer id) {
//    	ProsecuteSvc.deleteProsecute(id);
//        return ResponseEntity.ok().build();
//    }

    
// 移到 IndexController
    
//    @GetMapping("/adminProsecute")
//    public String getAdminProsecute(Model model) {
//        model.addAttribute("message", "Welcome to Admin Prosecute Page");
//        return "back-end-admin/admin_prosecute";
//    }
//
//    @GetMapping("/adminSidebar")
//    public String getAdminSidebar() {
//        return "back-end-admin/admin_sidebar";
//    }
    


}
