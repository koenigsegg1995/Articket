package com.maddog.articket.ticket.dao;

import com.maddog.articket.ticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{
		
		//刪除
		@Transactional
		@Modifying
		@Query(value = "DELETE FROM ticket WHERE ticketID = ?1", nativeQuery = true)
		void deleteByTicketID(Integer ticketID);
		
		List<Ticket> findByGeneralMemberMemberID(Integer memberID);
		
		
		
		
}
