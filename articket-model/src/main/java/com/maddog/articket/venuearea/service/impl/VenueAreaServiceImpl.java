package com.maddog.articket.venuearea.service.impl;

import com.maddog.articket.venuearea.dao.VenueAreaDao;
import com.maddog.articket.venuearea.service.pri.VenueAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 場館區域 Service Implementation
 */
@Service("venueAreaService")
public class VenueAreaServiceImpl implements VenueAreaService {

	/**
	 * 場館區域 DAO
	 */
	@Autowired
	private VenueAreaDao venueAreaDao;

	/**
	 * 依場館 ID 和區域名稱查詢場館區域 ID
	 *
	 * @param venueId
	 * 			場館 ID
	 * @param venueAreaName
	 * 			區域代號
	 * @return 場館區域 ID
	 */
	@Override
	@Transactional(readOnly = true)
	public Integer findVenueAreaIdByVenueIdAndVenueAreaName(Integer venueId, String venueAreaName) {
		return venueAreaDao.findVenueAreaIdByVenueIdAndVenueAreaName(venueId, venueAreaName);
	}

}