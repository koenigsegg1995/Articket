package com.maddog.articket.bookticket.entity;

import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.activitytimeslot.entity.ActivityTimeSlot;
import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.membercoupon.entity.MemberCoupon;
import com.maddog.articket.ticket.entity.Ticket;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

//票券訂單
@Entity
@Table(name = "bookticket")
public class BookTicket implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookTicketID", updatable = false)
	private Integer bookTicketID; // 票券訂單ID

	@ManyToOne
	@JoinColumn(name = "memberID", referencedColumnName = "memberID")
	private GeneralMember generalMember; // 一般會員 (買家)

	@ManyToOne
	@JoinColumn(name = "activityID", referencedColumnName = "activityID")
	private Activity activity; // 活動

	@ManyToOne
	@JoinColumn(name = "activityTimeSlotID", referencedColumnName = "activityTimeSlotID")
	private ActivityTimeSlot activityTimeSlot; // 活動時段

	@OneToOne
	@JoinColumn(name = "memberCouponID")
	private MemberCoupon memberCoupon; // 會員優惠券

	@Column(name = "bookTime", updatable = false, insertable = false)
	private Timestamp bookTime; // 訂購日期

	@Column(name = "ticketQuantity")
	private Integer ticketQuantity; // 數量

	@Column(name = "totalPrice")
	private BigDecimal totalPrice; // 總金額
	
	@OneToMany(mappedBy = "bookTicket", cascade = CascadeType.ALL)
	@OrderBy("ticketID asc")
	private Set<Ticket> tickets = new HashSet<Ticket>(); // 票券

	// 建構子
	public BookTicket() {
		super();
	}

	public BookTicket(Integer bookTicketID, GeneralMember generalMember, Activity activity,
			ActivityTimeSlot activityTimeSlot, MemberCoupon memberCoupon, Timestamp bookTime, Integer ticketQuantity,
			BigDecimal totalPrice, Set<Ticket> tickets) {
		super();
		this.bookTicketID = bookTicketID;
		this.generalMember = generalMember;
		this.activity = activity;
		this.activityTimeSlot = activityTimeSlot;
		this.memberCoupon = memberCoupon;
		this.bookTime = bookTime;
		this.ticketQuantity = ticketQuantity;
		this.totalPrice = totalPrice;
		this.tickets = tickets;
	}

	// Getters & Setters
	public Integer getBookTicketID() {
		return bookTicketID;
	}

	public void setBookTicketID(Integer bookTicketID) {
		this.bookTicketID = bookTicketID;
	}

	public GeneralMember getGeneralMember() {
		return generalMember;
	}

	public void setGeneralMember(GeneralMember generalMember) {
		this.generalMember = generalMember;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public ActivityTimeSlot getActivityTimeSlot() {
		return activityTimeSlot;
	}

	public void setActivityTimeSlot(ActivityTimeSlot activityTimeSlot) {
		this.activityTimeSlot = activityTimeSlot;
	}

	public MemberCoupon getMemberCoupon() {
		return memberCoupon;
	}

	public void setMemberCoupon(MemberCoupon memberCoupon) {
		this.memberCoupon = memberCoupon;
	}

	public Timestamp getBookTime() {
		return bookTime;
	}

	public void setBookTime(Timestamp bookTime) {
		this.bookTime = bookTime;
	}

	public Integer getTicketQuantity() {
		return ticketQuantity;
	}

	public void setTicketQuantity(Integer ticketQuantity) {
		this.ticketQuantity = ticketQuantity;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Set<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}
	
}
