package com.maddog.articket.ticket.dao;

import com.maddog.articket.ticket.entity.Ticket;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 票券 DAO
 */
@Mapper
public interface TicketDao {

	/**
	 * 新增
	 *
	 * @param ticket
	 * 			票券
	 * @return 成功筆數
	 */
	int insert(Ticket ticket);

	/**
	 * 查全部
	 *
	 * @return 票券清單
	 */
	List<Ticket> findAll();

	/**
	 * 依一般會員 ID 查詢
	 *
	 * @param memberId
	 * 			一般會員 ID
	 * @return 票券清單
	 */
	List<Ticket> findByGeneralMemberMemberId(Integer memberId);
		
}
