package com.maddog.articket.bookticket.dao;

import com.maddog.articket.bookticket.entity.BookTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookTicketRepository extends JpaRepository<BookTicket, Integer>{

	//刪除
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM bookTicket WHERE bookTicketID = ?1", nativeQuery = true)
	void deleteByBookTicketID(Integer bookTicketID);
	
	@Query("SELECT bt FROM BookTicket bt JOIN FETCH bt.activityTimeSlot WHERE bt.generalMember.memberID = :memberID")
	List<BookTicket> findByGeneralMember_MemberIDWithTimeSlot(@Param("memberID") Integer memberID);
	
	List<BookTicket> findByGeneralMember_MemberID(Integer memberID);
}
