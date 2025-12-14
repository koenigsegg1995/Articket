package com.maddog.articket.activitytimeslot.entity;

import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.bookticket.entity.BookTicket;
import com.maddog.articket.seatstatus.entity.SeatStatus;
import com.maddog.articket.ticket.entity.Ticket;
import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Set;

//活動時段
@Entity
@Table(name = "activitytimeslot")
public class ActivityTimeSlot implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "activityTimeSlotID", updatable = false)
	private Integer activityTimeSlotID; // 時段ID

	@ManyToOne
	@JoinColumn(name = "activityID", referencedColumnName = "activityID")
	private Activity activity; // 活動

	@Column(name = "activityTimeSlotDate")
	private Date activityTimeSlotDate; // 日期

	@Column(name = "activityTimeSlot")
	private Integer activityTimeSlot; // 時段 1:早 2:午 3:晚

	@Column(name = "activityTimeSlotSeatAmount")
	private Integer activityTimeSlotSeatAmount; // 時段剩餘座位數

	@OneToMany(mappedBy = "activityTimeSlot", cascade = CascadeType.ALL)
	@OrderBy("seatStatusID asc")
	private Set<SeatStatus> seatStatuses; // 座位狀態

	@OneToMany(mappedBy = "activityTimeSlot", cascade = CascadeType.ALL)
	@OrderBy("bookTicketID asc")
	private Set<BookTicket> bookTickets; // 票券訂單

	@OneToMany(mappedBy = "activityTimeSlot", cascade = CascadeType.ALL)
	@OrderBy("ticketID asc")
	private Set<Ticket> tickets; // 票券

	// 建構子
	public ActivityTimeSlot() {
		super();
	}

	public ActivityTimeSlot(Integer activityTimeSlotID, Activity activity, Date activityTimeSlotDate,
			Integer activityTimeSlot, Integer activityTimeSlotSeatAmount, Set<SeatStatus> seatStatuses,
			Set<BookTicket> bookTickets, Set<Ticket> tickets) {
		super();
		this.activityTimeSlotID = activityTimeSlotID;
		this.activity = activity;
		this.activityTimeSlotDate = activityTimeSlotDate;
		this.activityTimeSlot = activityTimeSlot;
		this.activityTimeSlotSeatAmount = activityTimeSlotSeatAmount;
		this.seatStatuses = seatStatuses;
		this.bookTickets = bookTickets;
		this.tickets = tickets;
	}

	// Getters & Setters
	public Integer getActivityTimeSlotID() {
		return activityTimeSlotID;
	}

	public void setActivityTimeSlotID(Integer activityTimeSlotID) {
		this.activityTimeSlotID = activityTimeSlotID;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Date getActivityTimeSlotDate() {
		return activityTimeSlotDate;
	}

	public void setActivityTimeSlotDate(Date activityTimeSlotDate) {
		this.activityTimeSlotDate = activityTimeSlotDate;
	}

	public Integer getActivityTimeSlot() {
		return activityTimeSlot;
	}

	public void setActivityTimeSlot(Integer activityTimeSlot) {
		this.activityTimeSlot = activityTimeSlot;
	}

	public Integer getActivityTimeSlotSeatAmount() {
		return activityTimeSlotSeatAmount;
	}

	public void setActivityTimeSlotSeatAmount(Integer activityTimeSlotSeatAmount) {
		this.activityTimeSlotSeatAmount = activityTimeSlotSeatAmount;
	}

	public Set<SeatStatus> getSeatStatuses() {
		return seatStatuses;
	}

	public void setSeatStatuses(Set<SeatStatus> seatStatuses) {
		this.seatStatuses = seatStatuses;
	}

	public Set<BookTicket> getBookTickets() {
		return bookTickets;
	}

	public void setBookTickets(Set<BookTicket> bookTickets) {
		this.bookTickets = bookTickets;
	}

	public Set<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	public String getTimeSlotString() {
        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(activityTimeSlotDate);
        String timeStr;
        switch (activityTimeSlot) {
            case 1:
                timeStr = "09:00-12:00";
                break;
            case 2:
                timeStr = "13:00-17:00";
                break;
            case 3:
                timeStr = "18:00-22:00";
                break;
            default:
                timeStr = "未知時段";
        }
        return dateStr + " " + timeStr;
    }

}
