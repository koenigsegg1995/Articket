package com.maddog.articket.controller.login;

import com.maddog.articket.administrator.entity.Administrator;
import com.maddog.articket.administrator.service.impl.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class AdminLoginController {

	
	@Autowired
	AdministratorService adminSvc;
	
	@GetMapping("/adminLogin")
	public String getAdminLogin() {
		return "back-end-admin/adminLogin";
	}
	
	// 管理員登入
	
		@PostMapping("adminLogin")
		public String adminLogin(@RequestParam("administratorAccount") String administratorAccountStr,
				@RequestParam("administratorPassword") String administratorPasswordStr, HttpSession session, Model model) {

			// 驗證帳號格式
			if (administratorAccountStr == null || administratorAccountStr.trim().isEmpty() || !administratorAccountStr.matches("^[a-zA-Z0-9]+$")) {
				model.addAttribute("errorMessage", "請輸入有效的帳號(不能有特殊符號)");
				return "/back-end-admin/adminLogin";
			}
			// 根據帳號獲取會員資料
			Administrator administrator = adminSvc.getAdministratorAccount(administratorAccountStr);

			// 檢查會員是否存在
			if (administrator == null) {
				model.addAttribute("errorMessage", "會員不存在");
				return "/back-end-admin/adminLogin"; // 直接返回登入頁
			}

			// 檢查密碼
			if (administratorPasswordStr.equals(administrator.getAdministratorPassword())) {
				model.addAttribute("administratorAccount", administratorAccountStr);
				session.setAttribute("administratorID", administrator.getAdministratorID());
				return "/back-end-admin/admin"; // 登入成功
			} else {
				model.addAttribute("errorMessage", "密碼錯誤，登入失敗!!!");
				return "/back-end-admin/adminLogin"; // 返回登入頁
			}

		}
		
		@GetMapping("/logout")
		public String logout(HttpSession session) {
			session.invalidate(); // 使會話失效
			return "/back-end-admin/adminLogin"; // 重定向到登入頁
		}
}
