package com.maddog.articket.controller.verify;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class VerifyController {

	@GetMapping("verifyPage")
	public String showVerifyPage() {
		return "verifyPage"; // 返回顯示驗證碼輸入的頁面
	}

	@PostMapping("/verifyCode")
	public String verifyCode(@RequestParam("inputCode") String inputCode, HttpSession session, Model model) {
		// 從會話中獲取存儲的驗證碼
		String sessionVerificationCode = (String) session.getAttribute("verificationCode");

		// 檢查用戶輸入的驗證碼是否匹配
		if (sessionVerificationCode != null && sessionVerificationCode.equals(inputCode)) {
			// 驗證成功，跳轉到成功註冊頁面
			model.addAttribute("successMessage", "驗證成功，您已成功註冊！");
			return "successInRegister";
		} else {
			// 驗證失敗，返回驗證頁面並顯示錯誤信息
			model.addAttribute("errorMessage", "驗證碼錯誤，請重新輸入。");
			return "verifyPage";
		}
	}

//	@GetMapping("/verify")
//	public String verifyPage(ModelMap model, HttpSession session) {
//		String verificationCode = (String) session.getAttribute("verificationCode");
//		model.addAttribute("verify", new Verify());
//		model.addAttribute("verificationCode", verificationCode);
//		return "/verify"; // 返回驗證碼輸入頁面
//	}
//
	// 輸入驗證碼
//		@PostMapping("/verify")
//		public String verify(@RequestParam("verificationCode") String inputCode, HttpSession session, ModelMap model) {
//		    String verificationCode = (String) session.getAttribute("verificationCode");
//		    model.addAttribute("verify", new Verify());
//		    if (verificationCode != null && verificationCode.equals(inputCode)) {
//		        model.addAttribute("successMessage", "驗證成功！");
//		        return "successInRegister";
//		    } else {
//		        model.addAttribute("errorMessage", "驗證碼錯誤，請重新輸入。");
//		    }
//
//		    return "verify"; // 返回驗證碼輸入頁面
//		}

//	@GetMapping("verifyPage")
//	public String showVerificationForm(ModelMap model, HttpSession session) {
////		String verificationCode = (String) session.getAttribute("verificationCode");
//	    // 初始化 Verify 物件，並將其添加到模型中
////		model.addAttribute("verificationCode", verificationCode);
//		model.addAttribute("verify", new Verify());
//	    return "verifyPage"; // 返回驗證碼輸入頁面的視圖名稱
//	}
//	
//	@PostMapping("verify-captcha")
//	public String verifyCode(@ModelAttribute("verify") Verify verify, BindingResult result, Model model, HttpSession session) {
//	    // 從 Session 中獲取儲存的驗證碼
//	    String sessionCode  = (String) session.getAttribute("verificationCode");
//
//	    // 獲取用戶輸入的驗證碼
//	    String userCode = verify.getVerificationCode();
//
//	    // 檢查驗證碼是否正確
//	    if (sessionCode  != null && sessionCode .equals(userCode)) {
//	        model.addAttribute("successMessage", "驗證成功！");
//	        // 移除 Session 中的驗證碼（可選）
//	        session.removeAttribute("verificationCode");
//	        return "success"; // 驗證成功後跳轉到的頁面
//	    } else {
//	        model.addAttribute("errorMessage", "驗證碼不正確，請重試。");
//	        return "verifyPage"; // 驗證失敗後返回驗證碼輸入頁面
//	    }
//	}

//    @PostMapping("verify-captcha")
//    public String verifyCaptcha(HttpSession session, String inputCaptcha, Model model) {
//        String verificationCode = (String) session.getAttribute("verificationCode");
//        if (verificationCode != null && verificationCode.equals(inputCaptcha)) {
//            model.addAttribute("message", "驗證成功！");
//            return "successPage"; // 驗證成功的頁面
//        } else {
//            model.addAttribute("message", "驗證失敗，請重新輸入！");
//            return "verifyPage"; // 重新顯示驗證碼頁面
//        }
//    }

}
