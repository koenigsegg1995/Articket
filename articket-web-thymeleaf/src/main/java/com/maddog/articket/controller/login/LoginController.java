package com.maddog.articket.controller.login;

import com.maddog.articket.generalmember.dto.GeneralMemberDto;
import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.generalmember.service.pri.GeneralMemberService;
import com.maddog.articket.partnermember.entity.PartnerMember;
import com.maddog.articket.partnermember.service.pri.PartnerMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = { "/generalmember", "/partnermember" })
public class LoginController {

	@Autowired
    private GeneralMemberService gmemberSvc;

	@Autowired
    private PartnerMemberService partnerSvc;

	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}

	@GetMapping("/partnerLogin")
	public String getPartnerLogin() {
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
	        session.setAttribute("memberID", generalMember.getMemberId());
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
	public String memberCenter(HttpSession session,
                               Model model,
                               @RequestParam(required = false) Boolean updated) {
		log.info("Entering memberCenter method");

		if (updated != null && updated) {
			log.info("Redirected from updateMember");
		}

		if (session.getAttribute("memberAccount") == null) {
			log.info("User not logged in, redirecting to login");

			return "redirect:/generalmember/login";
		}

		String memberAccount = (String) session.getAttribute("memberAccount");
		log.info("Fetching data for member account: {}", memberAccount);

		GeneralMember generalMember = gmemberSvc.getByMemberAccount(memberAccount);

		if (generalMember == null) {
			log.warn("Member not found for account: {}", memberAccount);

			return "redirect:/generalmember/login";
		}

		log.info("Member data retrieved successfully");
		model.addAttribute("generalMember", generalMember);

		return "front-end/generalmember/memberCenter";
	}

	// 修改會員中心編輯頁面
	@GetMapping("/editMember/{memberId}")
	public String showEditMemberForm(@PathVariable Integer memberId,
									 HttpSession session,
									 Model model) {
		// 檢查用戶是否已登入
		if (session.getAttribute("memberAccount") == null) {
			return "redirect:/generalmember/login";
		}

		// 從數據庫獲取會員資料
		GeneralMember generalMember = gmemberSvc.getById(memberId);

		if (generalMember == null) {
			// 處理找不到會員的情況
			return "redirect:/memberCenter";
		}

		// 將資料轉進 DTO
		GeneralMemberDto generalMemberForUpdate = new GeneralMemberDto();
		generalMemberForUpdate.setMemberId(generalMember.getMemberId());
		generalMemberForUpdate.setMemberName(generalMember.getMemberName());
		generalMemberForUpdate.setMemberPhone(generalMember.getMemberPhone());
		generalMemberForUpdate.setMemberAddress(generalMember.getMemberAddress());
		generalMemberForUpdate.setMemberAccount(generalMember.getMemberAccount());
		generalMemberForUpdate.setNationalId(generalMember.getNationalId());
		generalMemberForUpdate.setMemberNickName(generalMember.getMemberNickName());
		generalMemberForUpdate.setBirthday(generalMember.getBirthday());
		generalMemberForUpdate.setGender(generalMember.getGender());

		// 將會員資料添加到模型中
		model.addAttribute("generalMember", generalMemberForUpdate);

		return "front-end/generalmember/editMember"; // 返回編輯頁面的視圖名稱
	}

	// 更新會員中心資料
	@PostMapping("/updateMember")
	public String updateMember(@ModelAttribute GeneralMemberDto generalMember,
							   @RequestParam(value = "memberPictureForUpdate", required = false) MultipartFile memberPictureForUpdate,
	                           HttpSession session,
	                           RedirectAttributes redirectAttributes) {
	    log.info("Entering updateMember method");

	    if (session.getAttribute("memberAccount") == null) {
	        log.info("User not logged in, redirecting to login");

			return "redirect:/generalmember/login";
	    }

	    try {
			GeneralMember generalMemberForUpdate = new GeneralMember();

			// 轉交資料給 DO
			generalMemberForUpdate.setMemberId(generalMember.getMemberId());
			generalMemberForUpdate.setMemberName(generalMember.getMemberName());
			generalMemberForUpdate.setMemberPhone(generalMember.getMemberPhone());
			generalMemberForUpdate.setMemberNickName(generalMember.getMemberNickName());
			generalMemberForUpdate.setMemberAddress(generalMember.getMemberAddress());
				// 如果有新的圖片上傳，則更新 memberPicture
			if (memberPictureForUpdate != null && !memberPictureForUpdate.isEmpty()) {
				generalMemberForUpdate.setMemberPicture(memberPictureForUpdate.getBytes());
			}

			log.info("Updating member data for account: {}", generalMemberForUpdate.getMemberAccount());
	        int success = gmemberSvc.update(generalMemberForUpdate);
	        if(success == 1) {
				log.info("Member data updated successfully");
				redirectAttributes.addFlashAttribute("message", "資料已成功更新！");
			} else if(success == 0) {
				throw new RuntimeException("service update 回傳 0 ");
			}

			return "redirect:/generalmember/memberCenter";
	    } catch (Exception e) {
	        log.error("Error updating member data: {}", e.getMessage());

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
		PartnerMember partnerMember = partnerSvc.getByTaxId(taxIDStr);

		// 檢查會員是否存在
		if (partnerMember == null) {
			model.addAttribute("errorMessage", "會員不存在");
			return "partnerLogin"; // 直接返回登入頁
		}

		// 檢查密碼
		if (partnerPasswordStr.equals(partnerMember.getPartnerPassword())) {
			model.addAttribute("taxID", taxIDStr);

			session.setAttribute("partnerName", partnerMember.getPartnerName());
			session.setAttribute("partnerID", partnerMember.getPartnerId());
			session.setAttribute("taxID", partnerMember.getTaxId());

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

		log.info("Entering partnerCenter method");

		if (updated != null && updated) {
			System.out.println("Redirected from updatePartner");
		}

		if (session.getAttribute("taxID") == null) {
			log.info("User not logged in, redirecting to login");
			return "redirect:/partnermember/partnerLogin";
		}

		String taxId = (String) session.getAttribute("taxID");
		log.info("Fetching data for member account: {}", taxId);

		PartnerMember partnerMember = partnerSvc.getByTaxId(taxId);

		if (partnerMember == null) {
			log.warn("Member not found for account: {}", taxId);
			return "redirect:/partnermember/partnerLogin";
		}

		log.info("Member data retrieved successfully");

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