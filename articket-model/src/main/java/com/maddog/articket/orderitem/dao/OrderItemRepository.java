package com.maddog.articket.orderitem.dao;

import com.maddog.articket.orderitem.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

	 @Transactional
	    @Modifying
	    @Query(value = "delete from OrderItem where orderItemID = :orderItemID", nativeQuery = true)
	    void deleteByOrderItemID(@Param("orderItemID") int orderItemID);
	 
	    // 根據訂單ID查找訂單項目
	    List<OrderItem> findByOrders_OrderID(Integer orderID);
	    
	 // 根據商品ID查找訂單項目
	    List<OrderItem> findByCommodity_CommodityID(Integer commodityID);
	    
	    // 查找特定訂單中數量最多的訂單項目
	    @Query("SELECT oi FROM OrderItem oi WHERE oi.orders.orderID = :orderID ORDER BY oi.orderItemQuantity DESC")
	    List<OrderItem> findMostOrderedItemsByOrderID(@Param("orderID") Integer orderID);
	    
	    // 查找特定商品在所有訂單中的總銷售量
	    @Query("SELECT SUM(oi.orderItemQuantity) FROM OrderItem oi WHERE oi.commodity.commodityID = :commodityID")
	    Integer getTotalSalesByCommodityID(@Param("commodityID") Integer commodityID);
	    
	    // 查找特定訂單中總價最高的訂單項目
	    @Query("SELECT oi FROM OrderItem oi WHERE oi.orders.orderID = :orderID ORDER BY oi.orderItemTotalPrice DESC")
	    List<OrderItem> findMostExpensiveItemsByOrderID(@Param("orderID") Integer orderID);
	}

