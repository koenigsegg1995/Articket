package com.maddog.articket.controller.article;

import com.maddog.articket.article.entity.Article;
import com.maddog.articket.article.service.pri.ArticleService;
import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.generalmember.service.impl.GeneralMemberService;
import com.maddog.articket.message.entity.Message;
import com.maddog.articket.message.service.impl.MessageService;
import com.maddog.articket.articleimg.service.impl.ArticleImgService;
import com.maddog.articket.board.entity.Board;
import com.maddog.articket.board.service.impl.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/messages")
public class MessageController {
	
	@Autowired
	private MessageService messageSvc;
	
	@Autowired
	private GeneralMemberService generalMemberSvc;

	@Autowired
	private BoardService boardSvc;

	//獲取所有會員
	@GetMapping("/members")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getAllMembers() {
	    List<GeneralMember> generalMemberListData = generalMemberSvc.getAll();

	    Map<String, Object> response = new HashMap<>();
	    response.put("generalMemberListData", generalMemberListData);

	    return ResponseEntity.ok(response);
	}
	

	 // 獲取會員照片
    @GetMapping("/picture/{memberID}")
    public ResponseEntity<byte[]> getMemberPicture(@PathVariable Integer memberID) {
        try {
            GeneralMember member = generalMemberSvc.getOneGeneralMember(memberID);
            if (member != null && member.getMemberPicture() != null) {
                return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(member.getMemberPicture());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

	
	//新增留言
	@PostMapping("insert")
	@ResponseBody
	public ResponseEntity<?> insert(@Valid @RequestBody Message message, HttpSession session) {
	    if (message.getArticle() == null || message.getArticleId() == null) {
	        return ResponseEntity.badRequest().body("Article ID cannot be null");
	    }
	    // 獲取當前登入的會員信息
	    String memberAccount = (String) session.getAttribute("memberAccount");
	    if (memberAccount == null || memberAccount.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("登入已過期，請重新登入");
	    }

	    GeneralMember generalMember = generalMemberSvc.getByMemberAccount(memberAccount);
	    if (generalMember == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("無法找到會員資料，請重新登入");
	    }

	    // 設置留言的作者
	    message.setGeneralMember(generalMember);
	    
	    try {
	        Message savedMessage = messageSvc.addMessage(message);
	        return ResponseEntity.ok(savedMessage);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Error saving message: " + e.getMessage());
	    }
	}
	


	@PostMapping("getOneMmessage_For_Update")
	public String getOneMessage_For_Update(
			@RequestParam("messageID") String messageIDStr,
			ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		
		Integer messageID = Integer.valueOf(messageIDStr);

		/*************************** 2.開始查詢資料 *****************************************/
		Message message = messageSvc.getOneMessage(messageID);
		
		List<Message> list = messageSvc.getAll();
		model.addAttribute("messageListData", list);
	 

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("message", message);
		return "front-end/forum/forum"; 
	}

	

	//更新留言
	@PostMapping("update")
	@ResponseBody
	public ResponseEntity<?> update(@Valid @RequestBody Message message, BindingResult result) {
	    if (result.hasErrors()) {
	        return ResponseEntity.badRequest().body(result.getAllErrors());
	    }

	    Message updatedMessage = messageSvc.updateMessage(message);
	    return ResponseEntity.ok(updatedMessage); //請求成功處理並將updatedMessage作為響應體,只返回成功狀態，不返回消息內容
	}
	

	//刪除留言
	@PostMapping("delete")
	@ResponseBody
	public ResponseEntity<?> delete(@RequestParam("messageID") String messageID) {  
	    try {
	        messageSvc.deleteMessage(Integer.valueOf(messageID));
	        return ResponseEntity.ok().build();
	    } catch (NumberFormatException e) {
	        return ResponseEntity.badRequest().body("Invalid message ID");
	    }
	}
	


	//特定文章的所有留言
    @GetMapping("list/{articleID}")
    public ResponseEntity<?> getMessagesByArticle(@PathVariable Integer articleID) {
        try {
            //List<Message> messages = messageSvc.getMessagesByArticleID(articleID); //original
        	List<Message> messages = messageSvc.getMessagesByArticleIdDao(articleID); //JDBC
            return ResponseEntity.ok()
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .body(messages);
        } catch (Exception e) {
            System.err.println("Controller: Error fetching messages: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error fetching messages: " + e.getMessage());
        }
    }
	

	
	/*
	 * This method will be called on select_page.html form submission, handling POST
	 * request It also validates the user input
	 */
	@PostMapping("getOneMessage_For_Display")
	public String getOneMessage_For_Display(
		/***************************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		@NotEmpty(message="留言編號: 請勿空白")
		@Digits(integer = 4, fraction = 0, message = "留言編號: 請填數字-請勿超過{integer}位數")
		@Min(value = 1, message = "留言編號: 不能小於{value}")
		@Max(value = 100, message = "留言編號: 不能超過{value}")
		@RequestParam("messageID") String messageID,
		ModelMap model) {
		
		
		/***************************2.開始查詢資料*********************************************/
//		ArticleService articleSvc = new ArticleService();
		Message message = messageSvc.getOneMessage(Integer.valueOf(messageID));
		
		List<Message> list = messageSvc.getAll();
		model.addAttribute("messageListData", list);     // for select_page.html 第97 109行用
		model.addAttribute("article", new Article());  // for select_page.html 第133行用
		List<Board> list2 = boardSvc.getAll();
    	model.addAttribute("boardListData",list2);    // for select_page.html 第135行用
		
		if (message == null) {
			model.addAttribute("errorMessage", "查無資料");
			return "front-end/forum/forum";
		}
		
		/***************************3.查詢完成,準備轉交(Send the Success view)*****************/
		model.addAttribute("article", message);
		model.addAttribute("getOne_For_Display", "true"); // 旗標getOne_For_Display見select_page.html的第156行 -->
		
//		return "front-end/forum/listOneArticle";  // 查詢完成後轉交listOneArticle.html
		return "front-end/forum/forum"; // 查詢完成後轉交select_page.html由其第158行insert listOneEmp.html內的th:fragment="listOneEmp-div
	}
	

	
	
	


	//錯誤處理
    @ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseBody
    public ResponseEntity<?> handleError(HttpServletRequest req, ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        List<String> errorMessages = violations.stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errorMessages);
    }

	

	
}
