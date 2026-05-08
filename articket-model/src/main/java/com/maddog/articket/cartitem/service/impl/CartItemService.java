package com.maddog.articket.cartitem.service.impl;

import com.maddog.articket.cartitem.dao.CartItemDao;
import com.maddog.articket.cartitem.entity.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    private CartItemDao cartItemDao;

    public List<CartItem> getCartItemsByCartID(Integer cartID) {
        return cartItemDao.findByCartId(cartID);
    }

    @Transactional
    public void updateCartItemQuantity(Integer cartItemID, Integer newQuantity) {
        CartItem item = cartItemDao.findById(cartItemID)
            .orElseThrow(() -> new RuntimeException("CartItem not found"));
        item.setCheckedQuantity(newQuantity);
        cartItemDao.save(item);
    }

    @Transactional
    public void deleteCartItem(Integer cartItemID) {
        cartItemDao.deleteById(cartItemID);
    }
}