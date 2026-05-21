package com.maddog.articket.cartitem.service.impl;

import com.maddog.articket.cartitem.dao.CartItemDao;
import com.maddog.articket.cartitem.entity.CartItem;
import com.maddog.articket.cartitem.service.pri.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 購物車明細 Service Implementation
 */
@Service("cartItemService")
public class CartItemServiceImpl implements CartItemService {

    /**
     * 購物車明細 DAO
     */
    @Autowired
    private CartItemDao cartItemDao;

    /**
     * 依購物車ID查詢
     *
     * @param cartId
     *          購物車ID
     * @return 購物車明細清單
     */
    public List<CartItem> findByCartId(Integer cartId) {
        return cartItemDao.findByCartId(cartId);
    }

}