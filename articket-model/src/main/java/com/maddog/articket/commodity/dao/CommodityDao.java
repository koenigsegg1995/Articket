package com.maddog.articket.commodity.dao;

import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.commodity.entity.Commodity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommodityDao {

	@Query(value = "delete from Commodity where commodityID =?1", nativeQuery = true)
	void deleteById(int commodityId);

	List<Commodity> findByActivityId(Integer activityId);

    Page<Commodity> findByActivityId(Integer activityId, Pageable pageable);

	@Query("SELECT DISTINCT c.activity FROM Commodity c WHERE c.partnerMember.partnerID = :partnerID")
	List<Activity> findActivitiesByMemberId(@Param("partnerID") Integer partnerId);

	// 添加這個方法來直接從Activity表查詢
	@Query("SELECT a FROM Activity a WHERE a.partnerMember.partnerID = :partnerID")
	List<Activity> findAllActivitiesByPartnerMemberID(@Param("partnerID") Integer partnerID);

	@Query("SELECT DISTINCT c.activity FROM Commodity c")
    List<Activity> findAllDistinctActivities();

}