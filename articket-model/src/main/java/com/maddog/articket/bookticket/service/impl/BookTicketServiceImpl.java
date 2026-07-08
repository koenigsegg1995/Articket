package com.maddog.articket.bookticket.service.impl;

import com.maddog.articket.bookticket.dao.BookTicketDao;
import com.maddog.articket.bookticket.dto.BookedTicketForView;
import com.maddog.articket.bookticket.entity.BookTicket;
import com.maddog.articket.bookticket.service.pri.BookTicketService;
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
	 * 新增
	 *
	 * @param bookTicket
	 * 			票券訂單 DO
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addBookTicket(BookTicket bookTicket) {
		bookTicketDao.insert(bookTicket);
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
