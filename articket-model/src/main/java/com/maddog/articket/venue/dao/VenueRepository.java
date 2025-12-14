package com.maddog.articket.venue.dao;

import com.maddog.articket.venue.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue, Integer> {

}
