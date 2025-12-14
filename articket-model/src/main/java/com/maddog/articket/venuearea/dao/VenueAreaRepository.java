package com.maddog.articket.venuearea.dao;

import com.maddog.articket.venuearea.entity.VenueArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueAreaRepository extends JpaRepository<VenueArea, Integer> {

    @Query(value = "SELECT venueAreaID FROM venuearea WHERE venueID = ?1 AND venueAreaName = ?2", nativeQuery = true)
    Integer findVenueAreaIdByVenueIdAndVenueAreaName(Integer venueId, String venueAreaName);
}
