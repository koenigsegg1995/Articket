package com.maddog.articket.controller.commodity;

import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.activity.service.pri.ActivityService;
import com.maddog.articket.commodity.entity.Commodity;
import com.maddog.articket.commodity.service.impl.CommodityService;
import com.maddog.articket.commoditypicture.entity.CommodityPicture;
import com.maddog.articket.commoditypicture.service.impl.CommodityPictureService;
import com.maddog.articket.partnermember.entity.PartnerMember;
import com.maddog.articket.partnermember.service.impl.PartnerMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/commodity")
@Validated
public class CommodityController {

    private static final Logger logger = LoggerFactory.getLogger(CommodityController.class);

	@Autowired
	CommodityService commoditySvc;

	@Autowired
	CommodityPictureService commodityPictureSvc;

	@Autowired
	PartnerMemberService partnerMemberSvc;

	@Autowired
	ActivityService activitySvc;

//	 首頁商城活動頁面的Mapping
	 @GetMapping("/mall_activity")
	    public String mallActivity(Model model, @RequestParam(defaultValue = "1") int page) {
		 
		 
		 List<Activity> activities = commoditySvc.getAllActivities();
	        model.addAttribute("activities", activities);
	        System.out.println("Activities size: " + activities.size());
		 
//	        int pageSize = 6; // 每頁顯示的活動數量
//	        Page<Activity> activityPage = commoditySvc.getAllActivitiesPaginated(PageRequest.of(page - 1, pageSize));
//	        model.addAttribute("activities", activityPage.getContent());
//	        model.addAttribute("currentPage", page);
//	        model.addAttribute("totalPages", activityPage.getTotalPages());		 
		 
//	        int pageSize = 6; // 每頁顯示的活動數量
//	        Page<Activity> activityPage = commoditySvc.getActivity(pageSize, PageRequest.of(page - 1, pageSize));
//	        model.addAttribute("activities", activityPage.getContent());
//	        model.addAttribute("currentPage", page);
//	        model.addAttribute("totalPages", activityPage.getTotalPages());
	     

	        return "front-end/mall/mallActivity";
	    }

	 
	 
//  首頁商城活動商品頁面的Mapping
	@GetMapping("/mall_listActivityCommodities")
	public String listActivityCommodities(@RequestParam(required = false) Integer activityID, Model model,
			@RequestParam(defaultValue = "1") int page) {
//		List<Commodity> commodities = commoditySvc.getCommoditiesByActivity(activityID);
//		model.addAttribute("commodities", commodities);
		
//		Integer activityID = 1; // 固定的 activityID
		 if (activityID == null) {
		        // 如果沒有提供 activityID，可以重定向到一個錯誤頁面或者首頁
		        return "redirect:/error";  // 或者 "redirect:/"
		    }
		
		int pageSize = 9; // 每頁顯示的商品數量
		Page<Commodity> commodityPage = commoditySvc.getCommoditiesByActivityPaginated(activityID,
				PageRequest.of(page - 1, pageSize));

		model.addAttribute("commodityList", commodityPage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", commodityPage.getTotalPages());
		model.addAttribute("activityID", activityID);
		
		return "front-end/mall/listActivityCommodities";
	}
	
	

//  首頁商城商品頁面的Mapping
	@GetMapping("/mall_listOneCommodity")
	public String listOneCommodity(@RequestParam("commodityID") String commodityID, ModelMap model) {
		Commodity commodity = commoditySvc.getOneCommodity(Integer.valueOf(commodityID));
		model.addAttribute("commodity", commodity);
		return "front-end/mall/listOneCommodity";
	}
	
	
	
	
	
	
	
	
//  測試修改廠商後臺顯示ID為1的商品PR
//  後台商城活動頁面,根據 partnerID 顯示已申請的活動
	@GetMapping("/activityCommodityList")
	public String getActivityByPartnerID(ModelMap model, HttpSession session) {
		
	    Integer partnerID = (Integer) session.getAttribute("partnerID");
	    if (partnerID == null) {
	        // 如果 session 中沒有 partnerID，可能用戶未登錄
	        return "redirect:/partnermember/partnerLogin"; // 重定向到登錄頁面
	    }
//		Integer partnerID = 1; // 這裡應該是從session或其他地方獲取當前登錄的partnermember ID
		List<Activity> activities = commoditySvc.getActivitiesByPartnerMember(partnerID);

		model.addAttribute("activities", activities);

		// 添加這行用於調試
		System.out.println("Activities size: " + activities.size());
		return "/back-end-partner/commodity/activityCommodity";
	}
	
	

	@GetMapping("/listAllCommodity")
	public String listAllCommodity(@RequestParam Integer activityID,
	                               @RequestParam(defaultValue = "1") int page,
	                               ModelMap model,
	                               HttpSession session) {

	    Integer partnerID = (Integer) session.getAttribute("partnerID");
	    if (partnerID == null) {
	        logger.warn("Attempt to access commodity list without login");
	        return "redirect:/partnermember/partnerLogin";
	    }

	    int pageSize = 5; // 每頁顯示的商品數量

	    try {
	        // 首先獲取活動信息
	        Activity activity = activitySvc.getOneActivity(activityID);
	        if (activity == null) {
	            logger.warn("Activity not found for ID: {}", activityID);
	            model.addAttribute("error", "找不到指定的活動");
	            return "back-end-partner/error";
	        }

	        // 檢查該活動是否屬於當前登錄的合作夥伴
	        if (!commoditySvc.isActivityOwnedByPartner(activityID, partnerID)) {
	            logger.warn("Partner {} attempted to access activity {} which they don't own", partnerID, activityID);
	            model.addAttribute("error", "您沒有權限查看此活動的商品");
	            return "back-end-partner/error";
	        }

	        // 獲取分頁的商品列表
	        Page<Commodity> commodityPage = commoditySvc.getCommoditiesByActivityPaginated(activityID, PageRequest.of(page - 1, pageSize));

	        // 獲取該活動的所有商品（不分頁）
	        List<Commodity> commodities = commoditySvc.getCommoditiesByActivity(activityID);

	        // 添加活動信息到模型
	        model.addAttribute("activity", activity);
	        model.addAttribute("activityName", activity.getActivityName());
	        model.addAttribute("activityID", activityID);

	        // 添加商品信息到模型
	        model.addAttribute("commodityList", commodityPage.getContent());
	        model.addAttribute("commodities", commodities);
	        model.addAttribute("currentPage", page);
	        model.addAttribute("totalPages", commodityPage.getTotalPages());

	        logger.info("Retrieved {} commodities for activity ID: {} ({}), partner ID: {}", 
	                    commodityPage.getContent().size(), activityID, activity.getActivityName(), partnerID);

	    } catch (Exception e) {
	        logger.error("Error retrieving commodities for activity ID: " + activityID + " and partner ID: " + partnerID, e);
	        model.addAttribute("error", "無法獲取商品列表，請稍後再試");
	        return "back-end-partner/error";
	    }

	    return "back-end-partner/commodity/commodity";
	}
//    @Controller
//    public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
//
//        @RequestMapping("/error")
//        public String handleError(HttpServletRequest request, Model model) {
//            // 獲取錯誤信息
//            Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//            if (status != null) {
//                int statusCode = Integer.parseInt(status.toString());
//                model.addAttribute("statusCode", statusCode);
//            }
//            return "error";  // 創建一個 error.html 視圖
//        }
//
//        public String getErrorPath() {
//            return "/error";
//        }
//    }

	@ModelAttribute("commodityListData")
	protected List<Commodity> referenceListData(Model model) {
		List<Commodity> list = commoditySvc.getAll();
		return list;
	}

//    @GetMapping("addCommodity")
//    public String addCommodity(@RequestParam("activityID") Integer activityID,
//                               @RequestParam("partnerID") Integer partnerID,
//                               ModelMap model) {
//        Commodity commodity = new Commodity();
//        Activity activity = activitySvc.getOneActivity(activityID);
//        PartnerMember partnerMember = partnerMemberSvc.getOnePartnerMember(partnerID);
//
//        commodity.setActivity(activity);
//        commodity.setPartnerMember(partnerMember);
//
//        model.addAttribute("commodity", commodity);
//        model.addAttribute("activityID", activityID);
//        model.addAttribute("partnerID", partnerID);
//
//        return "back-end-partner/commodity/addCommodity";
//    }

	
	
	// 新增商品
	@GetMapping("addCommodity")
	public String addCommodity(@RequestParam Integer activityID, 
	                           ModelMap model, 
	                           HttpSession session) {
	    Integer partnerID = (Integer) session.getAttribute("partnerID");
	    if (partnerID == null) {
	        return "redirect:/partnermember/partnerLogin";
	    }

	    Commodity commodity = new Commodity();
	    PartnerMember partnerMember = partnerMemberSvc.getOnePartnerMember(partnerID);
	    Activity activity = activitySvc.getOneActivity(activityID);

	    if (activity == null || !activity.getPartnerMember().getPartnerID().equals(partnerID)) {
	        model.addAttribute("error", "無效的活動ID");
	        return "redirect:/commodity/listAllCommodity?activityID=" + activityID;
	    }

	    commodity.setPartnerMember(partnerMember);
	    commodity.setActivity(activity);
	    model.addAttribute("commodity", commodity);
	    model.addAttribute("activityID", activityID);

	    return "back-end-partner/commodity/addCommodity";
	}

	@PostMapping("insert")
	public String insert(@Valid Commodity commodity, 
	                     BindingResult result, 
	                     @RequestParam Integer activityID,
	                     @RequestParam(value = "commodityPic", required = false) MultipartFile[] parts,
	                     RedirectAttributes redirectAttributes,
	                     HttpSession session) throws IOException {
	    Integer partnerID = (Integer) session.getAttribute("partnerID");
	    if (partnerID == null) {
	        return "redirect:/partnermember/partnerLogin";
	    }

	    if (result.hasErrors()) {
	        return "back-end-partner/commodity/addCommodity";
	    }

	    // 確保商品關聯到正確的合作夥伴和活動
	    PartnerMember partnerMember = partnerMemberSvc.getOnePartnerMember(partnerID);
	    Activity activity = activitySvc.getOneActivity(activityID);
	    commodity.setPartnerMember(partnerMember);
	    commodity.setActivity(activity);

	    // 保存商品
	    commoditySvc.addCommodity(commodity);

	    // 處理圖片上傳邏輯
	    if (parts != null && parts.length > 0) {
	        for (MultipartFile part : parts) {
	            if (!part.isEmpty()) {
	                CommodityPicture commodityPicture = new CommodityPicture();
	                commodityPicture.setCommodity(commodity);
	                commodityPicture.setCommodityPicture(part.getBytes());
	                commodityPictureSvc.addCommodityPicture(commodityPicture);
	            }
	        }
	    }

	    redirectAttributes.addFlashAttribute("success", "商品新增成功");
	    return "redirect:/commodity/listAllCommodity?activityID=" + activityID;
	}
	
	
	
	
	
	@PostMapping("updateCommodity")
	public String updateCommodity(@RequestParam("commodityID") String commodityIDStr, 
	                              @RequestParam("activityID") Integer activityID,
	                              ModelMap model,
	                              HttpSession session) {
	    Integer partnerID = (Integer) session.getAttribute("partnerID");
	    if (partnerID == null) {
	        return "redirect:/partnermember/partnerLogin";
	    }

	    Integer commodityID = Integer.valueOf(commodityIDStr);
	    Commodity commodity = commoditySvc.getOneCommodity(commodityID);

	    // 檢查商品是否屬於當前登錄的合作夥伴
	    if (!commodity.getPartnerMember().getPartnerID().equals(partnerID)) {
	        model.addAttribute("error", "您沒有權限修改此商品");
	        return "redirect:/commodity/listAllCommodity?activityID=" + activityID;
	    }

	    model.addAttribute("commodity", commodity);
	    model.addAttribute("activityID", activityID);

	    return "back-end-partner/commodity/updateCommodity";
	}
	
	
	
	@PostMapping("update")
	public String update(@Valid Commodity commodity, 
	                     BindingResult result, 
	                     @RequestParam("activityID") Integer activityID,
	                     @RequestParam(value = "commodityPic", required = false) MultipartFile[] parts, 
	                     RedirectAttributes redirectAttributes,
	                     HttpSession session) throws IOException {
	    Integer partnerID = (Integer) session.getAttribute("partnerID");
	    if (partnerID == null) {
	        redirectAttributes.addFlashAttribute("error", "請先登錄");
	        return "redirect:/partnermember/partnerLogin";
	    }

	    if (result.hasErrors()) {
	        redirectAttributes.addFlashAttribute("error", "表單驗證失敗，請檢查輸入");
	        return "redirect:/commodity/updateCommodity?commodityID=" + commodity.getCommodityID() + "&activityID=" + activityID;
	    }

	    try {
	        // 獲取原有的商品信息
	        Commodity originalCommodity = commoditySvc.getOneCommodity(commodity.getCommodityID());

	        // 檢查商品是否屬於當前登錄的合作夥伴
	        if (!originalCommodity.getPartnerMember().getPartnerID().equals(partnerID)) {
	            redirectAttributes.addFlashAttribute("error", "您沒有權限修改此商品");
	            return "redirect:/commodity/listAllCommodity?activityID=" + activityID;
	        }

	        // 確保活動ID和合作夥伴ID不變
	        commodity.setActivity(originalCommodity.getActivity());
	        commodity.setPartnerMember(originalCommodity.getPartnerMember());

	        // 處理圖片更新邏輯
	        if (parts != null && parts.length > 0) {
	            for (MultipartFile part : parts) {
	                if (!part.isEmpty()) {
	                    CommodityPicture commodityPicture = new CommodityPicture();
	                    commodityPicture.setCommodity(commodity);
	                    commodityPicture.setCommodityPicture(part.getBytes());
	                    commodityPictureSvc.addCommodityPicture(commodityPicture);
	                }
	            }
	        }

	        // 更新商品
	        commoditySvc.updateCommodity(commodity);

	        redirectAttributes.addFlashAttribute("success", "商品修改成功");
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("error", "更新商品時發生錯誤: " + e.getMessage());
	        return "redirect:/commodity/updateCommodity?commodityID=" + commodity.getCommodityID() + "&activityID=" + activityID;
	    }

	    return "redirect:/commodity/listAllCommodity?activityID=" + activityID;
	}
	
	
	
	

	@PostMapping("delete")
	public String delete(@RequestParam("commodityID") String commodityID, ModelMap model) {
		commoditySvc.deleteCommodity(Integer.valueOf(commodityID));
		List<Commodity> list = commoditySvc.getAll();
		model.addAttribute("commodityListData", list);
		model.addAttribute("success", "- (刪除成功)");
		return "back-end-partner/commodity/commodity";
	}

//    複合查詢
//    @PostMapping("listCommodities_ByCompositeQuery")
//    public String listAllCommodity(HttpServletRequest req, Model model) {
//        Map<String, String[]> map = req.getParameterMap();
//        List<Commodity> list = commoditySvc.getAll(map);
//        model.addAttribute("commodityListData", list);
//        return "front-end/commodity/listAllCommodity";
//    }

	@PostMapping("getOne_For_Display")
	public String getOne_For_Display(
			@NotEmpty(message = "商品編號: 請勿空白") @Digits(integer = 4, fraction = 0, message = "商品編號: 請填數字-請勿超過{integer}位數") @Min(value = 1, message = "商品編號: 不能小於{value}") @Max(value = 1000, message = "商品編號: 不能超過{value}") @RequestParam("commodityID") String commodityID,
			ModelMap model) {

		Commodity commodity = commoditySvc.getOneCommodity(Integer.valueOf(commodityID));

		if (commodity == null) {
			model.addAttribute("errorMessage", "查無資料");
			return "back-end-partner/commodity/commodity";
		}

		model.addAttribute("commodity", commodity);
		model.addAttribute("getOne_For_Display", "true");

		return "back-end-partner/commodity/commodity";
	}

//	@GetMapping("/list")
//	public String listCommodities(@RequestParam(defaultValue = "1") int page, Model model) {
//		int pageSize = 10; // 每頁顯示的商品數量
//		Page<Commodity> commodityPage = commoditySvc.getAllPaginated(PageRequest.of(page - 1, pageSize));
//
//		model.addAttribute("commodities", commodityPage.getContent());
//		model.addAttribute("currentPage", page);
//		model.addAttribute("totalPages", commodityPage.getTotalPages());
//
//		return "back-end-partner/commodity/commodity";
//	}

//	@PostMapping("/setPostTime")
//	public String setPostTime(
//			@RequestParam("postTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime postTime) {
//		// 實現設置上架時間的邏輯
//		return "redirect:/commodity/list";
//	}

//    @GetMapping("/activityList")
//    public String listActivities(@RequestParam(defaultValue = "1") int page, Model model) {
//        int pageSize = 10; // 每頁顯示的活動數量
//        Page<Activity> activityPage = activitySvc.getAllPaginated(PageRequest.of(page - 1, pageSize));
//
//        model.addAttribute("activities", activityPage.getContent());
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPages", activityPage.getTotalPages());
//
//        return "front-end/commodity/activityCommodity";
//    }

	@GetMapping("/listByActivity/{activityID}")
	public String listCommoditiesByActivity(@PathVariable Integer activityID, Model model) {
		List<Commodity> commodities = commoditySvc.getCommoditiesByActivity(activityID);
		model.addAttribute("commodities", commodities);
		return "back-end-partner/commodity/commodityByActivity"; // 需要創建這個新的視圖
//        return "back-end-partner/commodity/commodity";

	}
//    ActivityService getCommoditiesByActivity()

	@ExceptionHandler(value = { ConstraintViolationException.class })
	public ModelAndView handleError(HttpServletRequest req, ConstraintViolationException e, Model model) {
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		StringBuilder strBuilder = new StringBuilder();
		for (ConstraintViolation<?> violation : violations) {
			strBuilder.append(violation.getMessage() + "<br>");
		}

		List<Commodity> list = commoditySvc.getAll();
		model.addAttribute("commodityListData", list);

		String message = strBuilder.toString();
		return new ModelAndView("back-end-partner/commodity/select_page", "errorMessage", "請修正以下錯誤:<br>" + message);
	}
}