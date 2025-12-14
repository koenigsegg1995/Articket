package com.maddog.articket.activityareaprice.dao;

import com.maddog.articket.activityareaprice.entity.ActivityAreaPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface ActivityAreaPriceRepository extends JpaRepository<ActivityAreaPrice, Integer> {

//	因為被ticket fk所以沒用，要去fk設定delete on cascade
//	@Transactional
//	@Modifying
//	@Query(value = "delete from activityareaprice where activityareaprice = ?1", nativeQuery = true)
//	void deleteByPrice(BigDecimal activityAreaPrice);
//===============================================================================
	@Query(value = "SELECT * FROM activityAreaPrice "
			+ "WHERE venueAreaID = ?1 AND activityID = ?2", nativeQuery = true)
	ActivityAreaPrice findActivityAreaPrice(Integer venueAreaID, Integer activityID);

	
	// 新增更新價格的方法
	@Transactional
	@Modifying
	@Query(value = "UPDATE activityAreaPrice SET activityAreaPrice = ?3 "
			+ "WHERE venueAreaID = ?1 AND activityID = ?2", nativeQuery = true)
	int updateActivityAreaPrice(Integer venueAreaID, Integer activityID, BigDecimal newPrice);
}
