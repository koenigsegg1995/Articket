package com.maddog.articket.bookticket.service.pri;

import com.maddog.articket.bookticket.dto.BookedTicketForView;
import com.maddog.articket.bookticket.entity.BookTicket;
import com.maddog.articket.ticket.entity.Ticket;

import java.util.List;

/**
 * 票券訂單 Service Interface
 */
public interface BookTicketService {

	/**
	 * 新增
	 *
	 * @param bookTicket
	 * 			票券訂單 DO
	 * @param ticketList
	 * 	  		新增票券清單
	 */
	void addBookTicket(BookTicket bookTicket, List<Ticket> ticketList);

	/**
	 * 依 memberId 查詢票券訂單 VO 清單
	 *
	 * @param memberId
	 * 			一般會員ID(買家)
	 * @return 票券訂單 VO 清單
	 */
	List<BookedTicketForView> getTicketOrdersByMemberId(Integer memberId);

}
