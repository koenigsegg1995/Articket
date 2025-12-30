package com.maddog.articket.controller.seat;

import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.activity.service.pri.ActivityService;
import com.maddog.articket.activityareaprice.entity.ActivityAreaPrice;
import com.maddog.articket.activityareaprice.service.pri.ActivityAreaPriceService;
import com.maddog.articket.activitytimeslot.entity.ActivityTimeSlot;
import com.maddog.articket.activitytimeslot.service.pri.ActivityTimeSlotService;
import com.maddog.articket.seat.model.service.pri.SeatService;
import com.maddog.articket.seatstatus.entity.SeatStatus;
import com.maddog.articket.seatstatus.service.impl.SeatStatusService;
import com.maddog.articket.ticket.entity.Ticket;
import com.maddog.articket.venuearea.service.impl.VenueAreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Slf4j
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
	public String getSeatSelect(@RequestParam("activityTimeSlot") Integer activityTimeSlotId, Model model) {
		ActivityTimeSlot activityTimeSlot = activityTimeSlotService.getActivityTimeSlotById(activityTimeSlotId);
        Integer activityId = activityTimeSlot.getActivityId();
        Activity activity = activityService.getOneActivity(activityId);
        Integer venueId = activity.getVenueId();
		
		String venueAreaName1 = "VIP";
		Integer venueAreaId1 = venueAreaService.findVenueAreaIdByVenueIdAndVenueAreaName(venueId, venueAreaName1);
		log.info("venueAreaId1================================={}", venueAreaId1);
		log.info("activityId================================={}", activityId);
		ActivityAreaPrice activityAreaPrice1 = activityAreaPriceService.findByVenueAreaIdAndActivityId(venueAreaId1, activityId);
		
		String venueAreaName2 = "A";
		Integer venueAreaId2 = venueAreaService.findVenueAreaIdByVenueIdAndVenueAreaName(venueId, venueAreaName2);
		log.info("venueAreaId2================================={}", venueAreaId2);
		log.info("activityId================================={}", activityId);
		ActivityAreaPrice activityAreaPrice2 = activityAreaPriceService.findByVenueAreaIdAndActivityId(venueAreaId2, activityId);
		
		String venueAreaName3 = "B";
		Integer venueAreaId3 = venueAreaService.findVenueAreaIdByVenueIdAndVenueAreaName(venueId, venueAreaName3);
		log.info("venueAreaId3========================================={}", venueAreaId3);
		log.info("activityId========================================={}", activityId);
		ActivityAreaPrice activityAreaPrice3 = activityAreaPriceService.findByVenueAreaIdAndActivityId(venueAreaId3, activityId);
		
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

		return "/front-end/ticket/seatSelect";
	}

	@PostMapping("/submitSeats")
	public String submitSeats(@RequestParam Integer activityTimeSlotId,
							  @RequestParam String seat1,
							  @RequestParam(required = false) String seat2,
							  @RequestParam(required = false) String seat3,
							  @RequestParam(required = false) String seat4,
							  @RequestParam(required = false) String seat5,
							  HttpSession session,
							  RedirectAttributes redirectAttributes) {

		List<String> seatNames = Stream.of(seat1, seat2, seat3, seat4, seat5)
									.filter(Objects::nonNull)
									.filter(seat -> !seat.isBlank())
									.toList();

		List<Ticket> ticketList = new ArrayList<>();
		ActivityTimeSlot activityTimeSlot = activityTimeSlotService.getActivityTimeSlotById(activityTimeSlotId);
		Integer activityId = activityTimeSlot.getActivityId();// 活動ID從活動時段ID抓取
		Activity activity = activityService.getOneActivity(activityId);
		activity.setActivityId(activityId);
		Integer venueId = activity.getVenueId();// 場館ID從活動ID抓取

		try {
			log.info("處理座位選擇開始");
			for (String seatName : seatNames) {
				log.info("正在處理座位: {}", seatName);

				// 解析座位名稱，分離出區域名稱和座位號碼
				String venueAreaName = seatName.replaceAll("\\d+", "");

				log.info("解析後的區域名稱: {}", venueAreaName);

				// 使用 VenueAreaService 找出 venueAreaId
				Integer venueAreaId = venueAreaService.findVenueAreaIdByVenueIdAndVenueAreaName(venueId, venueAreaName);
				
				
				if (venueAreaId == null) {
					log.info("未找到區域: {}", venueAreaName);
					redirectAttributes.addFlashAttribute("error", "未找到區域: " + venueAreaName);
					return "redirect:/error";
				}

				log.info("找到區域ID: {} 對應區域名稱: {}", venueAreaId, venueAreaName);

				// 使用 ActivityAreaPriceService 查找 ActivityAreaPrice
				ActivityAreaPrice activityAreaPrice = activityAreaPriceService.findByVenueAreaIdAndActivityId(venueAreaId,
						activityId);
				if (activityAreaPrice == null) {
					log.info("未找到活動區域價格: 區域ID {}, 活動ID {}", venueAreaId, activityId);
					redirectAttributes.addFlashAttribute("error", "未找到活動區域價格: " + venueAreaName);
					return "redirect:/error";
				}

				log.info("找到活動區域價格ID: {} 對應區域ID: {}, 活動ID: {}", activityAreaPrice.getActivityAreaPriceId(), venueAreaId, activityId);

				// 使用 venueId 和 seatName 找出 seatId
				Integer seatId = seatService.findSeatId(venueId, seatName);
				if (seatId != null) {
					log.info("找到座位ID: {} 對應座位名稱: {} 在區域ID: {}", seatId, seatName, venueAreaId);
					// 使用 activityTimeSlotId 和 seatId 找出 SeatStatus
					SeatStatus seatStatus = seatStatusService
							.getSeatStatusByActivityTimeSlotIdAndSeatId(activityTimeSlotId, seatId);
					if (seatStatus != null) {
						log.info("找到座位狀態ID: {} 對應座位ID: {} 在區域ID: {}", seatStatus.getSeatStatusId(), seatId, venueAreaId);

						// Create a new Ticket object and add it to the list
						Ticket ticket = new Ticket();
						ticket.setSeatStatus(seatStatus);
						ticket.setActivityAreaPrice(activityAreaPrice);
						ticket.setActivityTimeSlotId(activityTimeSlotId);
						ticketList.add(ticket);
					} else {
						log.info("未找到座位狀態: {} 在區域ID: {}", seatName, venueAreaId);
						redirectAttributes.addFlashAttribute("error", "未找到座位狀態: " + seatName);
						return "redirect:/error";
					}
				} else {
					log.info("座位不存在: {} 在區域ID: {}", seatName, venueAreaId);
					redirectAttributes.addFlashAttribute("error", "座位不存在: " + seatName);
					return "redirect:/error";
				}
			}

			// Store the ticketList in the session
			session.setAttribute("ticketList", ticketList);

			log.info("所有票券資訊已存入session");
			return "redirect:/ticket/bookTicket";
		} catch (Exception e) {
			log.info("處理座位選擇時發生錯誤: {}", e.getMessage());
			redirectAttributes.addFlashAttribute("error", "處理座位選擇時發生錯誤: " + e.getMessage());
			return "redirect:/error";
		}
	}
}
