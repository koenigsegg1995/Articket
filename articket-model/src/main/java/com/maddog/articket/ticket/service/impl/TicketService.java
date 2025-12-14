package com.maddog.articket.ticket.service.impl;

import com.maddog.articket.ticket.dao.TicketRepository;
import com.maddog.articket.ticket.entity.Ticket;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("ticketService")
public class TicketService {

		@Autowired
		TicketRepository repository;
		
		@Autowired
		private SessionFactory sessionFactory;
		
		//新增
		public void addTicket(Ticket ticket) {
			repository.save(ticket);
		}
		
		//修改
		public void updateTicket(Ticket ticket) {
			repository.save(ticket);
		}
		
		//刪除
		public void deleteTicket(Integer ticketID) {
			if(repository.existsById(ticketID)) {
				repository.deleteByTicketID(ticketID);
			}
		}
		
		//查詢 (單一)
		public Ticket getOneTicket(Integer ticketID) {
			Optional<Ticket> optional = repository.findById(ticketID);
			
			return optional.orElse(null);
		}
		
		//查詢 (全部)
		public List<Ticket> getAll(){
			return repository.findAll();
		}
		
//		//查詢 (複合)
//		public List<Ticket> getAll(Map<String, String[]> map) {
//			return HibernateUtil_CompositeQuery_Ticket.getAllC(map,sessionFactory.openSession());
//		}
		
		public List<Ticket> getTicketsByMemberID(Integer memberID) {
	        return repository.findByGeneralMemberMemberID(memberID);
	    }
		
}
