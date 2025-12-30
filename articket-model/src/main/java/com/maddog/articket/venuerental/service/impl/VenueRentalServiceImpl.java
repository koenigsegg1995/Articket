package com.maddog.articket.venuerental.service.impl;

import com.maddog.articket.venuerental.dao.VenueRentalDao;
import com.maddog.articket.venuerental.entity.VenueRental;
import com.maddog.articket.venuerental.service.pri.VenueRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 場地申請資料 Service Implementation
 */
@Service("venueRentalService")
public class VenueRentalServiceImpl implements VenueRentalService {

	/**
	 * 場地申請 DAO
	 */
	@Autowired
	private VenueRentalDao venueRentalDao;

	/**
	 * 新增場地申請資料
	 *
	 * @param venueRental
	 * 			VenueRental
	 * @return 成功筆數
	 * 			int
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int addVenueRental(VenueRental venueRental) {
		return venueRentalDao.insert(venueRental);
	}

	/**
	 * 更新場地申請資料
	 *
	 * @param venueRental
	 * 			VenueRental
	 * @return 成功筆數
	 * 			int
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int updateVenueRental(VenueRental venueRental) {
		return venueRentalDao.update(venueRental);
	}

	/**
	 * 依 ID 查詢場地申請資料
	 *
	 * @param venueRentalId
	 * 			Integer
	 * @return 場地申請資料
	 * 			VenueRental
	 */
	@Override
	@Transactional(readOnly = true)
	public VenueRental getOneVenueRental(Integer venueRentalId) {
		return venueRentalDao.findById(venueRentalId);
	}

	/**
	 * 查詢所有場地申請資料清單
	 *
	 * @return 場地申請資料清單
	 * 			List<VenueRental>
	 */
	@Override
	@Transactional(readOnly = true)
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
	@Override
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
	@Override
	@Transactional(readOnly = true)
	public List<VenueRental> findUnNewByPartnerId(Integer partnerId){
		return venueRentalDao.findUnNewByPartnerId(partnerId);
	}

}
