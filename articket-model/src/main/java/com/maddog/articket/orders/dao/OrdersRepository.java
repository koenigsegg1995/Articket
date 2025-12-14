package com.maddog.articket.orders.dao;

import com.maddog.articket.orders.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


public interface OrdersRepository extends JpaRepository<Orders, Integer>{

	@Transactional
    @Modifying
    @Query(value = "delete from Orders where orderID = :orderID", nativeQuery = true)
    void deleteByOrderID(@Param("orderID") int orderID);
	
	//查詢訂單
    List<Orders> findByGeneralMemberMemberID(Integer memberID);
	
//	List<Orders> findByGeneralMember_MemberID(Integer memberID);

    List<Orders> findByOrderStatus(Integer orderStatus);

    @Query("SELECT o FROM Orders o WHERE o.generalMember.memberID = :memberID AND o.orderStatus = :status")
    List<Orders> findByMemberIDAndStatus(@Param("memberID") Integer memberID, @Param("status") Integer status);
    
 // 根據會員ID和訂單狀態查找訂單
    List<Orders> findByGeneralMember_MemberIDAndOrderStatus(Integer memberID, Integer orderStatus);
    
    // 查找特定會員的最近訂單（按創建時間降序排序）
    @Query("SELECT o FROM Orders o WHERE o.generalMember.memberID = :memberID ORDER BY o.payTime DESC")
    List<Orders> findRecentOrdersByMemberID(@Param("memberID") Integer memberID);
    
    // 查找使用特定優惠券的訂單
    List<Orders> findByMemberCoupon_MemberCouponID(Integer memberCouponID);
    
    // 根據訂單總額範圍查找訂單
    @Query("SELECT o FROM Orders o WHERE o.actualAmount BETWEEN :minAmount AND :maxAmount")
    List<Orders> findOrdersByAmountRange(@Param("minAmount") BigDecimal minAmount, @Param("maxAmount") BigDecimal maxAmount);
    
    
    
    
    

   
    
}