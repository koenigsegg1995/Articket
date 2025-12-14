package com.maddog.articket.commoditypicture.dao;

import com.maddog.articket.commoditypicture.entity.CommodityPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface CommodityPictureRepository extends JpaRepository<CommodityPicture, Integer>{

	@Transactional
	@Modifying
	@Query(value = "delete from CommodityPicture where commodityPictureID =?1", nativeQuery = true)
	void deleteByCommodityPictureID(int commodityPictureID);
}
