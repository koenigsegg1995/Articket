package com.maddog.articket.venue.service.impl;

import com.maddog.articket.venue.dao.VenueDao;
import com.maddog.articket.venue.entity.Venue;
import com.maddog.articket.venue.service.pri.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 場館 Service Implementation
 */
@Service("venueService")
public class VenueServiceImpl implements VenueService {

	/**
	 * 場館 DAO
	 */
	@Autowired
	private VenueDao venueDao;

	/**
	 * 查全部
	 *
	 * @return 場館清單
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Venue> getAll() {
		return venueDao.findAll();
	}

}
