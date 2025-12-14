package com.maddog.articket.activity.dao;

import com.maddog.articket.activity.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {
	
	//刪除
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM activity WHERE activityID = ?1", nativeQuery = true)
	void deleteByActivityID(Integer activityID);
	
    boolean existsByActivityIDAndPartnerMemberPartnerID(Integer activityID, Integer partnerID);
	
}
