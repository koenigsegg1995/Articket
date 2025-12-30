package com.maddog.articket.venuerental.service.pri;

import com.maddog.articket.venuerental.entity.VenueRental;

import java.util.List;

/**
 * 場地申請 Service Interface
 */
public interface VenueRentalService {

	/**
	 * 新增場地申請資料
	 *
	 * @param venueRental
	 * 			VenueRental
	 * @return 成功筆數
	 * 			int
	 */
	int addVenueRental(VenueRental venueRental);

	/**
	 * 更新場地申請資料
	 *
	 * @param venueRental
	 * 			VenueRental
	 * @return 成功筆數
	 * 			int
	 */
	int updateVenueRental(VenueRental venueRental);

	/**
	 * 依 ID 查詢場地申請資料
	 *
	 * @param venueRentalId
	 * 			Integer
	 * @return 場地申請資料
	 * 			VenueRental
	 */
	VenueRental getOneVenueRental(Integer venueRentalId);

	/**
	 * 查詢所有場地申請資料清單
	 *
	 * @return 場地申請資料清單
	 * 			List<VenueRental>
	 */
	List<VenueRental> getAll();

	/**
	 * 依廠商 ID 查詢場地申請資料清單
	 *
	 * @param partnerId
	 * 			Integer
	 * @return 場地申請資料清單
	 * 			List<VenueRental>
	 */
	List<VenueRental> findByPartnerId(Integer partnerId);

	/**
	 * 依廠商 ID 查詢未新增活動清單
	 *
	 * @param partnerId
	 * 			Integer
	 * @return 未新增活動清單
	 * 			List<VenueRental>
	 */
	List<VenueRental> findUnNewByPartnerId(Integer partnerId);

}
