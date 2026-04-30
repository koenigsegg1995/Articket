package com.maddog.articket.controller.partnermember;

import com.maddog.articket.partnermember.dto.PartnerMemberQueryCondition;
import com.maddog.articket.partnermember.entity.PartnerMember;
import com.maddog.articket.partnermember.service.pri.PartnerMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 廠商會員 Controller
 */
@Controller
@RequestMapping("/partnermember")
public class PartnerController {

	/**
	 * 廠商會員 Service
	 */
	@Autowired
    private PartnerMemberService partnerSvc;

	@GetMapping("addPartnerMember")
	public String addPartnerMember(ModelMap model) {
		PartnerMember partnerMember = new PartnerMember();

		model.addAttribute("partnerMember", partnerMember);

		return "back-end/partnermember/addPartnerMember";
	}
	
	@GetMapping("partnerRegister")
	public String partnerRegsiter(ModelMap model) {
		PartnerMember partnerMember = new PartnerMember();

		model.addAttribute("partnerMember", partnerMember);

		return "partnerRegister";
	}

	@PostMapping("insert")
	public String insert(@Valid PartnerMember partnerMember, BindingResult result, ModelMap model){
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中upFiles欄位的FieldError紀錄 --> 見第172行
		result = removeFieldError(partnerMember, result);

		
		if (result.hasErrors()) {
			return "back-end/partnermember/addPartnerMember";
		}
		/*************************** 2.開始新增資料 *****************************************/
		partnerSvc.addPartnerMember(partnerMember);

		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<PartnerMember> list = partnerSvc.getAll();

		model.addAttribute("partnerMemberListData", list);
		model.addAttribute("success", "- (新增成功)");

		return "redirect:/partnermember/listAllPartnerMember"; // 新增成功後重導至IndexController_inSpringBoot.java的第58行@GetMapping("/emp/listAllEmp")
	}
	
	// 廠商註冊
	
	@PostMapping("partnerRegister")
	public String partnerRegister(@Valid PartnerMember partnerMember, BindingResult result, Model model) {
		result = removeFieldError(partnerMember, result);
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		if (result.hasErrors()) {
			return "partnerRegister";
		}
		
		/*************************** 2.開始新增資料 *****************************************/
		try {
			partnerSvc.addPartnerMember(partnerMember); // 假設您有一個服務方法來保存資料
        } catch (Exception e) {
            model.addAttribute("errorMessage", "註冊失敗，請稍後再試。");

			return "partnerRegister"; // 返回註冊表單的視圖名稱
        }
		
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
        model.addAttribute("successMessage", "註冊成功！");

		return "successInRegister"; // 返回成功視圖的名稱
	}

	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("partnerID") String partnerID, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/

		/*************************** 2.開始查詢資料 *****************************************/
		PartnerMember partnerMember = partnerSvc.getOnePartnerMember(Integer.valueOf(partnerID));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("partnerMember", partnerMember);

		return "back-end/partnermember/update_partnermember_input"; // 查詢完成後轉交update_emp_input.html
	}

	@PostMapping("update")
	public String update(@Valid PartnerMember partnerMember, BindingResult result, ModelMap model){
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		result = removeFieldError(partnerMember, result);
		
		if (result.hasErrors()) {
			return "back-end/partnermember/update_partnermember_input";
		}

		/*************************** 2.開始修改資料 *****************************************/
		partnerSvc.updatePartnerMember(partnerMember);

		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		partnerMember = partnerSvc.getOnePartnerMember(partnerMember.getPartnerId());
		model.addAttribute("partnerMember", partnerMember);

		return "back-end/partnermember/listOnePartnerMember"; // 修改成功後轉交listOneEmp.html
	}

	@PostMapping("delete")
	public String delete(@RequestParam("partnerID") String partnerID, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/

		/*************************** 2.開始刪除資料 *****************************************/
		partnerSvc.deletePartnerMember(Integer.valueOf(partnerID));

		/*************************** 3.刪除完成,準備轉交(Send the Success view) **************/
		List<PartnerMember> list = partnerSvc.getAll();

		model.addAttribute("partnermemberListData", list);
		model.addAttribute("success", "- (刪除成功)");

		return "back-end/partnermember/listAllPartnerMember";
	}

	// 去除BindingResult中某個欄位的FieldError紀錄
	public BindingResult removeFieldError(PartnerMember partnerMember, BindingResult result) {
		List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
				.collect(Collectors.toList());
		result = new BeanPropertyBindingResult(partnerMember, "partnerMember");
		for (FieldError fieldError : errorsListToKeep) {
			result.addError(fieldError);
		}
		return result;
	}

	@PostMapping("/listpartnerMember_ByCompositeQuery")
	public String listAllPartnerMember(@ModelAttribute PartnerMemberQueryCondition condition, Model model) {
	    List<PartnerMember> list = partnerSvc.findByCondition(condition);

	    model.addAttribute("partnerMemberListData", list);

		return "back-end/partnermember/listAllPartnerMember";
	}

}
