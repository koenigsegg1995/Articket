package com.maddog.articket.venue.entity;

import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.venuearea.entity.VenueArea;
import com.maddog.articket.seat.model.entity.Seat;
import com.maddog.articket.venuerental.entity.VenueRental;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "venue")
public class Venue implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "venueID", updatable = false)
	private Integer venueID; // 修改為 Integer

	@Column(name = "venueName", length = 255)
	private String venueName;

	@Column(name = "venuePhone", length = 255)
	private String venuePhone;

	@Column(name = "venueContactPerson", length = 255)
	private String venueContactPerson;

	@Column(name = "venueAddress", length = 255)
	private String venueAddress;

	@Column(name = "venueLocation", length = 255)
	private String venueLocation;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "venue")
	private Set<VenueArea> venueAreas;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "venue")
	private Set<Seat> seats;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "venue")
	private Set<VenueRental> venueRentals;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "venue")
	private Set<Activity> activities;

	public Venue() {
	}

	public Integer getVenueID() { // 修改為 Integer
		return venueID;
	}

	public void setVenueID(Integer venueID) { // 修改為 Integer
		this.venueID = venueID;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public String getVenuePhone() {
		return venuePhone;
	}

	public void setVenuePhone(String venuePhone) {
		this.venuePhone = venuePhone;
	}

	public String getVenueContactPerson() {
		return venueContactPerson;
	}

	public void setVenueContactPerson(String venueContactPerson) {
		this.venueContactPerson = venueContactPerson;
	}

	public String getVenueAddress() {
		return venueAddress;
	}

	public void setVenueAddress(String venueAddress) {
		this.venueAddress = venueAddress;
	}

	public String getVenueLocation() {
		return venueLocation;
	}

	public void setVenueLocation(String venueLocation) {
		this.venueLocation = venueLocation;
	}

	public Set<VenueArea> getVenueAreas() {
		return venueAreas;
	}

	public void setVenueAreas(Set<VenueArea> venueAreas) {
		this.venueAreas = venueAreas;
	}

	public Set<Seat> getSeats() {
		return seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}

	public Set<VenueRental> getVenueRentals() {
		return venueRentals;
	}

	public void setVenueRentals(Set<VenueRental> venueRentals) {
		this.venueRentals = venueRentals;
	}

	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}
}
