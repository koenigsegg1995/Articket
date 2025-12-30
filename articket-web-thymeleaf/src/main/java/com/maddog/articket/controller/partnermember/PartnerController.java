package com.maddog.articket.controller.partnermember;

import com.maddog.articket.partnermember.entity.PartnerMember;
import com.maddog.articket.partnermember.service.impl.PartnerMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/partnermember")
public class PartnerController {
	
	Timestamp now = Timestamp.valueOf(LocalDateTime.now());

	@Autowired
	PartnerMemberService partnerSvc;
	



	/*
	 * This method will serve as addEmp.html handler.
	 */
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

	/*
	 * This method will be called on addEmp.html form submission, handling POST request It also validates the user input
	 */
	@PostMapping("insert")
	public String insert(@Valid PartnerMember partnerMember, BindingResult result, ModelMap model){

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中upFiles欄位的FieldError紀錄 --> 見第172行
		result = removeFieldError(partnerMember, result);

		
		if (result.hasErrors()) {
			return "back-end/partnermember/addPartnerMember";
		}
		/*************************** 2.開始新增資料 *****************************************/
		// EmpService empSvc = new EmpService();
		partnerSvc.addPartnerMember(partnerMember);
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<PartnerMember> list = partnerSvc.getAll();
		model.addAttribute("partnerMemberListData", list);
		model.addAttribute("success", "- (新增成功)");
		return "redirect:/partnermember/listAllPartnerMember"; // 新增成功後重導至IndexController_inSpringBoot.java的第58行@GetMapping("/emp/listAllEmp")
	}
	
	// 廠商註冊
	
	@PostMapping("partnerRegister")
	public String partnerRegister(@Valid PartnerMember partnerMember, BindingResult result, ModelMap model) {
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

	/*
	 * This method will be called on listAllEmp.html form submission, handling POST request
	 */
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("partnerID") String partnerID, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		// EmpService empSvc = new EmpService();
		PartnerMember partnerMember = partnerSvc.getOnePartnerMember(Integer.valueOf(partnerID));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("partnerMember", partnerMember);
		return "back-end/partnermember/update_partnermember_input"; // 查詢完成後轉交update_emp_input.html
	}

	/*
	 * This method will be called on update_emp_input.html form submission, handling POST request It also validates the user input
	 */
	@PostMapping("update")
	public String update(@Valid PartnerMember partnerMember, BindingResult result, ModelMap model){

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中upFiles欄位的FieldError紀錄 --> 見第172行
		result = removeFieldError(partnerMember, result);
		
		
		
		if (result.hasErrors()) {
			return "back-end/partnermember/update_partnermember_input";
		}
		/*************************** 2.開始修改資料 *****************************************/
		// EmpService empSvc = new EmpService();
		partnerSvc.updatePartnerMember(partnerMember);

		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		partnerMember = partnerSvc.getOnePartnerMember(partnerMember.getPartnerId());
		model.addAttribute("partnerMember", partnerMember);
		return "back-end/partnermember/listOnePartnerMember"; // 修改成功後轉交listOneEmp.html
	}

	/*
	 * This method will be called on listAllEmp.html form submission, handling POST request
	 */
	@PostMapping("delete")
	public String delete(@RequestParam("partnerID") String partnerID, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始刪除資料 *****************************************/
		// EmpService empSvc = new EmpService();
		partnerSvc.deletePartnerMember(Integer.valueOf(partnerID));
		/*************************** 3.刪除完成,準備轉交(Send the Success view) **************/
		List<PartnerMember> list = partnerSvc.getAll();
		model.addAttribute("partnermemberListData", list);
		model.addAttribute("success", "- (刪除成功)");
		return "back-end/partnermember/listAllPartnerMember"; // 刪除完成後轉交listAllEmp.html
	}

	/*
	 * 第一種作法 Method used to populate the List Data in view. 如 : 
	 * <form:select path="deptno" id="deptno" items="${deptListData}" itemValue="deptno" itemLabel="dname" />
	 */
//	@ModelAttribute("activityListData")
//	protected List<Activity> referenceListData() {
//		// DeptService deptSvc = new DeptService();
//		List<Activity> list = activitytSvc.getAll();
//		return list;
//	}

	/*
	 * 【 第二種作法 】 Method used to populate the Map Data in view. 如 : 
	 * <form:select path="deptno" id="deptno" items="${depMapData}" />
	 */
//	@ModelAttribute("activityMapData") //
//	protected Map<Integer, String> referenceMapData() {
//		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
//		map.put(1, "IVE Live Concert");
//		map.put(2, "SM Town Concert");
//		return map;
//	}

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
	
	/*
	 * This method will be called on select_page.html form submission, handling POST request
	 */
	@PostMapping("/listpartnerMember_ByCompositeQuery")
	public String listAllPartnerMember(HttpServletRequest req, Model model) {
	    Map<String, String[]> map = req.getParameterMap();
	    List<PartnerMember> list = partnerSvc.getAll(map);
	    model.addAttribute("partnerMemberListData", list); // for listAllEmp.html 第85行用
	    return "back-end/partnermember/listAllPartnerMember";
	}


}
