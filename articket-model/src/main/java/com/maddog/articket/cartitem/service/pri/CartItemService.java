package com.maddog.articket.cartitem.service.pri;

import com.maddog.articket.cartitem.entity.CartItem;

import java.util.List;

/**
 * 購物車明細 Service Interface
 */
public interface CartItemService {

    /**
     * 依購物車ID查詢
     *
     * @param cartId
     *          購物車ID
     * @return 購物車明細清單
     */
    List<CartItem> findByCartId(Integer cartId);

}