package com.maddog.articket.controller.venuerental;

import com.maddog.articket.partnermember.entity.PartnerMember;
import com.maddog.articket.venue.entity.Venue;
import com.maddog.articket.venue.service.impl.VenueService;
import com.maddog.articket.venuerental.entity.VenueRental;
import com.maddog.articket.venuerental.service.impl.VenueRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/venueRentalPage")
public class VenueRentalPageController {
	@Autowired
	VenueRentalService venueRentalService;
	@Autowired
	VenueService venueService;

	@GetMapping
	public String addVenueRental(ModelMap model) {
		VenueRental venueRental = new VenueRental();
		Venue venue = new Venue();
		venue.setVenueID(1);
//        PartnerMember partnerMember = new PartnerMember();
//        partnerMember.setPartnerID(1);
//        venueRental.setPartnerMember(partnerMember);
		venueRental.setVenue(venue);
		venueRental.setActivityName("");
		venueRental.setVenueRentalStatus(2);
		venueRental.setVenueRentalStartDate(java.sql.Date.valueOf("2024-09-19"));
		venueRental.setVenueRentalEndDate(java.sql.Date.valueOf("2024-09-19"));

		model.addAttribute("venueRental", venueRental);
		return "/back-end-partner/venue/venueRentalPage";
	}

//  根據 partnerID 顯示場地租借資料，這是給廠商查看已申請的場地用的
	@GetMapping("/partnerVenueRentalList")
	public String getVenueRentalsByPartnerID(ModelMap model, HttpSession session) {
		// 從 session 中獲取 partnerID
		Integer partnerId = (Integer) session.getAttribute("partnerID");
		List<VenueRental> list = venueRentalService.findByPartnerId(partnerId);
		model.addAttribute("venueRentalListData", list);
		return "/back-end-partner/venue/listAllPartnerVenueRental";
	}

//    這是給場館申請的下拉式選單用的
	@ModelAttribute("venueList")
	public List<Venue> showVenueList() {
		return venueService.getAll(); // 從服務層獲取所有場館資料並返回
	}

	@PostMapping("insert")
	public String insert(@Valid VenueRental venueRental, BindingResult result,
			@RequestParam("proposalFile") MultipartFile proposalFile, ModelMap model, HttpSession session) {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		if (venueRental.getVenue().getVenueID() < 1 || venueRental.getVenue().getVenueID() > 3) {
			result.rejectValue("venue.venueID", "error.venueRental", "Venue ID 必須在 1 和 3 之間");
		}

//        if (venueRental.getPartnerMember().getPartnerID() < 1 || venueRental.getPartnerMember().getPartnerID() > 5) {
//            result.rejectValue("partnerMember.partnerID", "error.venueRental", "Partner ID 查無資料");
//        }

		// 處理文件上傳
		if (proposalFile.isEmpty()) {
			result.rejectValue("proposal", "error.venueRental", "請上傳PDF檔");
		} else {
			// 處理文件上傳
			try {
				venueRental.setProposal(proposalFile.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
				result.rejectValue("proposal", "error.venueRental", "PDF 上傳失敗");
			}
		}

		if (result.hasErrors()) {
			// 將錯誤信息添加到模型中
			model.addAttribute("venueRental", venueRental);
			// 重新獲取場地列表，以確保表單重新加載時下拉菜單數據正確
			model.addAttribute("venueList", showVenueList());
			// 返回表單頁面
			return "/back-end-partner/venue/venueRentalPage";
		}

		// 先設定一個臨時的代碼
		venueRental.setVenueRentalCode("temp_code");

		PartnerMember partnerMember = new PartnerMember();
		partnerMember.setPartnerID((Integer) session.getAttribute("partnerID"));
		venueRental.setPartnerMember(partnerMember);
		/***************************
		 * 2.先保存資料，生成主鍵
		 *****************************************/
		venueRentalService.addVenueRental(venueRental);

		/***************************
		 * 3.根據主鍵生成 venueRentalCode，並更新
		 *****************************************/
		String generatedCode = generateVenueRentalCode(venueRental.getVenueRentalID());
		venueRental.setVenueRentalCode(generatedCode);

		// 更新 venueRental
		venueRentalService.updateVenueRental(venueRental);

		/*************************** 4.新增完成,準備轉交(Send the Success view) **************/
		return "redirect:/venueRentalPage/partnerVenueRentalList"; // 新增成功後重導至VenueRentalPageController
	}

	// 生成 venueRentalCode 的方法
	private String generateVenueRentalCode(Integer venueRentalID) {
		int baseNumber = 3001000;
		int generatedNumber = baseNumber + venueRentalID;
		return "G" + generatedNumber;
	}
}
