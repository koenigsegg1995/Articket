package com.maddog.articket.activityareaprice.entity;

import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.venuearea.entity.VenueArea;
import com.maddog.articket.ticket.entity.Ticket;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "activityareaprice")
public class ActivityAreaPrice implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "activityAreaPriceID")
	private Integer activityAreaPriceID;  // 修改為 Integer

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "venueAreaID", referencedColumnName = "venueAreaID")
	private VenueArea venueArea;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "activityID", referencedColumnName = "activityID")
	private Activity activity;
	
	
	@Column(name = "activityAreaPrice", precision = 7, scale = 2)
	private BigDecimal activityAreaPrice;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "activityAreaPrice")
	@OrderBy("ticketID asc")
	private Set<Ticket> tickets = new HashSet<Ticket>();

	public ActivityAreaPrice() {
	}

	public Integer getActivityAreaPriceID() {  // 修改為 Integer
		return activityAreaPriceID;
	}

	public void setActivityAreaPriceID(Integer activityAreaPriceID) {  // 修改為 Integer
		this.activityAreaPriceID = activityAreaPriceID;
	}

	public VenueArea getVenueArea() {
		return venueArea;
	}

	public void setVenueArea(VenueArea venueArea) {
		this.venueArea = venueArea;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public BigDecimal getActivityAreaPrice() {
		return activityAreaPrice;
	}

	public void setActivityAreaPrice(BigDecimal activityAreaPrice) {
		this.activityAreaPrice = activityAreaPrice;
	}

	public Set<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

}
