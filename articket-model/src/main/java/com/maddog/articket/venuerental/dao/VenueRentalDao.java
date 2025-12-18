package com.maddog.articket.venuerental.dao;

import com.maddog.articket.venuerental.entity.VenueRental;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VenueRentalDao {

	/**
	 * 依 ID 查詢場地申請資料
	 *
	 * @param venueRentalId
	 * 			Integer
	 * @return 場地申請資料
	 * 			VenueRental
	 */
	VenueRental findById(Integer venueRentalId);

	List<VenueRental> findAll();

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
