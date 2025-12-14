package com.maddog.articket.commodity.dao;

import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.commodity.entity.Commodity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommodityRepository extends JpaRepository<Commodity, Integer>{

	@Transactional
	@Modifying
	@Query(value = "delete from Commodity where commodityID =?1", nativeQuery = true)
	void deleteByCommodityID(int commodityID);

	List<Commodity> findByActivity_ActivityID(Integer activityID);
//	List<Activity> findByPartnerMember_PartnerID(Integer partnerID);
	
//	Page<Activity> findActivity(Integer activityID,PageRequest pageRequest);
	
    Page<Commodity> findByActivity_ActivityID(Integer activityID, org.springframework.data.domain.Pageable pageable);


	@Query("SELECT DISTINCT c.activity FROM Commodity c WHERE c.partnerMember.partnerID = :partnerID")
	List<Activity> findActivitiesByPartnerMemberID(@Param("partnerID") Integer partnerID);

	// 添加這個方法來直接從Activity表查詢
	@Query("SELECT a FROM Activity a WHERE a.partnerMember.partnerID = :partnerID")
	List<Activity> findAllActivitiesByPartnerMemberID(@Param("partnerID") Integer partnerID);

	@Query("SELECT DISTINCT c.activity FROM Commodity c")
    List<Activity> findAllDistinctActivities();
	
	
//    Page<Commodity> findByActivityActivityID(Integer activityID, Pageable pageable);

	
//	@Query("SELECT DISTINCT d.activityPicture FROM activity d")
//    List<Activity> findAllDistinctActivitiesPicture();
	
	

}

//	@Query("SELECT DISTINCT c.activity FROM Commodity c WHERE c.partnerMember.partnerID = :partnerID")
//	List<Activity> findActivitiesByPartnerMemberID(@Param("partnerID") Integer partnerID);}

//	List<Activity> findActivitiesByPartnerMemberID(Integer partnerID);
//	List<Commodity> findByPartnerMember_PartnerMemberID(Integer partnerID);


