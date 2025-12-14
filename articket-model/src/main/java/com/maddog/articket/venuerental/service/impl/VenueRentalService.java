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

	@Autowired
	private SessionFactory sessionFactory;

	public void addVenueRental(VenueRental venueRental) {
		venueRentalRepository.save(venueRental);
	}

	public void updateVenueRental(VenueRental venueRental) {
		venueRentalRepository.save(venueRental);
	}

	public void deleteVenueRental(Integer venueRentalID) {// 刪不掉，寫開心的
		if (venueRentalRepository.existsById(venueRentalID))
			venueRentalRepository.deleteById(venueRentalID);
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

//	public List<VenueRental> getAll(Map<String,String[]> map){//複合查詢，暫時先不做
//		return HibernateUtil_CompositeQuery_VenueRental.getAllC(map,sessionFactory.openSession());
//	}
}
