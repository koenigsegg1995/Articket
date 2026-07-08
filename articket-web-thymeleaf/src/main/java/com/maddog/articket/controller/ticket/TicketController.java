package com.maddog.articket.controller.ticket;

import com.maddog.articket.activity.dto.ActivityDisplayForView;
import com.maddog.articket.activity.service.pri.ActivityService;
import com.maddog.articket.activitytimeslot.service.pri.ActivityTimeSlotService;
import com.maddog.articket.bookticket.dto.BookTicketForView;
import com.maddog.articket.bookticket.service.pri.BookTicketService;
import com.maddog.articket.activitytimeslot.entity.ActivityTimeSlot;
import com.maddog.articket.bookticket.entity.BookTicket;
import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.generalmember.service.pri.GeneralMemberService;
import com.maddog.articket.partnermember.entity.PartnerMember;
import com.maddog.articket.partnermember.service.pri.PartnerMemberService;
import com.maddog.articket.ticket.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

/**
 * 票券 Controller
 */
@Controller
@RequestMapping("/ticket")
public class TicketController {

	/**
	 * 活動時段 Service
	 */
	@Autowired
	private ActivityTimeSlotService activityTimeSlotService;

	/**
	 * 票券訂單 Service
	 */
	@Autowired
	private BookTicketService bookTicketSvc;

	/**
	 * 廠商會員 Service
	 */
	@Autowired
	private PartnerMemberService partnerSvc;

	/**
	 * 活動 Service
	 */
	@Autowired
	private ActivityService  activityService;

	/**
	 * 一般會員 Service
	 */
	@Autowired
	private GeneralMemberService generalMemberService;
	
/********************* 跳轉 **********************/
//////////////// 前台 ////////////////
	//票券結帳
	@GetMapping("bookTicket")
	public String bookTicket(HttpSession session, ModelMap model) {
		//確認是否登入，未登入重導至會員登入頁面
		if(session.getAttribute("memberID") == null) {
			return "redirect:/generalmember/login";
		}
		
		List<BookTicketForView> ticketList = (List<BookTicketForView>)session.getAttribute("ticketList");
		BigDecimal total = BigDecimal.ZERO;
		
		//選購票券總價
		for(BookTicketForView ticket : ticketList) {
			total = total.add(ticket.getActivityAreaPrice());
		}
		
		model.addAttribute("total", total);
		
		return "front-end/ticket/bookTicket";
	}
//////////////// 前台 ////////////////
	
//////////////// 後台 ////////////////
	//售票資訊
	@GetMapping("ticketDisplay")
	public String ticketDisplay(HttpSession session, ModelMap model) {
		//確認是否登入，未登入重導至廠商登入頁面
		if(session.getAttribute("partnerID") == null) {
			return "redirect:/partnermember/partnerLogin";
		}
		
		//取得 Partner
		Integer partnerID = (Integer)session.getAttribute("partnerID");
		PartnerMember partner = partnerSvc.getOnePartnerMember(partnerID);
		
		//取得廠商所有 Activity
		List<ActivityDisplayForView> activities = activityService.getActivityDisplayForViewByPartnerId(partnerID);

		model.addAttribute("partnerActivityListData", activities);
		
		return "back-end-partner/ticket/ticketDisplay";
	}
//////////////// 後台 ////////////////
/********************* 跳轉 **********************/
	
/********************* action **********************/
	//刪減票券
	@PostMapping("deleteOneTicket")
	public String deleteOneTicket(@RequestParam("count") Integer count, HttpSession session) {
		//確認是否登入，未登入重導至會員登入頁面
		if(session.getAttribute("memberID") == null) {
			return "redirect:/generalmember/login";
		}
		
		List<BookTicketForView> ticketList = (List<BookTicketForView>)session.getAttribute("ticketList");
		Integer id = ticketList.getFirst().getActivityTimeSlot().getActivityId();
		
		ticketList.remove(count - 1);
		
		//若移除至 0 票券，重導至 seatSelect.html
		if(ticketList.isEmpty()) {
			return "redirect:/seatSelect?activityTimeSlot=" + id;
		}
		
		session.setAttribute("ticketList", ticketList);
		
		return "redirect:/ticket/bookTicket";
	}
	
	//取消與結帳
	@PostMapping("confirm")
	public String confirm(@RequestParam("action") String action,
						  @RequestParam("ticketMemberAccounts") String[] ticketMemberAccounts,
						  @RequestParam("totalPrice") String totalPrice,
						  HttpSession session) {
		//確認是否登入，未登入重導至會員登入頁面
		Object memberIdObj = session.getAttribute("memberID");
		Integer memberId;
		if(memberIdObj == null) {
			return "redirect:/generalmember/login";
		} else {
			memberId = (Integer) memberIdObj;
		}
				
		//取消
		if("cancel".equals(action)) {
			return "redirect:/";
		}

		//取得選購票券
		List<BookTicketForView> ticketList = (List<BookTicketForView>) session.getAttribute("ticketList");

		//建立訂單
		BookTicket bookTicket = new BookTicket();
			//設置訂單資料
		bookTicket.setMemberId(memberId);
		ActivityTimeSlot activityTimeSlot = activityTimeSlotService.getActivityTimeSlotById(ticketList.getFirst().getActivityTimeSlot().getActivityTimeSlotId());
		bookTicket.setActivityId(activityTimeSlot.getActivityId());
		bookTicket.setActivityTimeSlotId(activityTimeSlot.getActivityTimeSlotId());
		bookTicket.setTicketQuantity(ticketList.size());
		bookTicket.setTotalPrice(new BigDecimal(totalPrice));

		//設定持有人給票券
		for(int i = 0; i < ticketMemberAccounts.length; i++) {
			//該序號未輸入帳號，跳轉回首頁
			if(ticketMemberAccounts[i] == null) {
				return "redirect:/";
			}

			//取得第 i 個持有人
			try {
				//取得第 i 張票券
//				Ticket ticket = ticketList.get(i);

				GeneralMember member = generalMemberService.getByMemberAccount(ticketMemberAccounts[i]);
				Ticket ticket = new Ticket();
				ticket.setMemberId(member.getMemberId());
				ticket.setSeatStatusId();
				ticket.setBookTicketId(bookTicket.getBookTicketId());
			}catch (Exception e) {
				return "redirect:/";
			}
		}

		//新增訂單
		bookTicketSvc.addBookTicket(bookTicket);
		
		//session 移除選購票券
		session.removeAttribute("ticketList");
		
		//跳轉
		return "redirect:/generalmember/myTicketOrders";
	}
/********************* action **********************/
	
}
