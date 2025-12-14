package com.maddog.articket.venue.service.impl;

import com.maddog.articket.venue.dao.VenueRepository;
import com.maddog.articket.venue.entity.Venue;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("venueService")
public class VenueService {
	@Autowired
	VenueRepository venueRepository;

	@Autowired
	private SessionFactory sessionFactory;

	public void addVenue(Venue venue) {
		venueRepository.save(venue);
	}

	public void updateVenue(Venue venue) {
		venueRepository.save(venue);
	}

	public void deleteVenue(Integer venueID) {// 刪不掉，寫開心的
		if (venueRepository.existsById(venueID))
			venueRepository.deleteById(venueID);
	}

	public Venue getOneVenue(Integer venueID) {
		Optional<Venue> optional = venueRepository.findById(venueID);
		return optional.orElse(null);
	}

	public List<Venue> getAll() {
		return venueRepository.findAll();
	}

//	public List<Venue> getAll(Map<String,String[]> map){//複合查詢，暫時先不做
//		return HibernateUtil_CompositeQuery_Venue.getAllC(map,sessionFactory.openSession());
//	}
}
