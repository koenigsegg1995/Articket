package com.maddog.articket.controller.generalmember;

import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.generalmember.service.impl.GeneralMemberService;
import com.maddog.articket.membercoupon.service.impl.MemberCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Controller
@Validated
@RequestMapping("/generalmember")
public class MemberIDController {
	
	@Autowired
	GeneralMemberService gmemberSvc;
	
	@Autowired
	MemberCouponService mbcSvc;
	
	/*
	 * This method will be called on select_page.html form submission, handling POST
	 * request It also validates the user input
	 */
	@PostMapping("getOne_For_Display")
	public String getOne_For_Display(
		/***************************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		@NotEmpty(message="會員編號: 請勿空白")
		@Digits(integer = 4, fraction = 0, message = "會員編號: 請填數字-請勿超過{integer}位數")
		@Min(value = 0, message = "會員編號: 不能小於{value}")
		@Max(value = 50, message = "會員編號: 不能超過{value}")
		@RequestParam("memberID") String memberID,
		ModelMap model) {
		System.out.println("Received memberID: " + memberID);
		/***************************2.開始查詢資料*********************************************/
//		EmpService empSvc = new EmpService();
		GeneralMember generalMember = gmemberSvc.getOneGeneralMember(Integer.valueOf(memberID));
		
		List<GeneralMember> list = gmemberSvc.getAll();
		model.addAttribute("generalMemberListData", list);     // for select_page.html 第97 109行用
//		model.addAttribute("memberCoupon", new MemberCoupon());  // for select_page.html 第133行用
//		List<MemberCoupon> list2 = mbcSvc.getAll();
//    	model.addAttribute("memberCoupontListData",list2);    // for select_page.html 第135行用
		
		if (generalMember == null) {
			model.addAttribute("errorMessage", "查無資料");
			return "back-end/generalmember/select_page";
		}
		
		/***************************3.查詢完成,準備轉交(Send the Success view)*****************/
		model.addAttribute("generalMember", generalMember);
		model.addAttribute("getOne_For_Display", "true"); // 旗標getOne_For_Display見select_page.html的第156行 -->
		
//		return "back-end/emp/listOneEmp";  // 查詢完成後轉交listOneEmp.html
		return "back-end/generalmember/select_page"; // 查詢完成後轉交select_page.html由其第158行insert listOneEmp.html內的th:fragment="listOneEmp-div
	}
	
	
	@ExceptionHandler(value = { ConstraintViolationException.class })
	//@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ModelAndView handleError(HttpServletRequest req,ConstraintViolationException e,Model model) {
	    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
	    StringBuilder strBuilder = new StringBuilder();
	    for (ConstraintViolation<?> violation : violations ) {
	          strBuilder.append(violation.getMessage() + "<br>");
	    }
	    //==== 以下第92~96行是當前面第77行返回 /src/main/resources/templates/back-end/emp/select_page.html用的 ====   
//	    model.addAttribute("empVO", new EmpVO());
//    	EmpService empSvc = new EmpService();
		List<GeneralMember> list = gmemberSvc.getAll();
		model.addAttribute("generalMemberListData", list);     // for select_page.html 第97 109行用
//		model.addAttribute("activity", new Activity());  // for select_page.html 第133行用
//		List<Activity> list2 = activitytSvc.getAll();
//    	model.addAttribute("activityListData",list2);    // for select_page.html 第135行用
		String message = strBuilder.toString();
	    return new ModelAndView("back-end/generalmember/select_page", "errorMessage", "請修正以下錯誤:<br>"+message);
	}
}


