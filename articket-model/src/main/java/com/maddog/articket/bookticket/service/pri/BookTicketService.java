package com.maddog.articket.bookticket.service.pri;

import com.maddog.articket.bookticket.entity.BookTicket;

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
	 */
	void addBookTicket(BookTicket bookTicket);

	/**
	 * 依 memberId 查詢票券訂單 VO 清單
	 *
	 * @param memberId
	 * 			一般會員ID(買家)
	 * @return 票券訂單 VO 清單
	 */
	List<BookTicket> getTicketOrdersByMemberId(Integer memberId);

}
