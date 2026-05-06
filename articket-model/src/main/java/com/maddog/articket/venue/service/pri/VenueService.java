package com.maddog.articket.venue.service.pri;

import com.maddog.articket.venue.entity.Venue;

import java.util.List;

/**
 * 場館 Service Interface
 */
public interface VenueService {

	/**
	 * 查全部
	 *
	 * @return 場館清單
	 */
	List<Venue> getAll();

}
