package com.maddog.articket.seat.model.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

    @Query(value = "SELECT * FROM seat WHERE venueID = ?1 AND seatName = ?2", nativeQuery = true)
    Seat findByVenueIdAndSeatName(Integer venueID, String seatName);
}
