package com.maddog.articket.cartitem.dao;

import com.maddog.articket.cartitem.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface CartItemRepository extends JpaRepository<CartItem, Integer>{

	@Transactional
	@Modifying
	@Query(value = "delete from CartItem where cartItemID =?1", nativeQuery = true)
	void deleteByCartItemID(int cartItemID);
	
	@Query("SELECT ci FROM CartItem ci WHERE ci.cart.cartID = :cartID")
    List<CartItem> findByCartID(@Param("cartID") Integer cartID);
}
