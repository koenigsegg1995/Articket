package com.maddog.articket.seat.model.entity;

import com.maddog.articket.seatstatus.entity.SeatStatus;
import com.maddog.articket.venuearea.entity.VenueArea;
import com.maddog.articket.venue.entity.Venue;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "seat")
public class Seat implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seatID")
	private Integer seatID;  // 修改為 Integer.

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "venueID", referencedColumnName = "venueID")
	private Venue venue;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "venueAreaID", referencedColumnName = "venueAreaID")
	private VenueArea venueArea;

	@Column(name = "seatName", length = 255)
	private String seatName;

	@Column(name = "seatRow")
	private Integer seatRow;  // 修改為 Integer

	@Column(name = "seatNumber")
	private Integer seatNumber;  // 修改為 Integer

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "seat")
	private Set<SeatStatus> seatStatuses;

	public Seat() {
	}

	public Integer getSeatID() {  // 修改為 Integer
		return seatID;
	}

	public void setSeatID(Integer seatID) {  // 修改為 Integer
		this.seatID = seatID;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public VenueArea getVenueArea() {
		return venueArea;
	}

	public void setVenueArea(VenueArea venueArea) {
		this.venueArea = venueArea;
	}

	public String getSeatName() {
		return seatName;
	}

	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}

	public Integer getSeatRow() {  // 修改為 Integer
		return seatRow;
	}

	public void setSeatRow(Integer seatRow) {  // 修改為 Integer
		this.seatRow = seatRow;
	}

	public Integer getSeatNumber() {  // 修改為 Integer
		return seatNumber;
	}

	public void setSeatNumber(Integer seatNumber) {  // 修改為 Integer
		this.seatNumber = seatNumber;
	}

	public Set<SeatStatus> getSeatStatuses() {
		return seatStatuses;
	}

	public void setSeatStatuses(Set<SeatStatus> seatStatuses) {
		this.seatStatuses = seatStatuses;
	}
}
