package com.maddog.articket;

import java.util.List;

import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.activity.service.pri.ActivityService;
import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.generalmember.service.impl.GeneralMemberService;
import com.maddog.articket.partnermember.entity.PartnerMember;
import com.maddog.articket.partnermember.service.impl.PartnerMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
	
	@Autowired
    GeneralMemberService gmemberSvc;
	
	@Autowired
    PartnerMemberService partnerSvc;
	
    @Autowired
    ActivityService activitySvc;
	
	//導向首頁
    @GetMapping("/")
    public String index(Model model) {
        return "index"; //view
    }
    
    //網站 Header
    @GetMapping("/header")
    public String getHeader() {
        return "header";
    }
    
    //網站 Footer
    @GetMapping("/footer")
    public String getFooter() {
        return "footer";
    }
    
    //導向後台主頁
    @GetMapping("/backEndPartner")
    public String getPartner() {
        return "/back-end-partner/partner";
    }
    
    //後臺主頁側邊欄，用於 partner.js 第二行
    @GetMapping("/partnerSidebar")
    public String getPartnerSidebar() {
        return "/back-end-partner/partner_sidebar";
    }

    @GetMapping("/generalmember/select_page")
	public String select_page(Model model) {
		return "back-end/generalmember/select_page";
	}
	
	@GetMapping("/partnermember/select_page")
	public String select_page1(Model model) {
		return "back-end/partnermember/select_page";
	}

	@GetMapping("/generalmember/listAllGeneralMember")
	public String listAllGeneralMember(Model model) {
		return "back-end/generalmember/listAllGeneralMember";
	}

	@ModelAttribute("generalMemberListData") // for select_page.html 第97 109行用 // for listAllEmp.html 第85行用
	protected List<GeneralMember> referenceListData(Model model) {

		List<GeneralMember> list = gmemberSvc.getAll();
		return list;
	}
    @GetMapping("/partnermember/listAllPartnerMember")
    public String listAllPartnerMember(Model model) {
    	return "back-end/partnermember/listAllPartnerMember";
    }
    
    @ModelAttribute("partnerMemberListData")  // for select_page.html 第97 109行用 // for listAllEmp.html 第85行用
    protected List<PartnerMember> referenceListData1(Model model) {
    	
    	List<PartnerMember> list = partnerSvc.getAll();
    	return list;
    }
    
 // 導向登入成功會員
 	@GetMapping("/success")
 	public String getsuccess() {
 		return "success";
 	}
 	
 	// 導向登入成功廠商
 	@GetMapping("/successpartner")
 	public String getSuccessPartner() {
 		return "successpartner";
 	}
 	
 	// 導向註冊成功
 	@GetMapping("/successInRegister")
 	public String getsuccessInRegister() {
 		return "successInRegister";
 	}
 	
 	// 導向後臺主頁
 	@GetMapping("/admin")
     public String getAdmin() {
         return "/back-end-admin/admin";
     }

 	// 導向管理員後台側邊攔
 	@GetMapping("/adminSidebar")
 	public String getAdminSidebar() {
 	    return "back-end-admin/admin_sidebar";
 	}
 	
 // inject(注入資料) via application.properties
    @Value("${welcome.message}")
    private String message;
    
 // http://......../hello?name=peter1
    @GetMapping("/hello")
    public String indexWithParam(
            @RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) {
        model.addAttribute("message", name);
        return "index"; //view
    }
	
    //管理員檢舉管理
    @GetMapping("/adminProsecute")
    public String getAdminProsecute(Model model) {
        model.addAttribute("message", "Welcome to Admin Prosecute Page");
        return "/back-end-admin/admin_prosecute";
    }

    //查 activity 全部
  	@ModelAttribute("activityListData")
  	protected List<Activity> referenceListActivityData(Model model) {
      	List<Activity> list = activitySvc.getAll();
      	
      	return list;
  	}
  	
  	//票務須知
 	@GetMapping("/ticketInfo")
    public String getTicketInfo() {
        return "/ticketInfo";
    }
 	
  	//常見問題
 	@GetMapping("/qa")
    public String getQA() {
        return "/QA";
    }

}