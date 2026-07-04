package com.maddog.articket.cart.service.impl;

import com.maddog.articket.cart.dao.CartDao;
import com.maddog.articket.cart.entity.Cart;
import com.maddog.articket.cart.service.pri.CartService;
import com.maddog.articket.cartitem.entity.CartItem;
import com.maddog.articket.cartitem.dao.CartItemDao;
import com.maddog.articket.commodity.service.pri.CommodityService;
import com.maddog.articket.orders.entity.Orders;
import com.maddog.articket.orders.dao.OrdersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * 購物車 Service Implementation
 */
@Service("cartService")
public class CartServiceImpl implements CartService {

    /**
     * 購物車 DAO
     */
    @Autowired
    private CartDao cartDao;

    /**
     * 購物車明細 DAO
     */
    @Autowired
    private CartItemDao cartItemDao;

    /**
     * 商品 Service
     */
    @Autowired
    private CommodityService commodityService;

    /**
     * 訂單 DAO
     */
    @Autowired
    private OrdersDao ordersDao;

    /**
     * 依會員ID查詢
     *
     * @param memberId
     *          會員ID
     * @return 購物車
     */
    @Override
    @Transactional(readOnly = true)
    public Cart getCartByMemberId(Integer memberId) {
        return cartDao.findByMemberId(memberId);
    }

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
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addToCart(Integer memberId, Integer commodityId, Integer quantity) {
        // 獲取購物車，或創建一個新的
        Cart cart = getOrCreateCart(memberId);  // 獲取或創建購物車

        CartItem cartItem = cartItemDao.findByCartId(cart.getCartId()).stream()
                .filter(item -> item.getCommodityId().equals(commodityId))
                .findFirst()
                .orElse(null);

        if (cartItem == null) {
            // 如果購物車中沒有該商品，創建新的購物車項目
            cartItem = new CartItem();
            cartItem.setCartId(cart.getCartId());
            cartItem.setCommodityId(commodityId);
            cartItem.setCheckedQuantity(quantity);

            cartItemDao.insert(cartItem);
        } else {
            // 如果購物車中已有該商品，更新數量
            cartItem.setCheckedQuantity(cartItem.getCheckedQuantity() + quantity);
        }

        // 更新購物車總價
        updateCartTotalPrice(cart);
        cartDao.insert(cart);
    }

    /**
     * 計算購物車總金額
     *
     * @param cart
     *          購物車
     */
    @Override
    @Transactional(readOnly = true)
    public void calculateTotalPrice(Cart cart) {
        BigDecimal total = cartItemDao.findByCartId(cart.getCartId()).stream()
                .map(item -> commodityService.getOneCommodity(item.getCommodityId()).getCommodityPrice()
                        .multiply(BigDecimal.valueOf(item.getCheckedQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        cart.setCartTotalPrice(total);
    }

    /**
     * 更新購物車總價
     *
     * @param cart
     *          購物車
     */
    @Override
    @Transactional(readOnly = true)
    public void updateCartTotalPrice(Cart cart) {
        BigDecimal totalPrice = cartItemDao.findByCartId(cart.getCartId()).stream()
                .map(item -> commodityService.getOneCommodity(item.getCommodityId()).getCommodityPrice()
                        .multiply(new BigDecimal(item.getCheckedQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        cart.setCartTotalPrice(totalPrice);
    }

    /**
     * 取得或新增購物車
     *
     * @param memberId
     *          會員ID
     * @return 購物車
     */
    @Transactional
    public Cart getOrCreateCart(Integer memberId) {
        Cart cart = cartDao.findByMemberId(memberId);

        if (cart == null) {
            cart = new Cart();
            cart.setMemberId(memberId);
            cart.setCartTotalPrice(BigDecimal.ZERO);
            cartDao.insert(cart);
        }

        return cart;
    }

    /**
     * 修改數量
     *
     * @param cartItemId
     *          購物車明細ID
     * @param change
     *          修改數量
     */
    @Transactional
    public void changeQuantity(Integer cartItemId, Integer change) {
        CartItem item = cartItemDao.findById(cartItemId);
        if (item == null) {
            throw new RuntimeException("購物車項目不存在");
        }

        int newQuantity = item.getCheckedQuantity() + change;
        if (newQuantity < 1) {
            throw new RuntimeException("商品數量不能小於1");
        }
        item.setCheckedQuantity(newQuantity);
        cartItemDao.update(item);
        calculateTotalPrice(cartDao.findById(item.getCartId()));
    }

    /**
     * 更新數量
     *
     * @param cartItemId
     *          購物車明細ID
     * @param quantity
     *          數量
     */
    @Transactional
    public void updateQuantity(Integer cartItemId, Integer quantity) {
        if (quantity < 1) {
            throw new RuntimeException("商品數量不能小於1");
        }
        CartItem item = cartItemDao.findById(cartItemId);
        if (item == null) {
            throw new RuntimeException("購物車項目不存在");
        }

        item.setCheckedQuantity(quantity);
        cartItemDao.update(item);
        calculateTotalPrice(cartDao.findById(item.getCartId()));
    }

    /**
     * 刪除購物車項目
     *
     * @param cartItemId
     *          購物車明細ID
     */
    @Transactional
    public void removeFromCart(Integer cartItemId) {
        CartItem item = cartItemDao.findById(cartItemId);
        if (item == null) {
            throw new RuntimeException("購物車項目不存在");
        }

        cartItemDao.deleteById(cartItemId);

        Cart cart = cartDao.findById(item.getCartId());
        updateCartTotalPrice(cart); //這要刪掉嗎?
        calculateTotalPrice(cart);
        cartDao.update(cart);
    }

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
    @Transactional
    public Orders processCheckout(Integer memberId, String recipient, String recipientPhone, String recipientEmail, String recipientAddress) {
        Cart cart = getCartByMemberId(memberId);
        if (cartItemDao.findByCartId(cart.getCartId()).isEmpty()) {
            throw new RuntimeException("購物車是空的");
        }

        // 創建新訂單
        Orders order = new Orders();
        order.setMemberId(memberId);
        order.setRecipient(recipient);
        order.setRecipientPhone(recipientPhone);
        order.setRecipientEmail(recipientEmail);
        order.setRecipientAddress(recipientAddress);
        order.setOrderStatus(1); // 1: 未出貨
        order.setPayStatus(1); // 1: 已支付

        BigDecimal totalAmount = calculateTotalAmount(cart);
        order.setActualAmount(totalAmount);

        // 保存訂單
        ordersDao.insert(order);

        // 清空購物車
        cartItemDao.findByCartId(cart.getCartId()).clear();
        cart.setCartTotalPrice(BigDecimal.ZERO);
        cartDao.update(cart);

        return order;
    }

    /**
     * 計算總金額
     *
     * @param cart
     *          購物車
     * @return 總金額
     */
    private BigDecimal calculateTotalAmount(Cart cart) {
        return cartItemDao.findByCartId(cart.getCartId()).stream()
                .map(item -> commodityService.getOneCommodity(item.getCommodityId()).getCommodityPrice().multiply(BigDecimal.valueOf(item.getCheckedQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
