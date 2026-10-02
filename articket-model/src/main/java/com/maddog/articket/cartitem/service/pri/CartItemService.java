package com.maddog.articket.cartitem.service.pri;

import com.maddog.articket.cartitem.dto.CartItemForView;
import com.maddog.articket.cartitem.entity.CartItem;

import java.util.List;

/**
 * 購物車明細 Service Interface
 */
public interface CartItemService {

    /**
     * 依購物車 ID 查詢
     *
     * @param cartId
     *          購物車 ID
     * @return 購物車明細清單
     */
    List<CartItem> findByCartId(Integer cartId);

    /**
     * 依會員 ID 查詢
     *
     * @param memberId
     *          會員 ID
     * @return 購物車明細清單
     */
    List<CartItemForView> findByMemberId(Integer memberId);

}