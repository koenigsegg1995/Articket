package com.maddog.articket.ticket.service.impl;

import com.maddog.articket.ticket.dao.TicketDao;
import com.maddog.articket.ticket.entity.Ticket;
import com.maddog.articket.ticket.service.pri.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 票券 Service Implementation
 */
@Service("ticketService")
public class TicketServiceImpl implements TicketService {

	/**
	 * 票券 DAO
	 */
	@Autowired
	private TicketDao dao;

	/**
	 * 查詢 (全部)
	 *
	 * @return 票卷清單
	 */
	@Override
	@Transactional(readOnly = true)
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
	@Override
	@Transactional(readOnly = true)
	public List<Ticket> getTicketsByMemberId(Integer memberId) {
		return dao.findByGeneralMemberMemberId(memberId);
	}
		
}
