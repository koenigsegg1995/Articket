package com.maddog.articket.controller.seat;

import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.activity.service.pri.ActivityService;
import com.maddog.articket.activityareaprice.entity.ActivityAreaPrice;
import com.maddog.articket.activityareaprice.service.impl.ActivityAreaPriceService;
import com.maddog.articket.activitytimeslot.entity.ActivityTimeSlot;
import com.maddog.articket.activitytimeslot.service.impl.ActivityTimeSlotService;
import com.maddog.articket.seat.model.service.impl.SeatService;
import com.maddog.articket.seatstatus.entity.SeatStatus;
import com.maddog.articket.seatstatus.service.impl.SeatStatusService;
import com.maddog.articket.ticket.entity.Ticket;
import com.maddog.articket.venuearea.service.impl.VenueAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/seatSelect")
public class SeatSelectController {

	@Autowired
	private ActivityTimeSlotService activityTimeSlotService;

	@Autowired
	private ActivityService activityService;

	@Autowired
	private SeatService seatService;

	@Autowired
	private SeatStatusService seatStatusService;

	@Autowired
	private VenueAreaService venueAreaService;

	@Autowired
	private ActivityAreaPriceService activityAreaPriceService;

	@GetMapping
	public String getSeatSelect(@RequestParam("activityTimeSlot") Integer activityTimeSlotId,ModelMap model) {
		// @RequestParam("activityTimeSlotId") Integer activityTimeSlotId, ModelMap
		// model//到時候需要加入這參數
//		Integer activityTimeSlotId = 30;
		ActivityTimeSlot activityTimeSlot = activityTimeSlotService.getActivityTimeSlotById(activityTimeSlotId);
        Integer activityId = activityTimeSlot.getActivity().getActivityID();
        Activity activity = activityService.getOneActivity(activityId);
        Integer venueId = activity.getVenue().getVenueID();
		
		String venueAreaName1 = "VIP";
		Integer venueAreaId1 = venueAreaService.findVenueAreaIdByVenueIdAndVenueAreaName(venueId, venueAreaName1);
		System.out.println("venueAreaId1================================="+venueAreaId1);
		System.out.println("activityId================================="+activityId);
		ActivityAreaPrice activityAreaPrice1 = activityAreaPriceService.findActivityAreaPrice(venueAreaId1, activityId);
		
		String venueAreaName2 = "A";
		Integer venueAreaId2 = venueAreaService.findVenueAreaIdByVenueIdAndVenueAreaName(venueId, venueAreaName2);
		System.out.println("venueAreaId2================================="+venueAreaId2);
		System.out.println("activityId================================="+activityId);
		ActivityAreaPrice activityAreaPrice2 = activityAreaPriceService.findActivityAreaPrice(venueAreaId2, activityId);
		
		String venueAreaName3 = "B";
		Integer venueAreaId3 = venueAreaService.findVenueAreaIdByVenueIdAndVenueAreaName(venueId, venueAreaName3);
		System.out.println("venueAreaId3========================================="+venueAreaId3);
		System.out.println("activityId========================================="+activityId);
		ActivityAreaPrice activityAreaPrice3 = activityAreaPriceService.findActivityAreaPrice(venueAreaId3, activityId);
		
		model.addAttribute("activityTimeSlot", activityTimeSlot);
		model.addAttribute("activityAreaPrice1", activityAreaPrice1.getActivityAreaPrice());
		model.addAttribute("activityAreaPrice2", activityAreaPrice2.getActivityAreaPrice());
		model.addAttribute("activityAreaPrice3", activityAreaPrice3.getActivityAreaPrice());
		
		// 這裡可以添加更多需要傳遞給視圖的數據
		
		//把座位狀態傳到前端
		List<SeatStatus> seatStatuses = seatStatusService.getAllSeatStatusByActivityTimeSlotID(activityTimeSlotId);
		model.addAttribute("seatStatuses", seatStatuses);
		
		
		// 將不可用的座位添加到模型中
		List<String> unavailableSeats = seatStatusService.getUnavailableSeatNames(activityTimeSlotId);
        model.addAttribute("unavailableSeats", unavailableSeats);
		//測試用，看能不能讓前端抓到
//		List<String> unavailableSeats = Arrays.asList("VIP15", "A23", "A211", "B61", "B62");
//      model.addAttribute("unavailableSeats", unavailableSeats);

		return "/front-end/ticket/seatSelect";
	}

	@PostMapping("/submitSeats")
	public String submitSeats(@RequestParam Integer activityTimeSlotID, @RequestParam String seat1,
			@RequestParam(required = false) String seat2, @RequestParam(required = false) String seat3,
			@RequestParam(required = false) String seat4, @RequestParam(required = false) String seat5,
			HttpSession session, RedirectAttributes redirectAttributes) {
		List<String> seatNames = Arrays.asList(seat1, seat2, seat3, seat4, seat5).stream().filter(Objects::nonNull)
				.filter(seat -> !seat.trim().isEmpty()).collect(Collectors.toList());

		List<Ticket> ticketList = new ArrayList<>();
		ActivityTimeSlot activityTimeSlot = activityTimeSlotService.getActivityTimeSlotById(activityTimeSlotID);
		Integer activityId = activityTimeSlot.getActivity().getActivityID();// 活動ID從活動時段ID抓取
		Activity activity = activityService.getOneActivity(activityId);
		activity.setActivityID(activityId);
		Integer venueId = activity.getVenue().getVenueID();// 場館ID從活動ID抓取

		try {
			System.out.println("處理座位選擇開始");
			for (String seatName : seatNames) {
				System.out.println("正在處理座位: " + seatName);

				// 解析座位名稱，分離出區域名稱和座位號碼
				String venueAreaName = seatName.replaceAll("\\d+", "");

				System.out.println("解析後的區域名稱: " + venueAreaName);

				// 使用 VenueAreaService 找出 venueAreaId
				Integer venueAreaId = venueAreaService.findVenueAreaIdByVenueIdAndVenueAreaName(venueId, venueAreaName);
				
				
				if (venueAreaId == null) {
					System.out.println("未找到區域: " + venueAreaName);
					redirectAttributes.addFlashAttribute("error", "未找到區域: " + venueAreaName);
					return "redirect:/error";
				}

				System.out.println("找到區域ID: " + venueAreaId + " 對應區域名稱: " + venueAreaName);

				// 使用 ActivityAreaPriceService 查找 ActivityAreaPrice
				ActivityAreaPrice activityAreaPrice = activityAreaPriceService.findActivityAreaPrice(venueAreaId,
						activityId);
				if (activityAreaPrice == null) {
					System.out.println("未找到活動區域價格: 區域ID " + venueAreaId + ", 活動ID " + activityId);
					redirectAttributes.addFlashAttribute("error", "未找到活動區域價格: " + venueAreaName);
					return "redirect:/error";
				}

				System.out.println("找到活動區域價格ID: " + activityAreaPrice.getActivityAreaPriceID() + " 對應區域ID: "
						+ venueAreaId + ", 活動ID: " + activityId);

				// 使用 venueId 和 seatNumber 找出 seatId
				Integer seatId = seatService.findSeatId(venueId, seatName);
				if (seatId != null) {
					System.out.println("找到座位ID: " + seatId + " 對應座位名稱: " + seatName + " 在區域ID: " + venueAreaId);
					// 使用 activityTimeSlotId 和 seatId 找出 SeatStatus
					SeatStatus seatStatus = seatStatusService
							.getSeatStatusByActivityTimeSlotIdAndSeatId(activityTimeSlotID, seatId);
					if (seatStatus != null) {
						System.out.println("找到座位狀態ID: " + seatStatus.getSeatStatusID() + " 對應座位ID: " + seatId
								+ " 在區域ID: " + venueAreaId);

						// Create a new Ticket object and add it to the list
						Ticket ticket = new Ticket();
						ticket.setSeatStatus(seatStatus);
						ticket.setActivityAreaPrice(activityAreaPrice);
						ticket.setActivityTimeSlot(activityTimeSlot);
						ticketList.add(ticket);
					} else {
						System.out.println("未找到座位狀態: " + seatName + " 在區域ID: " + venueAreaId);
						redirectAttributes.addFlashAttribute("error", "未找到座位狀態: " + seatName);
						return "redirect:/error";
					}
				} else {
					System.out.println("座位不存在: " + seatName + " 在區域ID: " + venueAreaId);
					redirectAttributes.addFlashAttribute("error", "座位不存在: " + seatName);
					return "redirect:/error";
				}
			}

			// Store the ticketList in the session
			session.setAttribute("ticketList", ticketList);

			System.out.println("所有票券資訊已存入session");
			return "redirect:/ticket/bookTicket";
		} catch (Exception e) {
			System.out.println("處理座位選擇時發生錯誤: " + e.getMessage());
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "處理座位選擇時發生錯誤: " + e.getMessage());
			return "redirect:/error";
		}
	}
}
