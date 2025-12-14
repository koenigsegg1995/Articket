package com.maddog.articket.venuerental.dao;

import com.maddog.articket.venuerental.entity.VenueRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VenueRentalRepository extends JpaRepository<VenueRental,Integer>{
	
	 List<VenueRental> findByPartnerMemberPartnerID(Integer partnerID);
	 
	 //查詢 (未新增活動)
	 @Query(value = "SELECT * FROM venueRental WHERE venueRentalStatus = 1 AND partnerID = ?", nativeQuery = true)
	 List<VenueRental> findUnNewByPartnerID(Integer partnerID);
	 
}
