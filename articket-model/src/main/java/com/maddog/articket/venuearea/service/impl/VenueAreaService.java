package com.maddog.articket.venuearea.service.impl;

import com.maddog.articket.venuearea.dao.VenueAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VenueAreaService {

	@Autowired
	private VenueAreaRepository venueAreaRepository;

	public Integer findVenueAreaIdByVenueIdAndVenueAreaName(Integer venueId, String venueAreaName) {
		return venueAreaRepository.findVenueAreaIdByVenueIdAndVenueAreaName(venueId, venueAreaName);
	}
}