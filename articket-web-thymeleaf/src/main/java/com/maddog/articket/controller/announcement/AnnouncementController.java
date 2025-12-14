package com.maddog.articket.controller.announcement;

import com.maddog.articket.announcement.model.entity.Announcement;
import com.maddog.articket.announcement.model.service.impl.AnnouncementService;
import com.maddog.articket.administrator.entity.Administrator;
import com.maddog.articket.administrator.service.impl.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/announcement")
@Validated
public class AnnouncementController {

    @Autowired
    AnnouncementService announcementSvc;

    @Autowired
    AdministratorService administratorSvc;


    // 管理員公告頁面 沒有側邊攔
    @GetMapping("/listAllAnnouncement")
    public String listAllAnnouncement(HttpSession session, Model model, @RequestParam(defaultValue = "1") int page) {
    	
    	if(session.getAttribute("administratorID") == null) {
    		return "redirect:/adminLogin";
    	}
    	
        int pageSize = 10; // 每頁顯示的公告數量
        Page<Announcement> announcementPage = announcementSvc.getAllPaginated(PageRequest.of(page - 1, pageSize));

        model.addAttribute("announcements", announcementPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", announcementPage.getTotalPages());

        return "back-end-admin/announcement-news/announcement";
    }

    // 廠商公告頁面
    @GetMapping("/allAnnouncement")
    public String allAnnouncement(HttpSession session, Model model, @RequestParam(defaultValue = "1") int page) {
    	
    	if(session.getAttribute("partnerID") == null) {
    		return "redirect:/partnermember/partnerLogin";
    	}
    	
//        List<Announcement> announcementList = announcementSvc.getAll();
//        model.addAttribute("announcements", announcementList);

        int pageSize = 5; // 每頁顯示的公告數量
        Page<Announcement> announcementPage = announcementSvc.getAllPaginated(PageRequest.of(page - 1, pageSize));

        model.addAttribute("announcements", announcementPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", announcementPage.getTotalPages());

        return "back-end-partner/announcement-news/announcement";
    }
    

   

 // 新增公告頁面
    @GetMapping("addAnnouncement")
    public String addAnnouncement(HttpSession session, ModelMap model) {
        // 檢查是否登入
        Integer administratorID = (Integer) session.getAttribute("administratorID");
        if (administratorID == null) {
            return "redirect:/adminLogin";
        }

        Announcement announcement = new Announcement();
        Administrator admin = new Administrator();
        admin.setAdministratorID(administratorID);
        announcement.setAdministrator(admin);
        model.addAttribute("announcement", announcement);

        return "back-end-admin/announcement-news/addAnnouncement";
    }

    // 處理新增公告
    @PostMapping("insert")
    public String insert(@Valid Announcement announcement, BindingResult result, HttpSession session, ModelMap model) throws IOException {
        // 再次檢查是否登入
        Integer administratorID = (Integer) session.getAttribute("administratorID");
        if (administratorID == null) {
            return "redirect:/adminLogin";
        }

        if (result.hasErrors()) {
            return "back-end-admin/announcement-news/addAnnouncement";
        }

        // 設置管理員ID
        announcement.getAdministrator().setAdministratorID(administratorID);

        announcementSvc.addAnnouncement(announcement);

        model.addAttribute("success", "- (新增成功)");
        return "redirect:/announcement/listAllAnnouncement";
    }

 // 獲取要更新的公告
    @PostMapping("getOne_For_Update")
    public String getOne_For_Update(@RequestParam("announcementID") String announcementIDStr, HttpSession session, ModelMap model) {
        // 檢查是否登入
        Integer administratorID = (Integer) session.getAttribute("administratorID");
        if (administratorID == null) {
            return "redirect:/adminLogin";
        }

        Integer announcementID = Integer.valueOf(announcementIDStr);
        Announcement announcement = announcementSvc.getOneAnnouncement(announcementID);

        if (!announcement.getAdministrator().getAdministratorID().equals(administratorID)) {
            model.addAttribute("error", "您沒有權限更新這個公告");
            return "back-end-admin/announcement-news/announcement"; // 返回列表頁面
        }

        model.addAttribute("announcement", announcement);
        return "back-end-admin/announcement-news/updateAnnouncement";
    }

    // 處理公告更新
    @PostMapping("update")
    public String update(@Valid Announcement announcement, BindingResult result, HttpSession session, ModelMap model) throws IOException {
        // 檢查是否登入
        Integer administratorID = (Integer) session.getAttribute("administratorID");
        if (administratorID == null) {
            return "redirect:/adminLogin";
        }

        if (result.hasErrors()) {
            return "back-end-admin/announcement-news/updateAnnouncement";
        }

        // 設置當前登入的管理員ID
        announcement.getAdministrator().setAdministratorID(administratorID);

        announcementSvc.updateAnnouncement(announcement);

        model.addAttribute("success", "- (修改成功)");
        return "redirect:/announcement/listAllAnnouncement";
    }

    @PostMapping("delete")
    public String delete(@RequestParam("announcementID") String announcementID, ModelMap model) {
        announcementSvc.deleteAnnouncement(Integer.valueOf(announcementID));
        List<Announcement> list = announcementSvc.getAll();
        model.addAttribute("announcementListData", list);
        model.addAttribute("success", "- (刪除成功)");
//        return "back-end/announcement/listAllAnnouncement";
        return "back-end-admin/announcement-news/announcement";

    }



    @ModelAttribute("announcementListData")
    protected List<Announcement> referenceListData(Model model) {
        List<Announcement> list = announcementSvc.getAll();
        return list;
    }




    @PostMapping("listAnnouncements_ByCompositeQuery")
    public String listAllAnnouncement(HttpServletRequest req, Model model) {
        Map<String, String[]> map = req.getParameterMap();
        List<Announcement> list = announcementSvc.getAll(map);
        model.addAttribute("announcementListData", list);
//        return "back-end/announcement/listAllAnnouncement";
        return "front-end/announcement-news/listAllAnnouncement";

    }

    @PostMapping("getOne_For_Display")
    public String getOne_For_Display(
            @NotEmpty(message="公告編號: 請勿空白")
            @Digits(integer = 4, fraction = 0, message = "公告編號: 請填數字-請勿超過{integer}位數")
            @Min(value = 1, message = "公告編號: 不能小於{value}")
            @Max(value = 1000, message = "公告編號: 不能超過{value}")
            @RequestParam("announcementID") String announcementID,
            ModelMap model) {

        Announcement announcement = announcementSvc.getOneAnnouncement(Integer.valueOf(announcementID));

        if (announcement == null) {
            model.addAttribute("errorMessage", "查無資料");
//            return "back-end/announcement/select_page";
            return "front-end/announcement-news/select_page";

        }

        model.addAttribute("announcement", announcement);
        model.addAttribute("getOne_For_Display", "true");

//        return "back-end/announcement/select_page";
        return "front-end/announcement-news/select_page";

    }

    @ExceptionHandler(value = { ConstraintViolationException.class })
    public ModelAndView handleError(HttpServletRequest req, ConstraintViolationException e, Model model) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations ) {
            strBuilder.append(violation.getMessage() + "<br>");
        }

        List<Announcement> list = announcementSvc.getAll();
        model.addAttribute("announcementListData", list);

        String message = strBuilder.toString();
//        return new ModelAndView("back-end/announcement/select_page", "errorMessage", "請修正以下錯誤:<br>"+message);
        return new ModelAndView("front-end/announcement-news/select_page", "errorMessage", "請修正以下錯誤:<br>"+message);

    }
    

}