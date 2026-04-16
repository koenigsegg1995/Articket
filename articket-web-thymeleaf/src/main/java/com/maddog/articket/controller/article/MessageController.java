package com.maddog.articket.controller.article;

import com.maddog.articket.article.entity.Article;
import com.maddog.articket.board.service.pri.BoardService;
import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.generalmember.service.pri.GeneralMemberService;
import com.maddog.articket.message.dto.MessageForView;
import com.maddog.articket.message.entity.Message;
import com.maddog.articket.board.entity.Board;
import com.maddog.articket.message.service.pri.MessageService;
import lombok.extern.slf4j.Slf4j;
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

/**
 * 文章留言 Controller
 */
@Slf4j
@Controller
@RequestMapping("/messages")
public class MessageController {

	/**
	 * 文章留言 Service
	 */
	@Autowired
	private MessageService messageSvc;

	/**
	 * 會員 Service
	 */
	@Autowired
	private GeneralMemberService generalMemberSvc;

	/**
	 * 文章各版 Service
	 */
	@Autowired
	private BoardService boardSvc;

	/**
	 * 獲取所有會員
	 *
	 * @return 所有會員清單
	 */
	@GetMapping("/members")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getAllMembers() {
	    List<GeneralMember> generalMemberListData = generalMemberSvc.getAll();

	    Map<String, Object> response = new HashMap<>();
	    response.put("generalMemberListData", generalMemberListData);

	    return ResponseEntity.ok(response);
	}

	/**
	 * 獲取會員照片
	 *
	 * @param memberID
	 * 			會員 ID
	 * @return 會員照片
 	 */
    @GetMapping("/picture/{memberID}")
    public ResponseEntity<byte[]> getMemberPicture(@PathVariable Integer memberID) {
        try {
            GeneralMember member = generalMemberSvc.getById(memberID);
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

	/**
	 * 新增留言
	 *
	 * @param message
	 * 			留言
	 * @param session
	 * 			HTTP Session
	 * @return 新增後的留言或錯誤訊息
	 */
	@PostMapping("insert")
	@ResponseBody
	public ResponseEntity<?> insert(@Valid @RequestBody Message message, HttpSession session) {
	    if (message.getArticleId() == null) {
	        return ResponseEntity.badRequest().body("Article ID cannot be null");
	    }

	    // 獲取當前登入的會員資訊
	    String memberAccount = (String) session.getAttribute("memberAccount");
	    if (memberAccount == null || memberAccount.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("登入已過期，請重新登入");
	    }

	    GeneralMember generalMember = generalMemberSvc.getByMemberAccount(memberAccount);
	    if (generalMember == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("無法找到會員，請重新登入");
	    }

	    // 設置留言的作者
	    message.setMemberId(generalMember.getMemberId());
	    
	    try {
	        Message savedMessage = messageSvc.addMessage(message);

			return ResponseEntity.ok(savedMessage);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Error saving message: " + e.getMessage());
	    }
	}

	/**
	 *
	 *
	 * @param messageIDStr
	 * 			留言 ID 字串
	 * @param model
	 * 			ModelMap
	 * @return forum.html
	 * 			社群空間
	 */
	@PostMapping("getOneMmessage_For_Update")
	public String getOneMessage_For_Update(@RequestParam("messageID") String messageIDStr, ModelMap model) {
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

	/**
	 * 更新留言
	 *
	 * @param message
	 * 			留言
	 * @param result
	 * 			BindingResult
	 * @return 更新後留言
	 */
	@PostMapping("update")
	@ResponseBody
	public ResponseEntity<?> update(@Valid @RequestBody Message message, BindingResult result) {
	    if (result.hasErrors()) {
	        return ResponseEntity.badRequest().body(result.getAllErrors());
	    }

	    Message updatedMessage = messageSvc.updateMessage(message);

		return ResponseEntity.ok(updatedMessage); // 請求成功處理並將 updatedMessage 作為響應體,只返回成功狀態，不返回消息內容
	}

	/**
	 * 刪除留言
	 *
	 * @param messageID
	 * 			留言 ID
	 * @return 成功或錯誤訊息
	 */
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

	/**
	 * 特定文章的所有留言
	 *
	 * @param articleID
	 * 			文章 ID
	 * @return 留言清單或錯誤訊息
	 */
    @GetMapping("list/{articleID}")
    public ResponseEntity<?> getMessagesByArticle(@PathVariable Integer articleID) {
        try {
            //List<Message> messages = messageSvc.getMessagesByArticleID(articleID); //original
        	List<MessageForView> messages = messageSvc.getMessagesByArticleId(articleID); //JDBC

			return ResponseEntity.ok()
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .body(messages);
        } catch (Exception e) {
            log.error("Controller: Error fetching messages: {}", e.getMessage());

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error fetching messages: " + e.getMessage());
        }
    }

	/**
	 * 查詢單一留言
	 *
	 * @param messageID
	 * 			留言 ID
	 * @param model
	 * 			ModelMap
	 * @return forum.html
	 * 			社群空間
	 */
	@PostMapping("getOneMessage_For_Display")
	public String getOneMessage_For_Display(@NotEmpty(message="留言編號: 請勿空白")
											@Digits(integer = 4, fraction = 0, message = "留言編號: 請填數字-請勿超過{integer}位數")
											@Min(value = 1, message = "留言編號: 不能小於{value}")
											@Max(value = 100, message = "留言編號: 不能超過{value}")
											@RequestParam("messageID") String messageID,
											ModelMap model) {
		/***************************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		
		/***************************2.開始查詢資料*********************************************/
		Message message = messageSvc.getOneMessage(Integer.valueOf(messageID));
		
		List<Message> list = messageSvc.getAll();
		model.addAttribute("messageListData", list);
		model.addAttribute("article", new Article());
		List<Board> list2 = boardSvc.getAll();
    	model.addAttribute("boardListData",list2);
		
		if (message == null) {
			model.addAttribute("errorMessage", "查無資料");

			return "front-end/forum/forum";
		}
		
		/***************************3.查詢完成,準備轉交(Send the Success view)*****************/
		model.addAttribute("article", message);
		model.addAttribute("getOne_For_Display", "true");

		return "front-end/forum/forum";
	}

	/**
	 * 錯誤處理
	 *
	 * @param req
	 * 			HTTPServletRequest
	 * @param e
	 * 			ConstraintViolationException
	 * @return 錯誤訊息
	 */
    @ExceptionHandler(value = { ConstraintViolationException.class } )
    @ResponseBody
    public ResponseEntity<?> handleError(HttpServletRequest req, ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        List<String> errorMessages = violations.stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.toList());

		return ResponseEntity.badRequest().body(errorMessages);
    }
	
}
