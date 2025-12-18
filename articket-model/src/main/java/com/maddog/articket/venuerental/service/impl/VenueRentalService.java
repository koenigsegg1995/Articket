package com.maddog.articket.venuerental.service.impl;

import com.maddog.articket.venuerental.dao.VenueRentalDao;
import com.maddog.articket.venuerental.entity.VenueRental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("venueRentalService")
public class VenueRentalService {

	@Autowired
	VenueRentalDao venueRentalDao;

	public void addVenueRental(VenueRental venueRental) {
		venueRentalDao.save(venueRental);
	}

	public void updateVenueRental(VenueRental venueRental) {
		venueRentalDao.save(venueRental);
	}

	/**
	 * 依 ID 查詢場地申請資料
	 *
	 * @param venueRentalId
	 * 			Integer
	 * @return 場地申請資料
	 * 			VenueRental
	 */
	@Transactional(readOnly = true)
	public VenueRental getOneVenueRental(Integer venueRentalId) {
		return venueRentalDao.findById(venueRentalId);
	}

	public List<VenueRental> getAll() {
		return venueRentalDao.findAll();
	}

	/**
	 * 依廠商 ID 查詢場地申請資料清單
	 *
	 * @param partnerId
	 * 			Integer
	 * @return 場地申請資料清單
	 * 			List<VenueRental>
	 */
	@Transactional(readOnly = true)
	public List<VenueRental> findByPartnerId(Integer partnerId) {
        return venueRentalDao.findByPartnerId(partnerId);
    }

	/**
	 * 依廠商 ID 查詢未新增活動清單
	 *
	 * @param partnerId
	 * 			Integer
	 * @return 未新增活動清單
	 * 			List<VenueRental>
	 */
	@Transactional(readOnly = true)
	public List<VenueRental> findUnNewByPartnerId(Integer partnerId){
		return venueRentalDao.findUnNewByPartnerId(partnerId);
	}

}
