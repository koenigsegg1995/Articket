package com.maddog.articket.controller.generalmember;

import com.maddog.articket.article.service.impl.ArticleService;
import com.maddog.articket.email.MailService;
import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.generalmember.service.impl.GeneralMemberService;
import com.maddog.articket.articlecollection.entity.ArticleCollection;
import com.maddog.articket.articlecollection.service.impl.ArticleCollectionService;
import com.maddog.articket.bookticket.entity.BookTicket;
import com.maddog.articket.bookticket.service.impl.BookTicketService;
import com.maddog.articket.orders.entity.Orders;
import com.maddog.articket.orders.service.impl.OrdersService;
import com.maddog.articket.passwordchangeform.PasswordChangeForm;
import com.maddog.articket.ticket.entity.Ticket;
import com.maddog.articket.ticket.service.impl.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/generalmember")
public class GeneralMemberController {

	@Autowired
	GeneralMemberService gmemberSvc;

	@Autowired
	MailService mailService;

	@Autowired
	ArticleService articeSvc;

	@Autowired
	ArticleCollectionService artCollSvc;

	@Autowired
	BookTicketService bookTicketService;

	@Autowired
	TicketService ticketSvc;
	
	@Autowired
	OrdersService ordersSvc;

	/*
	 * This method will serve as addEmp.html handler.
	 */
	@GetMapping("addGeneralMember")
	public String addGeneralMember(ModelMap model) {
		GeneralMember generalMember = new GeneralMember();
		model.addAttribute("generalMember", generalMember);
		return "back-end/generalmember/addGeneralMember";
	}

	/*
	 * This method will be called on addEmp.html form submission, handling POST
	 * request It also validates the user input
	 */

	@PostMapping("insert")
	public String insert(@Valid GeneralMember generalMember, BindingResult result, ModelMap model,
			@RequestParam("memberPicture") MultipartFile[] parts) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中upFiles欄位的FieldError紀錄 --> 見第172行
		result = removeFieldError(generalMember, result, "memberPicture");

		if (parts[0].isEmpty()) { // 使用者未選擇要上傳的圖片時
			model.addAttribute("errorMessage", "大頭貼: 請上傳照片");
		} else {
			for (MultipartFile multipartFile : parts) {
				byte[] buf = multipartFile.getBytes();
				generalMember.setMemberPicture(buf);
			}
		}
		if (result.hasErrors() || parts[0].isEmpty()) {
			return "back-end/generalmember/addGeneralMember";
		}
		/*************************** 2.開始新增資料 *****************************************/
		// EmpService empSvc = new EmpService();
		gmemberSvc.addGeneralMember(generalMember);
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<GeneralMember> list = gmemberSvc.getAll();
		model.addAttribute("generalMemberListData", list);
		model.addAttribute("success", "- (新增成功)");
		return "redirect:/generalmember/listAllGeneralMember"; // 新增成功後重導至IndexController_inSpringBoot.java的第58行@GetMapping("/emp/listAllEmp")
	}

	@GetMapping("register")
	public String register(ModelMap model) {
		GeneralMember generalMember = new GeneralMember();
		model.addAttribute("generalMember", generalMember);
		return "register";
	}

	// 會員註冊
	@PostMapping("register")
	public String register(@Valid GeneralMember generalMember, BindingResult result, ModelMap model,
			@RequestParam("memberPicture") MultipartFile[] parts, HttpSession session) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 檢查生日是否為空
		if (generalMember.getBirthday() == null) {
			result.rejectValue("birthday", "error.birthday", "生日: 請勿空白");
		}

		result = removeFieldError(generalMember, result, "memberPicture");

		if (parts[0].isEmpty()) { // 使用者未選擇要上傳的圖片時
			model.addAttribute("errorMessage", "大頭貼: 請上傳照片");
		} else {
			for (MultipartFile multipartFile : parts) {
				byte[] buf = multipartFile.getBytes();
				generalMember.setMemberPicture(buf);
			}
		}
		if (result.hasErrors() || parts[0].isEmpty()) {
			return "register";
		}

		/*************************** 2.開始新增資料 *****************************************/

		try {
			gmemberSvc.addGeneralMember(generalMember); // 假設您有一個服務方法來保存資料

			String verificationCode = String.valueOf((int) (Math.random() * 900000) + 100000);

			System.out.println("驗證碼: " + verificationCode);

			// 設置有效期為30分鐘
			LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(30);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formattedExpirationTime = expirationTime.format(formatter);

			System.out.println("驗證碼有效期至: " + formattedExpirationTime);

			// 發送驗證郵件
			String subject = "您的驗證碼";
			String messageText = "您的驗證碼是：" + verificationCode + "有效時間30分鐘";
			mailService.sendMail("kai199202232578@gmail.com", subject, messageText);

			// 將驗證碼存入會話
			session.setAttribute("verificationCode", verificationCode);
			model.addAttribute("successMessage", "您的驗證碼已發送至您的郵箱 ");
			return "verifyPage";

		} catch (Exception e) {
			model.addAttribute("errorMessage", "註冊失敗，請稍後再試。");
			return "register"; // 返回註冊表單的視圖名稱
		}

	}

	/*
	 * This method will be called on listAllEmp.html form submission, handling POST
	 * request
	 */

	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("memberID") String memberID, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		// EmpService empSvc = new EmpService();
		GeneralMember generalMember = gmemberSvc.getOneGeneralMember(Integer.valueOf(memberID));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("generalMember", generalMember);
		return "back-end/generalmember/update_generalmember_input"; // 查詢完成後轉交update_emp_input.html
	}

	/*
	 * This method will be called on update_emp_input.html form submission, handling
	 * POST request It also validates the user input
	 */
	@PostMapping("update")
	public String update(@Valid GeneralMember generalMember, BindingResult result, ModelMap model,
			@RequestParam("memberPicture") MultipartFile[] parts, @RequestParam("birthday") String birthdayStr)
			throws IOException {

		Date birthday = null;

		result = removeFieldError(generalMember, result, "birthday");
		{

			if (birthdayStr != null && birthdayStr.trim().length() != 0) {
				try {
					birthday = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(birthdayStr).getTime());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				model.addAttribute("errorMessage", "生日請勿空白");
			}
		}

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中upFiles欄位的FieldError紀錄 --> 見第172行
		result = removeFieldError(generalMember, result, "memberPicture");

		if (parts[0].isEmpty()) { // 使用者未選擇要上傳的新圖片時
			// EmpService empSvc = new EmpService();
			byte[] memberPicture = gmemberSvc.getOneGeneralMember(generalMember.getMemberID()).getMemberPicture();
			generalMember.setMemberPicture(memberPicture);
		} else {
			for (MultipartFile multipartFile : parts) {
				byte[] memberPicture = multipartFile.getBytes();
				generalMember.setMemberPicture(memberPicture);
			}
		}
		if (result.hasErrors()) {
			return "back-end/generalmember/update_generalmember_input";
		}
		/*************************** 2.開始修改資料 *****************************************/
		// EmpService empSvc = new EmpService();
		gmemberSvc.updateGeneralMember(generalMember);

		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		generalMember = gmemberSvc.getOneGeneralMember(Integer.valueOf(generalMember.getMemberID()));
		model.addAttribute("generalMember", generalMember);
		return "back-end/generalmember/listOneGeneralMember"; // 修改成功後轉交listOneEmp.html
	}

	/*
	 * This method will be called on listAllEmp.html form submission, handling POST
	 * request
	 */

	@PostMapping("delete")
	public String delete(@RequestParam("memberID") String memberID, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始刪除資料 *****************************************/
		// EmpService empSvc = new EmpService();
		gmemberSvc.deleteGeneralMember(Integer.valueOf(memberID));
		/*************************** 3.刪除完成,準備轉交(Send the Success view) **************/
		List<GeneralMember> list = gmemberSvc.getAll();
		model.addAttribute("generalMemberListData", list);
		model.addAttribute("success", "- (刪除成功)");
		return "back-end/generalmember/listAllGeneralMember"; // 刪除完成後轉交listAllEmp.html
	}

	/*
	 * 第一種作法 Method used to populate the List Data in view. 如 : <form:select
	 * path="deptno" id="deptno" items="${deptListData}" itemValue="deptno"
	 * itemLabel="dname" />
	 */
//	@ModelAttribute("deptListData")
//	protected List<DeptVO> referenceListData() {
//		// DeptService deptSvc = new DeptService();
//		List<DeptVO> list = deptSvc.getAll();
//		return list;
//	}

	/*
	 * 【 第二種作法 】 Method used to populate the Map Data in view. 如 : <form:select
	 * path="deptno" id="deptno" items="${depMapData}" />
	 */
//	@ModelAttribute("deptMapData") //
//	protected Map<Integer, String> referenceMapData() {
//		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
//		map.put(10, "財務部");
//		map.put(20, "研發部");
//		map.put(30, "業務部");
//		map.put(40, "生管部");
//		return map;
//	}

	// 去除BindingResult中某個欄位的FieldError紀錄
	public BindingResult removeFieldError(GeneralMember generalMember, BindingResult result, String removedFieldname) {
		List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
				.filter(fieldname -> !fieldname.getField().equals(removedFieldname)).collect(Collectors.toList());
		result = new BeanPropertyBindingResult(generalMember, "generalMember");
		for (FieldError fieldError : errorsListToKeep) {
			result.addError(fieldError);
		}
		return result;
	}

	/*
	 * This method will be called on select_page.html form submission, handling POST
	 * request
	 */

	@PostMapping("listGeneralmember_ByCompositeQuery")
	public String listAllGeneralMember(HttpServletRequest req, Model model) {
		Map<String, String[]> map = req.getParameterMap();
		List<GeneralMember> list = gmemberSvc.getAll(map);
		model.addAttribute("generalMemberListData", list); // for listAllEmp.html 第85行用
		return "back-end/generalmember/listAllGeneralMember";
	}

	@GetMapping("/member/{id}")
	public String getMemberById(@PathVariable("id") Integer id, Model model) {
		GeneralMember member = gmemberSvc.getById(id); // 獲取會員資料
		model.addAttribute("member", member); // 將會員資料添加到模型
		return "memberCenter"; // 返回前端模板名稱
	}

	// 會員收藏文章列表
//	@GetMapping("/myCollections")
//	public String showAllArticles(Model model) {
//	    List<Article> articles = articeSvc.getAll();
//	    model.addAttribute("articles", articles);
//	    return "front-end/generalmember/myCollections";
//	}

	// 會員收藏文章列表
	@GetMapping("myCollections")
	public String showMyCollections(Model model, HttpSession session) {
		// 假設你在session中存儲了用戶ID
		Integer memberID = (Integer) session.getAttribute("memberID");

		if (memberID == null) {
			// 如果用戶未登錄，重定向到登錄頁面
			return "redirect:/login";
		}

		List<ArticleCollection> collections = artCollSvc.getCollectionsByMemberID(memberID);
		model.addAttribute("collections", collections);
		return "front-end/generalmember/myCollections";
	}

	// 會員票券訂單列表
	@GetMapping("myTicketOrders")
	public String showMyTicketOrders(Model model, HttpSession session) {
		Integer memberID = (Integer) session.getAttribute("memberID");

		if (memberID == null) {
			// 如果用戶未登錄，重定向到登錄頁面
			return "redirect:/login"; // 確保這是正確的登錄頁面路徑
		}

		List<BookTicket> orders = bookTicketService.getTicketOrdersByMemberId(memberID);
		model.addAttribute("orders", orders);

		return "front-end/generalmember/myTicketOrders"; // 返回顯示訂單的視圖名稱
	}

	// 會員中心我的票券
	@GetMapping("/myTickets")
	public String myTickets(Model model, HttpSession session) {
		Integer memberID = (Integer) session.getAttribute("memberID");
		if (memberID == null) {
			return "redirect:/generalmember/login";
		}
		List<Ticket> tickets = ticketSvc.getTicketsByMemberID(memberID);
		model.addAttribute("tickets", tickets);
		return "front-end/generalmember/myTickets";
	}
	
	// 會員中心商品訂單
	@GetMapping("/myOrders")
    public String myOrders(Model model, HttpSession session) {
        Integer memberID = (Integer) session.getAttribute("memberID");
        if (memberID == null) {
            return "redirect:/generalmember/login";
        }
        List<Orders> orders = ordersSvc.getOrdersByMemberID(memberID);
        model.addAttribute("orders", orders);
        return "front-end/generalmember/myOrders";
    }
	
	
	
    // 顯示修改密碼的表單
	@GetMapping("/changePassword")
    public String showChangePasswordForm(Model model) {
        model.addAttribute("passwordForm", new PasswordChangeForm());
        return "front-end/generalmember/changePassword";
    }
	
	// 處理密碼修改請求
	@PostMapping("/changePassword")
	public String changePassword(@ModelAttribute("passwordForm") @Valid PasswordChangeForm form,
	                             BindingResult bindingResult,
	                             HttpSession session,
	                             RedirectAttributes redirectAttributes) {
	    if (bindingResult.hasErrors()) {
	        return "front-end/generalmember/changePassword";
	    }

	    Integer memberID = (Integer) session.getAttribute("memberID");
	    if (memberID == null) {
	        return "redirect:/generalmember/login";
	    }

	    try {
	    	gmemberSvc.changePassword(memberID, form.getCurrentPassword(), form.getNewPassword());
	        redirectAttributes.addFlashAttribute("passwordChangeSuccess", true);
	        return "redirect:/generalmember/memberCenter";
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
	        return "redirect:/generalmember/changePassword";
	    }
    }

	
	// 會員忘記密碼
	@PostMapping("/forgotPassword")
    @ResponseBody
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
        try {
        	gmemberSvc.sendRecoveryEmail(email);
            return ResponseEntity.ok().body("恢復郵件已發送，請查看您的郵箱。");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("發送恢復郵件失敗：" + e.getMessage());
        }
    }


	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

}
