package com.maddog.articket.controller.ticket;

import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.activity.service.pri.ActivityService;
import com.maddog.articket.activitytimeslot.service.pri.ActivityTimeSlotService;
import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.generalmember.service.impl.GeneralMemberService;
import com.maddog.articket.activitytimeslot.entity.ActivityTimeSlot;
import com.maddog.articket.bookticket.entity.BookTicket;
import com.maddog.articket.bookticket.service.impl.BookTicketService;
import com.maddog.articket.partnermember.entity.PartnerMember;
import com.maddog.articket.partnermember.service.impl.PartnerMemberService;
import com.maddog.articket.ticket.entity.Ticket;
import com.maddog.articket.ticket.service.impl.TicketService;
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
import java.util.Set;

@Controller
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired
	private ActivityTimeSlotService activityTimeSlotService;
	
	@Autowired
	private GeneralMemberService memberSvc;
	
	@Autowired
	private BookTicketService bookTicketSvc;
	
	@Autowired
	private PartnerMemberService partnerSvc;
	
/********************* 跳轉 **********************/
//////////////// 前台 ////////////////
	//票券結帳
	@GetMapping("bookTicket")
	public String bookTicket(HttpSession session, ModelMap model) {
		//確認是否登入，未登入重導至會員登入頁面
		if(session.getAttribute("memberID") == null) {
			return "redirect:/generalmember/login";
		}
		
		List<Ticket> ticketList = (List<Ticket>)session.getAttribute("ticketList");
		BigDecimal total = BigDecimal.ZERO;
		
		//選購票券總價
		for(Ticket ticket : ticketList) {
			total = total.add(ticket.getActivityAreaPrice().getActivityAreaPrice());
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
		Set<Activity> activities = partner.getActivities();

		model.addAttribute("partnerActivityListData", activities);
		
		return "back-end-partner/ticket/ticketDisplay";
	}
//////////////// 後台 ////////////////
/********************* 跳轉 **********************/
	
/********************* action **********************/
	//刪減票券
	@PostMapping("deleteOneTicket")
	public String deleteOneTicket(@RequestParam("count") Integer count, HttpSession session, ModelMap model) {
		//確認是否登入，未登入重導至會員登入頁面
		if(session.getAttribute("memberID") == null) {
			return "redirect:/generalmember/login";
		}
		
		List<Ticket> ticketList = (List<Ticket>)session.getAttribute("ticketList");
		Integer id = ticketList.get(0).getActivityTimeSlotId();
		
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
	public String confirm(@RequestParam("action") String action, @RequestParam("memberID") String memberID, @RequestParam("ticketMemberIDs") String[] ticketMemberIDs,
			@RequestParam("totalPrice") String totalPrice, HttpSession session, ModelMap model) {
		//確認是否登入，未登入重導至會員登入頁面
		if(session.getAttribute("memberID") == null) {
			return "redirect:/generalmember/login";
		}
				
		//取消
		if("cancel".equals(action)) {
			return "redirect:/";
		}
		
		//取得訂單
		BookTicket bookTicket = new BookTicket();
		
		//取得選購票券
		List<Ticket> ticketList = (List<Ticket>)session.getAttribute("ticketList");
		
		//無輸入分票帳號，跳轉回首頁
		if(ticketMemberIDs.length == 0) {
			return "redirect:/";
		}
		//設定持有人給票券
		for(int i = 0; i < ticketMemberIDs.length; i++) {
			//該序號未輸入帳號，跳轉回首頁
			if(ticketMemberIDs[i] == null) {
				return "redirect:/";
			}
			//取得第 i 個持有人
			try {
				GeneralMember ticketMember = memberSvc.getOneGeneralMember(Integer.valueOf(ticketMemberIDs[i]));
				//取得第 i 張票券
				Ticket ticket = ticketList.get(i);

				ticket.setGeneralMember(ticketMember);
				ticket.setBookTicket(bookTicket);
			}catch (Exception e) {
				return "redirect:/";
			}
		}
		
		//將票券設置至訂單
		for(Ticket ticket : ticketList) {
			bookTicket.getTickets().add(ticket);
		}
		
		//設置訂單資料
		bookTicket.setGeneralMember(memberSvc.getOneGeneralMember(Integer.valueOf(memberID))); //未從 session 取帳號
		
		ActivityTimeSlot activityTimeSlot = activityTimeSlotService.getActivityTimeSlotById(ticketList.get(0).getActivityTimeSlotId());
		bookTicket.setActivityId(activityTimeSlot.getActivityId());
		bookTicket.setActivityTimeSlotId(activityTimeSlot.getActivityTimeSlotId());
		
		bookTicket.setTicketQuantity(ticketList.size());
		bookTicket.setTotalPrice(new BigDecimal(totalPrice));
		
		//成立訂單存入資料庫
		bookTicketSvc.addBookTicket(bookTicket);
		
		//session 移除選購票券
		session.removeAttribute("ticketList");
		
		//跳轉
		return "redirect:/generalmember/myTicketOrders";
	}
/********************* action **********************/
	
}
