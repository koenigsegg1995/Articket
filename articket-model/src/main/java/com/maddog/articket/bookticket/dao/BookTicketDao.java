package com.maddog.articket.bookticket.dao;

import com.maddog.articket.bookticket.dto.BookedTicketForView;
import com.maddog.articket.bookticket.entity.BookTicket;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 票券訂單 DAO
 */
@Mapper
public interface BookTicketDao {

	/**
	 * 新增
	 *
	 * @param bookTicket
	 * 			票券訂單 DO
	 * @return 成功筆數
	 */
	int insert(BookTicket bookTicket);

	/**
	 * 依 memberId 查詢票券訂單 VO 清單
	 *
	 * @param memberId
	 * 			一般會員ID(買家)
	 * @return 票券訂單 VO 清單
	 */
	List<BookedTicketForView> findByMemberIdForView(Integer memberId);
	
}
