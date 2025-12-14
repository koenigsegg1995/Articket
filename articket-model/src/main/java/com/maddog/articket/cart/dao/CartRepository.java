package com.maddog.articket.cart.dao;

import com.maddog.articket.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer>{
	
    Cart findByGeneralMember_MemberID(Integer memberID);

}




























//package com.tia102g5.cart.model;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.tia102g5.activity.model.Activity;
//import com.tia102g5.generalmember.model.GeneralMember;
//
//import io.lettuce.core.dynamic.annotation.Param;
//
//public interface CartRepository extends JpaRepository<Cart, Integer>{
//	
//	@Transactional
//	@Modifying
//	@Query(value = "delete from Cart where cartID =?1", nativeQuery = true)
//	void deleteByCartID(int cartID);
//	
//	@Query("SELECT c FROM Cart c WHERE c.generalMember.memberID = :memberID")
//    Optional<Cart> findByMemberID(@Param("memberID") GeneralMember member);
//	
////	測試用
////	@Query("SELECT c FROM Cart c WHERE c.generalMember.memberID = :memberID")
////    Optional<Cart> findByMemberID(@Param("memberID") Integer member);
//
////	Optional<Cart> findByMemberID(GeneralMember member);
//	
//	List<Activity> findByMember_MemberID(Integer memberID);
//
//	
////	@Query("SELECT DISTINCT c.Cart FROM Commodity c WHERE c.GeneralMember.memberID = :memberID")
////	List<Cart> findByMember(@Param("partnerID") Integer partnerID);
//
//	// 添加這個方法來直接從Activity表查詢
//	@Query("SELECT a FROM Cart a WHERE a.GeneralMember.memberID = :memberID")
//	List<Cart> findByMemberID(@Param("memberID") Integer memberID);
//
//}
