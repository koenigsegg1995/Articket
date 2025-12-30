package com.maddog.articket.controller.seat;

import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.activity.service.pri.ActivityService;
import com.maddog.articket.activityareaprice.entity.ActivityAreaPrice;
import com.maddog.articket.activityareaprice.service.impl.ActivityAreaPriceService;
import com.maddog.articket.activitytimeslot.entity.ActivityTimeSlot;
import com.maddog.articket.activitytimeslot.service.pri.ActivityTimeSlotService;
import com.maddog.articket.seat.model.PriceForm;
import com.maddog.articket.seat.model.SeatPriceData;
import com.maddog.articket.seat.model.service.pri.SeatService;
import com.maddog.articket.seatstatus.service.impl.SeatStatusService;
import com.maddog.articket.venuearea.service.impl.VenueAreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/seatReservationAndPricing")
public class SeatReservationAndPricingController {

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

	@PostMapping
	public String getSeatReservationAndPricing(@RequestParam Integer activityID,
			@RequestParam Integer activityTimeSlotID, Model model) {
		log.info("Accessing seat reservation and pricing page for Activity ID: {} and Time Slot ID: {}", activityID,
				activityTimeSlotID);

		// 獲取 VIP、A、B 區的價格
		BigDecimal vipPrice = getPriceForArea("VIP", activityID);
		BigDecimal aPrice = getPriceForArea("A", activityID);
		BigDecimal bPrice = getPriceForArea("B", activityID);

		// 創建一個 PriceForm 對象並設置初始值
		PriceForm priceForm = new PriceForm();
		priceForm.setVipPrice(vipPrice);
		priceForm.setAPrice(aPrice);
		priceForm.setBPrice(bPrice);

		// 將 PriceForm 添加到模型中
		model.addAttribute("priceForm", priceForm);
		model.addAttribute("activityID", activityID);
		model.addAttribute("activityTimeSlotID", activityTimeSlotID);

		return "/back-end-partner/ticket/seatReservationAndPricing";
	}

	@PostMapping("/setSeatsAndPrices")
	@ResponseBody
	public ResponseEntity<?> setSeatsAndPrices(@RequestBody SeatPriceData data, @RequestParam Integer activityID,
											   @RequestParam Integer activityTimeSlotID) {
		log.info("Received request to set seats and prices for Activity ID: {} and Time Slot ID: {}", activityID,
				activityTimeSlotID);

		ActivityTimeSlot activityTimeSlot = activityTimeSlotService.getActivityTimeSlotById(activityTimeSlotID);
		Activity activity = activityService.getOneActivity(activityID);
		activityService.setTicketSetStatusFinished(activityID);
		Integer venueId = activity.getVenueId();
		Map<String, Object> response = new HashMap<>();

		try {
			log.info("Processing data...");

			// 驗證價格
			if (!validatePrices(data.getPrices())) {
				log.warn("Invalid prices received: {}", data.getPrices());
				response.put("error", "價格不能小於500");
				return ResponseEntity.badRequest().body(response);
			}

			// 處理保留座位
			if (data.getReservedSeats() != null && !data.getReservedSeats().isEmpty()) {
				log.info("Reserved seats:");
				for (String seatName : data.getReservedSeats()) {
					log.info(" - Seat Name: {}", seatName);
					Integer seatId = seatService.findSeatId(venueId, seatName);
					if (seatId != null) {
						seatStatusService.updateSeatStatusToReserved(seatId, activityTimeSlotID);
						log.info("   Updated seat status for Seat ID: {} to Reserved (3)", seatId);

						String venueAreaName = getVenueAreaNameFromSeatName(seatName);
						if (venueAreaName != null) {
							Integer venueAreaId = venueAreaService.findVenueAreaIdByVenueIdAndVenueAreaName(venueId,
									venueAreaName);
							if (venueAreaId != null) {
								log.info("   Seat belongs to Venue Area ID: {}", venueAreaId);
							} else {
								log.warn("   No corresponding Venue Area ID found for Venue Area Name: {}",
										venueAreaName);
							}
						} else {
							log.warn("   Unable to determine Venue Area Name for Seat Name: {}", seatName);
						}
					} else {
						log.warn("   No corresponding Seat ID found for Seat Name: {}", seatName);
					}
				}
				response.put("reservedSeats", data.getReservedSeats());
			} else {
				log.info("No reserved seats");
				response.put("reservedSeats", "None");
			}

			// 處理票價
			if (data.getPrices() != null && !data.getPrices().isEmpty()) {
				log.info("Ticket prices:");
				for (Map.Entry<String, Integer> entry : data.getPrices().entrySet()) {
					String venueAreaName = entry.getKey();
					Integer price = entry.getValue();
					log.info(" - {} area: ${}", venueAreaName, price);

					Integer venueAreaId = venueAreaService.findVenueAreaIdByVenueIdAndVenueAreaName(venueId, venueAreaName);
					if (venueAreaId != null) {
						try {
							ActivityAreaPrice activityAreaPrice = new ActivityAreaPrice();
							activityAreaPrice.setVenueAreaId(venueAreaId);
							activityAreaPrice.setActivityId(activityID);
							activityAreaPrice.setActivityAreaPrice(new BigDecimal(price));
							activityAreaPriceService.updateOrCreateActivityAreaPrice(activityAreaPrice);
							log.info(
									"   Updated/Created price for Venue Area ID: {}, Activity ID: {}, New Price: ${}",
									venueAreaId, activityID, new BigDecimal(price));
						} catch (Exception e) {
							log.error("Error updating/creating price for Venue Area ID: {}, Activity ID: {}",
									venueAreaId, activityID, e);
						}
					} else {
						log.warn("   No corresponding Venue Area ID found for Venue Area Name: {}", venueAreaName);
					}
				}
				response.put("prices", data.getPrices());
			} else {
				log.info("No ticket prices set");
				response.put("prices", "Not set");
			}

			log.info("Data processing completed successfully");
			response.put("message", "設定已成功保存");
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			log.error("Error occurred while processing the request", e);
			response.put("error", "處理請求時發生錯誤: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PostMapping("/seatStatus")
	@ResponseBody
	public ResponseEntity<?> getSeatStatus(@RequestParam Integer activityTimeSlotID) {
		log.info("Fetching seat status for Time Slot ID: {}", activityTimeSlotID);

		try {
			List<String> soldSeats = seatStatusService.getSeatNamesWithStatus2(activityTimeSlotID);
			List<String> reservedSeats = seatStatusService.getSeatNamesWithStatus3(activityTimeSlotID);

			Map<String, Object> response = new HashMap<>();
			response.put("soldSeats", soldSeats);
			response.put("reservedSeats", reservedSeats);

			log.info("Successfully fetched seat status");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			log.error("Error occurred while fetching seat status", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("獲取座位狀態時發生錯誤");
		}
	}

	@PostMapping("/cancelReservation")
	@ResponseBody
	public ResponseEntity<?> cancelReservation(@RequestBody Map<String, String> request) {
		log.info("Received request to cancel seat reservation");

		String seatName = request.get("seatName");
		Integer activityTimeSlotID = Integer.parseInt(request.get("activityTimeSlotID"));

		try {
			Integer venueId = getVenueIdForActivityTimeSlot(activityTimeSlotID);
			Integer seatId = seatService.findSeatId(venueId, seatName);

			if (seatId != null) {
				seatStatusService.updateSeatStatus(seatId, activityTimeSlotID, 1); // 將狀態更新為1（可用）
				log.info("Successfully canceled reservation for seat: {}", seatName);
				Map<String, Object> response = new HashMap<>();
				response.put("success", true);
				response.put("message", "座位預留已成功取消");
				return ResponseEntity.ok(response);
			} else {
				log.warn("No corresponding Seat ID found for Seat Name: {}", seatName);
				Map<String, Object> response = new HashMap<>();
				response.put("success", false);
				response.put("message", "找不到對應的座位");
				return ResponseEntity.badRequest().body(response);
			}
		} catch (Exception e) {
			log.error("Error occurred while canceling seat reservation", e);
			Map<String, Object> response = new HashMap<>();
			response.put("success", false);
			response.put("message", "取消座位預留時發生錯誤");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	private String getVenueAreaNameFromSeatName(String seatName) {
		if (seatName == null || seatName.isEmpty()) {
			return null;
		}
		if (seatName.startsWith("VIP")) {
			return "VIP";
		} else if (seatName.startsWith("A")) {
			return "A";
		} else if (seatName.startsWith("B")) {
			return "B";
		} else {
			log.warn("Unknown seat area for seat name: {}", seatName);
			return null;
		}
	}

	// 輔助方法：獲取指定區域的價格
	private BigDecimal getPriceForArea(String areaName, Integer activityID) {
		Integer venueId = getVenueIdForActivity(activityID);

		Integer venueAreaId = venueAreaService.findVenueAreaIdByVenueIdAndVenueAreaName(venueId, areaName);
		if (venueAreaId != null) {
			ActivityAreaPrice price = activityAreaPriceService.findByVenueAreaIdAndActivityId(venueAreaId, activityID);
			if (price != null) {
				return price.getActivityAreaPrice().setScale(0, RoundingMode.HALF_UP);
			}
		}
		return BigDecimal.ZERO; // 如果沒有找到價格，返回 0
	}

	private Integer getVenueIdForActivity(Integer activityID) {
		Activity activity = activityService.getOneActivity(activityID);
		return activity.getVenueId();
	}

	private Integer getVenueIdForActivityTimeSlot(Integer activityTimeSlotId) {
		ActivityTimeSlot activityTimeSlot = activityTimeSlotService.getActivityTimeSlotById(activityTimeSlotId);

		Activity activity = activityService.getOneActivity(activityTimeSlot.getActivityId());
		return activity.getVenueId();
	}

	// 驗證價格的方法
	private boolean validatePrices(Map<String, Integer> prices) {
		return prices.values().stream().allMatch(price -> price >= 500);
	}
}