package com.maddog.articket.bookticket.service.impl;

import com.maddog.articket.bookticket.dao.BookTicketDao;
import com.maddog.articket.bookticket.dto.BookedTicketForView;
import com.maddog.articket.bookticket.entity.BookTicket;
import com.maddog.articket.bookticket.service.pri.BookTicketService;
import com.maddog.articket.seatstatus.dao.SeatStatusDao;
import com.maddog.articket.seatstatus.service.pri.SeatStatusService;
import com.maddog.articket.ticket.dao.TicketDao;
import com.maddog.articket.ticket.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 票券訂單 Service Implementation
 */
@Service("bookTicketService")
public class BookTicketServiceImpl implements BookTicketService {

	/**
	 * 票券訂單 DAO
	 */
	@Autowired
    private BookTicketDao bookTicketDao;

	/**
	 * 票券 DAO
	 */
	@Autowired
	private TicketDao ticketDao;

	@Autowired
	private SeatStatusDao  seatStatusDao;

	/**
	 * 新增
	 *
	 * @param bookTicket
	 * 			票券訂單 DO
	 * @param ticketList
	 * 	  		新增票券清單
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addBookTicket(BookTicket bookTicket, List<Ticket> ticketList) {
		// 新增訂單
		bookTicketDao.insert(bookTicket);

		for(Ticket ticket : ticketList){
			// 已取回 bookTicketId 並設進 ticket
			ticket.setBookTicketId(bookTicket.getBookTicketId());
			// 新增票券
			ticketDao.insert(ticket);

			// 更新座位狀態
			seatStatusDao.updateToSoldById(ticket.getSeatStatusId());
		}
	}

	/**
	 * 依 memberId 查詢票券訂單 VO 清單
	 *
	 * @param memberId
	 * 			一般會員ID(買家)
	 * @return 票券訂單 VO 清單
	 */
	@Override
	@Transactional(readOnly = true)
	public List<BookedTicketForView> getTicketOrdersByMemberId(Integer memberId) {
	    return bookTicketDao.findByMemberIdForView(memberId);
	}

}
