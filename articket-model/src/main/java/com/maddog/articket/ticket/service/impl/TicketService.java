package com.maddog.articket.ticket.service.impl;

import com.maddog.articket.ticket.dao.TicketDao;
import com.maddog.articket.ticket.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ticketService")
public class TicketService {

	@Autowired
	private TicketDao dao;

	/**
	 * 查詢 (全部)
	 *
	 * @return 票卷清單
	 */
	public List<Ticket> getAll(){
		return dao.findAll();
	}

	/**
	 * 依照一般會員 ID 查詢票券
	 *
	 * @param memberId
	 * 			一般會員 ID
	 * @return 票券清單
	 */
	public List<Ticket> getTicketsByMemberId(Integer memberId) {
		return dao.findByGeneralMemberMemberId(memberId);
	}
		
}
