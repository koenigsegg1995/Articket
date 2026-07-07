package com.maddog.articket.venue.service.pri;

import com.maddog.articket.venue.entity.Venue;

import java.util.List;

/**
 * 場館 Service Interface
 */
public interface VenueService {

	/**
	 * 依場館 ID 查詢場館名稱
	 *
	 * @param venueId
	 * 			場館 ID
	 * @return 場館名稱
	 */
	String getNameById(Integer venueId);

	/**
	 * 查全部
	 *
	 * @return 場館清單
	 */
	List<Venue> getAll();

}
