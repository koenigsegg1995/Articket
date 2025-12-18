package com.maddog.articket.controller.activity;

import com.maddog.articket.activity.dto.ActivityForAdd;
import com.maddog.articket.activity.dto.ActivityForView;
import com.maddog.articket.activity.dto.ActivityQueryCondition;
import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.activity.service.pri.ActivityService;
import com.maddog.articket.activitypicture.entity.ActivityPicture;
import com.maddog.articket.activitypicture.service.pri.ActivityPictureService;
import com.maddog.articket.activitytimeslot.entity.ActivityTimeSlot;
import com.maddog.articket.partnermember.entity.PartnerMember;
import com.maddog.articket.partnermember.service.impl.PartnerMemberService;
import com.maddog.articket.venue.entity.Venue;
import com.maddog.articket.venue.service.impl.VenueService;
import com.maddog.articket.venuerental.entity.VenueRental;
import com.maddog.articket.venuerental.service.impl.VenueRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/activity")
public class ActivityController {
	
	@Autowired
	private ActivityService activitySvc;

	@Autowired
	private PartnerMemberService partnerSvc;
	
	@Autowired
	private VenueRentalService venueRentalSvc;
	
/********************* 跳轉 **********************/
//////////////// 前台 ////////////////
    /**
     * 活動資訊總攬
     *
     * @param model
     *          ModelMap
     * @return activityInfoAll.html
     *          String
     */
	@GetMapping("activityInfoAll")
	public String activityInfo(Model model) {
        // 空容器，查詢後再顯示活動
        List<ActivityForView> activities = new ArrayList<>();
		
		model.addAttribute("activitySearchList", activities);
		
		return "front-end/activity/activityInfoAll";
	}

    /**
     * 活動資訊
     *
     * @param activityId
     *          Integer
     * @return activityInfoOne.html
     *          String
     */
	@GetMapping("activityInfoOne")
	public String activityInfoOne(@RequestParam("activityId") Integer activityId, ModelMap model) {
		// 依活動 ID 查詢活動 VO
        ActivityForView view = activitySvc.findByIdForView(activityId);

        // 加進活動圖片 ID 清單
        view.setActivityPictureIds(activitySvc.findActivityPictureIdByActivityId(activityId));

        // 加進活動時段清單
        view.setActivityTimeSlots(activitySvc.findActivityTimeSlotByActivityId(activityId));

		model.addAttribute("activity", view);
		
		return "front-end/activity/activityInfoOne";
	}
//////////////// 前台 ////////////////
	
//////////////// 後台 ////////////////
	//活動資訊
	@GetMapping("activityDisplay")
	public String activityDisplay(HttpSession session, ModelMap model) {
		//確認是否登入，未登入重導至廠商登入頁面
		if(session.getAttribute("partnerID") == null) {
			return "redirect:/partnermember/partnerLogin";
		}
		
		//取得登入帳號
		Integer partnerID = (Integer)session.getAttribute("partnerID");
		PartnerMember partner = partnerSvc.getOnePartnerMember(partnerID);
		
		//取得廠商活動
		Set<Activity> partnerActivities = partner.getActivities();
		
		model.addAttribute("backEndActivityListData", partnerActivities);
		
		return "back-end-partner/activity/activityDisplay";
	}
	
	//活動未新增
	@GetMapping("activityUnadd")
	public String activityUnadd(HttpSession session, ModelMap model) {
		//確認是否登入，未登入重導至廠商登入頁面
		if(session.getAttribute("partnerID") == null) {
			return "redirect:/partnermember/partnerLogin";
		}
		
		//取得登入帳號
		Integer partnerId = (Integer)session.getAttribute("partnerID");
		
		//取得廠商未新增活動的場地申請
		List<VenueRental> venueRentals = venueRentalSvc.findUnNewByPartnerId(partnerId);
		
		model.addAttribute("venueRentalListData", venueRentals);
		
		return "back-end-partner/activity/activityUnadd";
	}

	/**
	 * 活動新增
	 *
	 * @param venueRentalId
	 * 			Integer
	 * @param session
	 * 			HttpSession
	 * @param model
	 * 			Model
	 */
	@PostMapping("activityAdd")
	public String activityAdd(@RequestParam("venueRentalId") Integer venueRentalId,
							  HttpSession session,
							  Model model) {
		//確認是否登入，未登入重導至廠商登入頁面
		if(session.getAttribute("partnerID") == null) {
			return "redirect:/partnermember/partnerLogin";
		}

		ActivityForAdd activityForAdd = activitySvc.getActivityForAddByVenueRentalId(venueRentalId);

    	model.addAttribute("activityForAdd", activityForAdd);

		return "back-end-partner/activity/activityAdd";
	}
	
	//活動資訊設定
	@PostMapping("activityConfig")
	public String activityConfig(@RequestParam("activityID") String activityID, HttpSession session, ModelMap model) {
		//確認是否登入，未登入重導至廠商登入頁面
		if(session.getAttribute("partnerID") == null) {
			return "redirect:/partnermember/partnerLogin";
		}
		
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		Activity activity = activitySvc.getOneActivity(Integer.valueOf(activityID));
		
		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("activity", activity);
		
		return "back-end-partner/activity/activityConfig";
	}
//////////////// 後台 ////////////////
/********************* 跳轉 **********************/
	
/********************* action *******************/
	//activityAdd 送出新增
	@PostMapping("add")
	public String addActivity(@Valid ActivityForAdd activityForAdd,
							  BindingResult result,
							  @RequestParam("activityPictures") MultipartFile[] parts,
							  HttpSession session) throws IOException {
		
		// 確認是否登入，未登入重導至廠商登入頁面
		Integer partnerId = (Integer) session.getAttribute("partnerID");
		if(partnerId == null) {
			return "redirect:/partnermember/partnerLogin";
		}
		
		// 錯誤驗證
		if (result.hasErrors()) {
			return "back-end-partner/activity/activityAdd";
		}

		// 設置上傳圖片
		List<ActivityPicture> activityPictureList = new ArrayList<>();
		for (MultipartFile multipartFile : parts) {
			ActivityPicture activityPicture = new ActivityPicture();
			
			// activityId 於 service 再設置
			activityPicture.setActivityPicture(multipartFile.getBytes());

			activityPictureList.add(activityPicture);
		}
		
		//////// 設置未在表單中的資訊 ////////////

		// 取得 venueId
		VenueRental venueRental = venueRentalSvc.getOneVenueRental(activityForAdd.getVenueRentalId());
		Integer venueId = venueRental.getVenueId();

		//////// 設置未在表單中的資訊 ////////////

		//新增 activity
		activitySvc.addActivity(activityForAdd, partnerId, venueId);
		
		//跳轉至 activityDisplay
		return "redirect:/activity/activityDisplay";
	}
	
	//activityConfig 送出更新
	@PostMapping("update")
	public String update(@Valid Activity activity, BindingResult result,
						 @RequestParam("activityPictures") MultipartFile[] parts,
						 HttpSession session, ModelMap model) throws IOException {
		//確認是否登入，未登入重導至廠商登入頁面
		if(session.getAttribute("partnerID") == null) {
			return "redirect:/partnermember/partnerLogin";
		}

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中upFiles欄位的FieldError紀錄
		result = removeFieldError(activity, result, "activityPictures");

		Activity activityORI = activitySvc.getOneActivity(activity.getActivityId());

		if (parts[0].isEmpty()) { // 使用者未選擇要上傳的新圖片時
				Set<ActivityPicture> activityPictures = activityORI.getActivityPictures();

				activity.setActivityPictures(activityPictures);
		} else {
			for (MultipartFile multipartFile : parts) {
				ActivityPicture activityPicture = new ActivityPicture();

				activityPicture.setActivityPicture(multipartFile.getBytes());
				activityPicture.setActivity(activity);
				activity.getActivityPictures().add(activityPicture);
			}
		}

		if (result.hasErrors()) {
			return "back-end-partner/activity/activityConfig";
		}
		/*************************** 2.開始修改資料 *****************************************/
		//////// 設置未在表單中的資訊 ////////////

		activity.setPartnerMember(activityORI.getPartnerMember());
		activity.setVenue(activityORI.getVenue());
		activity.setVenueRental(activityORI.getVenueRental());
		activity.setActivityStatus(1);
		activity.setTicketSetStatus(activityORI.getTicketSetStatus());

		////////設置未在表單中的資訊 ////////////

		activitySvc.updateActivity(activity);

		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		activity = activitySvc.getOneActivity(Integer.valueOf(activity.getActivityID()));
		model.addAttribute("activity", activity);

		return "redirect:/activity/activityDisplay"; // 修改成功後轉交activityDisplay.html
	}

    /**
     * activityInfoAll 搜尋
     *
     * @param condition
     *          ActivityQueryCondition
     * @param model
     *          Model
     * @return activityInfoAll.html
     *          String
     */
	@PostMapping("activitySearch")
	public String activitySearch(@ModelAttribute ActivityQueryCondition condition, Model model) {
		List<ActivityForView> activities = activitySvc.findByConditionForView(condition);
		model.addAttribute("activitySearchList", activities);
		
		return "front-end/activity/activityInfoAll";
	}
/********************* action ********************/

/*************** ExceptionHandler ****************/
	// 去除BindingResult中某個欄位的FieldError紀錄
	public BindingResult removeFieldError(Activity activity, BindingResult result, String removedFieldname) {
		List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
				.filter(fieldname -> !fieldname.getField().equals(removedFieldname))
				.toList();
		result = new BeanPropertyBindingResult(activity, "activity");
		for (FieldError fieldError : errorsListToKeep) {
			result.addError(fieldError);
		}
		
		return result;
	}
/*************** ExceptionHandler ****************/
		
}
