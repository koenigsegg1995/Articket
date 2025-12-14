package com.maddog.articket.bookticket.service.impl;

import com.maddog.articket.bookticket.dao.BookTicketRepository;
import com.maddog.articket.bookticket.entity.BookTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookTicketService {

	@Autowired
    private BookTicketRepository repository;
	
	//新增
	public void addBookTicket(BookTicket bookTicket) {
		repository.save(bookTicket);
	}
	
	//修改
	public void updateBookTicket(BookTicket bookTicket) {
		repository.save(bookTicket);
	}
	
	//刪除
	public void deleteBookTicket(Integer bookTicketID) {
		if(repository.existsById(bookTicketID)) {
			repository.deleteByBookTicketID(bookTicketID);
		}
	}
	
	//查詢 (單一)
	public BookTicket getOneBookTicket(Integer bookTicketID) {
		Optional<BookTicket> optional = repository.findById(bookTicketID);
		
		return optional.orElse(null);
	}
	
	//查詢 (全部)
	public List<BookTicket> getAll(){
		return repository.findAll();
	}
	
	//查詢 (以 memberID)
	public List<BookTicket> getTicketOrdersByMemberId(Integer memberID) {
	    return repository.findByGeneralMember_MemberIDWithTimeSlot(memberID);
	}
}
