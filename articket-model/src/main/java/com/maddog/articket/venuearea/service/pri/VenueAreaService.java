package com.maddog.articket.venuearea.service.pri;

/**
 * 場館區域 Service Interface
 */
public interface VenueAreaService {

	/**
	 * 依場館 ID 和區域名稱查詢場館區域 ID
	 *
	 * @param venueId
	 * 			場館 ID
	 * @param venueAreaName
	 * 			區域代號
	 * @return 場館區域 ID
	 */
	Integer findVenueAreaIdByVenueIdAndVenueAreaName(Integer venueId, String venueAreaName);

}