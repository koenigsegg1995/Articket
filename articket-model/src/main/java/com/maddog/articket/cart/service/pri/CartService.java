package com.maddog.articket.cart.service.pri;

import com.maddog.articket.cart.entity.Cart;
import com.maddog.articket.orders.entity.Orders;

/**
 * 購物車 Service Interface
 */
public interface CartService {

    /**
     * 依會員ID查詢
     *
     * @param memberId
     *          會員ID
     * @return 購物車
     */
    Cart getCartByMemberId(Integer memberId);

    /**
     * 添加商品到購物車
     *
     * @param memberId
     *          會員ID
     * @param commodityId
     *          商品ID
     * @param quantity
     *          數量
     */
    void addToCart(Integer memberId, Integer commodityId, Integer quantity);

    /**
     * 計算購物車總金額
     *
     * @param cart
     *          購物車
     */
    void calculateTotalPrice(Cart cart);
    /**
     * 更新購物車總價
     *
     * @param cart
     *          購物車
     */
    void updateCartTotalPrice(Cart cart);

    /**
     * 取得或新增購物車
     *
     * @param memberId
     *          會員ID
     * @return 購物車
     */
    Cart getOrCreateCart(Integer memberId);

    /**
     * 修改數量
     *
     * @param cartItemId
     *          購物車明細ID
     * @param change
     *          修改數量
     */
    void changeQuantity(Integer cartItemId, Integer change);

    /**
     * 更新數量
     *
     * @param cartItemId
     *          購物車明細ID
     * @param quantity
     *          數量
     */
    void updateQuantity(Integer cartItemId, Integer quantity);

    /**
     * 刪除購物車項目
     *
     * @param cartItemId
     *          購物車明細ID
     */
    void removeFromCart(Integer cartItemId);

    /**
     * 結帳
     *
     * @param memberId
     *          會員ID
     * @param recipient
     *          收件人姓名
     * @param recipientPhone
     *          收件人電話
     * @param recipientEmail
     *          收件人Email
     * @param recipientAddress
     *          收件地址
     * @return 訂單
     */
    Orders processCheckout(Integer memberId,
                                  String recipient,
                                  String recipientPhone,
                                  String recipientEmail,
                                  String recipientAddress);

}
