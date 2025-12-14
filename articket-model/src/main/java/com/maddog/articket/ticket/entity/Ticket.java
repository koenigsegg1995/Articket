package com.maddog.articket.ticket.entity;

import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.activityareaprice.entity.ActivityAreaPrice;
import com.maddog.articket.activitytimeslot.entity.ActivityTimeSlot;
import com.maddog.articket.bookticket.entity.BookTicket;
import com.maddog.articket.seatstatus.entity.SeatStatus;
import jakarta.persistence.*;
import java.io.Serializable;

//票券
@Entity
@Table(name = "ticket")
public class Ticket implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticketID", updatable = false)
	private Integer ticketID; // 票券ID

	@ManyToOne
	@JoinColumn(name = "memberID", referencedColumnName = "memberID")
	private GeneralMember generalMember; // 一般會員(擁有者)

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seatStatusID", referencedColumnName = "seatStatusID")
	private SeatStatus seatStatus; // 座位狀態

	@ManyToOne
	@JoinColumn(name = "activityAreaPriceID", referencedColumnName = "activityAreaPriceID")
	private ActivityAreaPrice activityAreaPrice; // 活動區域價格

	@ManyToOne
	@JoinColumn(name = "bookTicketID", referencedColumnName = "bookTicketID")
	private BookTicket bookTicket; // 票券訂單

	@ManyToOne
	@JoinColumn(name = "activityTimeSlotID", referencedColumnName = "activityTimeSlotID")
	private ActivityTimeSlot activityTimeSlot; // 活動時段

	// 建構子
	public Ticket() {
		super();
	}

	public Ticket(Integer ticketID, GeneralMember generalMember, SeatStatus seatStatus,
			ActivityAreaPrice activityAreaPrice, BookTicket bookTicket, ActivityTimeSlot activityTimeSlot) {
		super();
		this.ticketID = ticketID;
		this.generalMember = generalMember;
		this.seatStatus = seatStatus;
		this.activityAreaPrice = activityAreaPrice;
		this.bookTicket = bookTicket;
		this.activityTimeSlot = activityTimeSlot;
	}

	// Getters & Setters
	public Integer getTicketID() {
		return ticketID;
	}

	public void setTicketID(Integer ticketID) {
		this.ticketID = ticketID;
	}

	public GeneralMember getGeneralMember() {
		return generalMember;
	}

	public void setGeneralMember(GeneralMember generalMember) {
		this.generalMember = generalMember;
	}

	public SeatStatus getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(SeatStatus seatStatus) {
		this.seatStatus = seatStatus;
	}

	public ActivityAreaPrice getActivityAreaPrice() {
		return activityAreaPrice;
	}

	public void setActivityAreaPrice(ActivityAreaPrice activityAreaPrice) {
		this.activityAreaPrice = activityAreaPrice;
	}

	public BookTicket getBookTicket() {
		return bookTicket;
	}

	public void setBookTicket(BookTicket bookTicket) {
		this.bookTicket = bookTicket;
	}

	public ActivityTimeSlot getActivityTimeSlot() {
		return activityTimeSlot;
	}

	public void setActivityTimeSlot(ActivityTimeSlot activityTimeSlot) {
		this.activityTimeSlot = activityTimeSlot;
	}

	@Override
	public String toString() {
		return "Ticket [ticketID=" + ticketID + ", generalMember.getMemberID()=" + generalMember.getMemberID() + ", seatStatus=" + seatStatus
				+ ", activityAreaPrice=" + activityAreaPrice + ", bookTicket=" + bookTicket + ", activityTimeSlot="
				+ activityTimeSlot + "]";
	}

	

}
