package com.maddog.articket.membercoupon.dao;

import com.maddog.articket.membercoupon.entity.MemberCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MemberCouponRepository extends JpaRepository<MemberCoupon, Integer>{
	
	@Transactional
	@Modifying
	@Query(value = "delete MemberCoupon where memberCouponID =?1", nativeQuery = true)
	void deleteByMemberCouponID(int memberCouponID);
}
