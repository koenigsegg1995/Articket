package com.maddog.articket.venuerental.service.impl;

import com.maddog.articket.venuerental.dao.VenueRentalRepository;
import com.maddog.articket.venuerental.entity.VenueRental;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("venueRentalService")
public class VenueRentalService {

	@Autowired
	VenueRentalRepository venueRentalRepository;

	public void addVenueRental(VenueRental venueRental) {
		venueRentalRepository.save(venueRental);
	}

	public void updateVenueRental(VenueRental venueRental) {
		venueRentalRepository.save(venueRental);
	}

	public VenueRental getOneVenueRental(Integer venueRentalID) {
		Optional<VenueRental> optional = venueRentalRepository.findById(venueRentalID);
		return optional.orElse(null);
	}

	public List<VenueRental> getAll() {
		return venueRentalRepository.findAll();
	}
	
	public List<VenueRental> findByPartnerMemberPartnerID(Integer partnerID) {
        return venueRentalRepository.findByPartnerMemberPartnerID(partnerID);
    }

	public List<VenueRental> findUnNewByPartnerID(Integer partnerID){
		return venueRentalRepository.findUnNewByPartnerID(partnerID);
	}

}
