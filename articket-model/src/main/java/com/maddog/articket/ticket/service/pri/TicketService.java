package com.maddog.articket.ticket.service.pri;

import com.maddog.articket.ticket.entity.Ticket;

import java.util.List;

/**
 * 票券 Service Interface
 */
public interface TicketService {

	/**
	 * 查詢 (全部)
	 *
	 * @return 票卷清單
	 */
	List<Ticket> getAll();

	/**
	 * 依照一般會員 ID 查詢票券
	 *
	 * @param memberId
	 * 			一般會員 ID
	 * @return 票券清單
	 */
	List<Ticket> getTicketsByMemberId(Integer memberId);
		
}
