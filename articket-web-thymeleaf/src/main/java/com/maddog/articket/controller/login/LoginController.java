package com.maddog.articket.controller.login;

import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.generalmember.service.impl.GeneralMemberService;
import com.maddog.articket.partnermember.entity.PartnerMember;
import com.maddog.articket.partnermember.service.impl.PartnerMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = { "/generalmember", "/partnermember" })
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	GeneralMemberService gmemberSvc;

	@Autowired
	PartnerMemberService partnerSvc;

	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}

	@GetMapping("/partnerLogin")
	public String getpartnerLogin() {
		return "partnerLogin";
	}

	// 會員登入
	@PostMapping("login")
	public String login(@RequestParam("memberAccount") String memberAccountStr,
	                    @RequestParam("memberPassword") String memberPasswordStr,
	                    @RequestParam(value = "rememberMe", required = false) boolean rememberMe,
	                    HttpSession session, HttpServletResponse response, Model model) {

	    // 驗證帳號格式
	    if (isInvalidEmail(memberAccountStr)) {
	        model.addAttribute("errorMessage", "請輸入有效的帳號(email)");
	        return "login";
	    }

	    // 根據帳號獲取會員資料
	    GeneralMember generalMember = gmemberSvc.getByMemberAccount(memberAccountStr);

	    // 檢查會員是否存在
	    if (generalMember == null) {
	        model.addAttribute("errorMessage", "會員不存在");
	        return "login"; // 直接返回登入頁
	    }

	    // 檢查密碼
	    if (isPasswordCorrect(memberPasswordStr, generalMember.getMemberPassword())) {
	        session.setAttribute("memberID", generalMember.getMemberID());
	        session.setAttribute("memberName", generalMember.getMemberName());
	        session.setAttribute("memberAccount", generalMember.getMemberAccount());

	        // 登入成功後，處理「記住帳號」
	        if (rememberMe) {
	            Cookie cookie = new Cookie("memberAccount", memberAccountStr);
	            cookie.setMaxAge(60 * 60 * 24 * 30); // 30 天
	            response.addCookie(cookie);
	        }

	        return "success"; // 登入成功
	    } else {
	        model.addAttribute("errorMessage", "密碼錯誤，登入失敗!!!");
	        return "login"; // 返回登入頁
	    }
	}

	// 會員中心
	@GetMapping("/memberCenter")
	public String memberCenter(HttpSession session, Model model, @RequestParam(required = false) Boolean updated) {
		System.out.println("memberCenter method called!");

		logger.info("Entering memberCenter method");

		if (updated != null && updated) {
			System.out.println("Redirected from updateMember");
		}

		if (session.getAttribute("memberAccount") == null) {
			logger.info("User not logged in, redirecting to login");
			return "redirect:/login";
		}

		String memberAccount = (String) session.getAttribute("memberAccount");
		logger.info("Fetching data for member account: {}", memberAccount);

		GeneralMember generalMember = gmemberSvc.getByMemberAccount(memberAccount);

		if (generalMember == null) {
			logger.warn("Member not found for account: {}", memberAccount);
			return "redirect:/login";
		}

		logger.info("Member data retrieved successfully");
		model.addAttribute("generalMember", generalMember);

		return "front-end/generalmember/memberCenter";
	}

	// 修改會員中心編輯頁面
	@GetMapping("/editMember/{memberID}")
	public String showEditMemberForm(@PathVariable Integer memberID, HttpSession session, Model model) {
		// 檢查用戶是否已登入
		if (session.getAttribute("memberAccount") == null) {
			return "redirect:/generalmember/login";
		}

		// 從數據庫獲取會員資料
		GeneralMember generalMember = gmemberSvc.getOneGeneralMember(memberID);

		if (generalMember == null) {
			// 處理找不到會員的情況
			return "redirect:/memberCenter";
		}

		// 將會員資料添加到模型中
		model.addAttribute("generalMember", generalMember);

		return "front-end/generalmember/editMember"; // 返回編輯頁面的視圖名稱
	}

	// 更新會員中心資料
	@PostMapping("/updateMember")
	public String updateMember(@ModelAttribute GeneralMember generalMember,
	                           HttpSession session, 
	                           RedirectAttributes redirectAttributes) {
	    
	    System.out.println("=====================");
	    System.out.println("updateMember CALLED!");
	    System.out.println("=====================");
	    
	    logger.info("Entering updateMember method");
	    
	    if (session.getAttribute("memberAccount") == null) {
	        logger.info("User not logged in, redirecting to login");
	        return "redirect:/login";
	    }

	    try {
	        // 獲取數據庫中現有的會員資料
	        GeneralMember existingMember = gmemberSvc.getOneGeneralMember(generalMember.getMemberID());
	        
	        // 如果有新的文件上傳，則更新 memberPicture
//	        if (generalMember.getMemberPictureFile() != null && !generalMember.getMemberPictureFile().isEmpty()) {
//	            generalMember.setMemberPicture(generalMember.getMemberPictureFile().getBytes());
//	        } else {
//	            // 如果沒有新的文件上傳，保留原有的 memberPicture
//	            generalMember.setMemberPicture(existingMember.getMemberPicture());
//	        }

	        // 更新其他可能沒有在表單中提交的字段
	        generalMember.setMemberAccount(existingMember.getMemberAccount());
	        generalMember.setMemberPassword(existingMember.getMemberPassword());
	        generalMember.setNationalID(existingMember.getNationalID());
	        generalMember.setBirthday(existingMember.getBirthday());
	        // ... 其他需要保留的字段

	        logger.info("Updating member data for account: {}", generalMember.getMemberAccount());
	        gmemberSvc.updateGeneralMember(generalMember);
	        logger.info("Member data updated successfully");

	        redirectAttributes.addFlashAttribute("message", "資料已成功更新！");
	        return "redirect:/generalmember/memberCenter";
	    } catch (Exception e) {
	        logger.error("Error updating member data", e);
	        redirectAttributes.addFlashAttribute("error", "更新資料時發生錯誤：" + e.getMessage());
	        return "redirect:/generalmember/memberCenter";
	    }
	}



	// 驗證電子郵件格式的輔助方法
	private boolean isInvalidEmail(String email) {
		return email == null || email.trim().isEmpty()
				|| !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
	}

	// 檢查密碼是否正確的輔助方法
	private boolean isPasswordCorrect(String inputPassword, String storedPassword) {
		return inputPassword.equals(storedPassword);
	}

	// 廠商登入
	@PostMapping("partnerLogin")
	public String partnerLogin(@RequestParam("taxID") String taxIDStr,
			@RequestParam("partnerPassword") String partnerPasswordStr, HttpSession session, Model model) {

		// 驗證帳號格式
		if (taxIDStr == null || taxIDStr.trim().isEmpty() || !taxIDStr.matches("^\\d{8}$")) {
			model.addAttribute("errorMessage", "請輸入有效的帳號(統一編號8位數字)");
			return "partnerLogin";
		}
		// 根據帳號獲取會員資料
		PartnerMember partnerMember = partnerSvc.getByTaxID(taxIDStr);

		// 檢查會員是否存在
		if (partnerMember == null) {
			model.addAttribute("errorMessage", "會員不存在");
			return "partnerLogin"; // 直接返回登入頁
		}

		// 檢查密碼
		if (partnerPasswordStr.equals(partnerMember.getPartnerPassword())) {
			model.addAttribute("taxID", taxIDStr);
			session.setAttribute("partnerName", partnerMember.getPartnerName());
			session.setAttribute("partnerID", partnerMember.getPartnerID());
			session.setAttribute("taxID", partnerMember.getTaxID());
			return "successpartner"; // 登入成功
		} else {
			model.addAttribute("errorMessage", "密碼錯誤，登入失敗!!!");
			return "partnerLogin"; // 返回登入頁
		}

	}
	
	// 廠商會員中心
	@GetMapping("/partnerCenter")
	public String partnerCenter(HttpSession session, Model model, @RequestParam(required = false) Boolean updated) {
		System.out.println("partnerCenter method called!");

		logger.info("Entering partnerCenter method");

		if (updated != null && updated) {
			System.out.println("Redirected from updatePartner");
		}

		if (session.getAttribute("taxID") == null) {
			logger.info("User not logged in, redirecting to login");
			return "redirect:/partnermember/partnerLogin";
		}

		String taxID = (String) session.getAttribute("taxID");
		logger.info("Fetching data for member account: {}", taxID);

		PartnerMember partnerMember = partnerSvc.getByTaxID(taxID);

		if (partnerMember == null) {
			logger.warn("Member not found for account: {}", taxID);
			return "redirect:/partnermember/partnerLogin";
		}

		logger.info("Member data retrieved successfully");
		model.addAttribute("partnerMember", partnerMember);

		return "back-end/partnermember/partnerCenter";
	}
	
	
	
	// 登出
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 使會話失效
		return "/login"; // 重定向到登入頁
	}

}