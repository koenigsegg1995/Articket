package com.maddog.articket.cartitem.service.impl;

import com.maddog.articket.cartitem.dao.CartItemRepository;
import com.maddog.articket.cartitem.entity.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItem> getCartItemsByCartID(Integer cartID) {
        return cartItemRepository.findByCartID(cartID);
    }

    @Transactional
    public void updateCartItemQuantity(Integer cartItemID, Integer newQuantity) {
        CartItem item = cartItemRepository.findById(cartItemID)
            .orElseThrow(() -> new RuntimeException("CartItem not found"));
        item.setCheckedQuantity(newQuantity);
        cartItemRepository.save(item);
    }

    @Transactional
    public void deleteCartItem(Integer cartItemID) {
        cartItemRepository.deleteByCartItemID(cartItemID);
    }
}