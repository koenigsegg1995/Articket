package com.maddog.articket.controller.venuerental;

import com.maddog.articket.venue.service.impl.VenueService;
import com.maddog.articket.venuerental.entity.VenueRental;
import com.maddog.articket.venuerental.service.pri.VenueRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/venueRentalAdmin")
public class VenueRentalAdminController {
	@Autowired
	VenueRentalService venueRentalService;
	@Autowired
	VenueService venueService;

//  根據 partnerID 顯示場地租借資料，這是給廠商查看已申請的場地用的
	@GetMapping
	public String getadminVenueRentalList(ModelMap model) {
		List<VenueRental> list = venueRentalService.getAll();
		model.addAttribute("venueRentalListData", list);
		return "/back-end-admin/venue/listAllAdminVenueRental";
	}

	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("venueRentalID") String venueRentalID, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		// EmpService empSvc = new EmpService();
		VenueRental venueRental = venueRentalService.getOneVenueRental(Integer.valueOf(venueRentalID));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("venueRental", venueRental);
		return "back-end-admin/venue/listOneAdminVenueRental"; // 查詢完成後轉交update_emp_input.html
	}

	@PostMapping("/approve")
	public String approveVenueRental(@RequestParam("venueRentalID") Integer venueRentalID,
			RedirectAttributes redirectAttributes) {
		VenueRental venueRental = venueRentalService.getOneVenueRental(venueRentalID);
		if (venueRental != null) {
			venueRental.setVenueRentalStatus(1); // 設置為通過狀態
			venueRentalService.updateVenueRental(venueRental);
			redirectAttributes.addFlashAttribute("message", "場地租借申請已通過審核");
		}
		return "redirect:/venueRentalAdmin";
	}

	@PostMapping("/reject")
	public String rejectVenueRental(@RequestParam("venueRentalID") Integer venueRentalID,
			RedirectAttributes redirectAttributes) {
		VenueRental venueRental = venueRentalService.getOneVenueRental(venueRentalID);
		if (venueRental != null) {
			venueRental.setVenueRentalStatus(0); // 設置為不通過狀態
			venueRentalService.updateVenueRental(venueRental);
			redirectAttributes.addFlashAttribute("message", "場地租借申請已被拒絕");
		}
		return "redirect:/venueRentalAdmin";
	}

	@GetMapping("/downloadProposal")
	public ResponseEntity<ByteArrayResource> downloadProposal(@RequestParam("venueRentalID") Integer venueRentalID) {
		VenueRental venueRental = venueRentalService.getOneVenueRental(venueRentalID);
		if (venueRental != null && venueRental.getProposal() != null) {
			byte[] pdfBytes = venueRental.getProposal();
			ByteArrayResource resource = new ByteArrayResource(pdfBytes);

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=proposal.pdf")
					.contentType(MediaType.APPLICATION_PDF).contentLength(pdfBytes.length).body(resource);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
