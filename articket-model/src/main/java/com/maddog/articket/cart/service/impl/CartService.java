package com.maddog.articket.cart.service.impl;

import com.maddog.articket.cart.dao.CartDao;
import com.maddog.articket.cart.entity.Cart;
import com.maddog.articket.cartitem.entity.CartItem;
import com.maddog.articket.cartitem.dao.CartItemRepository;
import com.maddog.articket.commodity.entity.Commodity;
import com.maddog.articket.commodity.service.impl.CommodityService;
import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.generalmember.service.pri.GeneralMemberService;
import com.maddog.articket.membercoupon.entity.MemberCoupon;
import com.maddog.articket.orderitem.entity.OrderItem;
import com.maddog.articket.orders.entity.Orders;
import com.maddog.articket.orders.dao.OrdersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Service
public class CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private GeneralMemberService generalMemberService;

    @Autowired
    private OrdersDao ordersDao;

    // 獲取購物車
    public Cart getCartByMemberId(Integer memberId) {
    	return cartDao.findByMemberId(memberId);
    }
    
    // 添加商品到購物車
    @Transactional
    public void addToCart(Integer memberID, Integer commodityID, Integer quantity) {
        // 獲取購物車，或創建一個新的
        Cart cart = getOrCreateCart(memberID);  // 獲取或創建購物車
//        // 找到商品
//        Commodity commodity = commodityService.getOneCommodity(commodityID);
//
//        // 查找已有的購物車項目
//        // 更新購物車中的項目
//        Optional<CartItem> existingItem = cart.getCartItems().stream()
//                .filter(item -> item.getCommodity().getCommodityID().equals(commodityID))
//                .findFirst();
//
//        if (existingItem.isPresent()) {
//            // 增加已存在項目的數量
//            CartItem item = existingItem.get();
//            item.setCheckedQuantity(item.getCheckedQuantity() + quantity);
//        } else {
//            // 創建新購物車項目
//            CartItem newItem = new CartItem();
//            newItem.setCart(cart);
//            newItem.setCommodity(commodity);
//            newItem.setCheckedQuantity(quantity);
//            cart.getCartItems().add(newItem);
//        }
//
//        // 更新購物車總價
////        updateCartTotalPrice(cart);
//        calculateTotalPrice(cart);
//        cartRepository.save(cart);
//    	
//    	 Cart cart = cartRepository.findByGeneralMember_MemberID(memberId);
//         if (cart == null) {
//             // 如果購物車不存在，創建新的購物車
//             cart = new Cart();
//             cart.setGeneralMember(/* 獲取會員對象 */);
//             cart = cartRepository.save(cart);
//         }

         Commodity commodity = commodityService.getOneCommodity(commodityID);
         CartItem cartItem = cart.getCartItems().stream()
                 .filter(item -> item.getCommodity().getCommodityId().equals(commodityID))
                 .findFirst()
                 .orElse(null);

         if (cartItem == null) {
             // 如果購物車中沒有該商品，創建新的購物車項目
             cartItem = new CartItem();
             cartItem.setCart(cart);
             cartItem.setCommodity(commodity);
             cartItem.setCheckedQuantity(quantity);
             cart.getCartItems().add(cartItem);
         } else {
             // 如果購物車中已有該商品，更新數量
             cartItem.setCheckedQuantity(cartItem.getCheckedQuantity() + quantity);
         }

         // 更新購物車總價
         updateCartTotalPrice(cart);
         cartDao.insert(cart);
    }

    //購物車總金額
    @Transactional
    public void calculateTotalPrice(Cart cart) {
        BigDecimal total = cart.getCartItems().stream()
                .map(item -> item.getCommodity().getCommodityPrice()
                        .multiply(BigDecimal.valueOf(item.getCheckedQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        cart.setCartTotalPrice(total);
    }
    
    // 更新購物車總價
    @Transactional
    public void updateCartTotalPrice(Cart cart) {
//        BigDecimal total = BigDecimal.ZERO;
//        for (CartItem item : cart.getCartItems()) {
//            BigDecimal itemPrice = item.getCommodity().getCommodityPrice();
//            BigDecimal quantity = BigDecimal.valueOf(item.getCheckedQuantity());
//            total = total.add(itemPrice.multiply(quantity));
//        }
//        cart.setCartTotalPrice(total);
    	
    	BigDecimal totalPrice = cart.getCartItems().stream()
                .map(item -> item.getCommodity().getCommodityPrice().multiply(new BigDecimal(item.getCheckedQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        cart.setCartTotalPrice(totalPrice);
    }
    
    @Transactional
    public Cart getOrCreateCart(Integer memberID) {
        Cart cart = cartDao.findByMemberId(memberID);
        if (cart == null) {
            cart = new Cart();
            GeneralMember member = generalMemberService.getById(memberID);
            cart.setGeneralMember(member);
            cart.setCartTotalPrice(BigDecimal.ZERO);
            cart.setCartCreateTime(new Timestamp(System.currentTimeMillis()));
            cart = cartDao.save(cart);
        }
        return cart;
    }
    
    @Transactional
    public void changeQuantity(Integer cartItemID, Integer change) {
        CartItem item = cartItemRepository.findById(cartItemID)
                .orElseThrow(() -> new RuntimeException("購物車項目不存在"));
        int newQuantity = item.getCheckedQuantity() + change;
        if (newQuantity < 1) {
            throw new RuntimeException("商品數量不能小於1");
        }
        item.setCheckedQuantity(newQuantity);
        cartItemRepository.save(item);
        calculateTotalPrice(item.getCart());
    }
    
    @Transactional
    public void updateQuantity(Integer cartItemId, Integer quantity) {
        if (quantity < 1) {
            throw new RuntimeException("商品數量不能小於1");
        }
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("購物車項目不存在"));
        item.setCheckedQuantity(quantity);
        cartItemRepository.save(item);
        calculateTotalPrice(item.getCart());
    }

   

    // 刪除購物車項目
    @Transactional
    public void removeFromCart(Integer cartItemID) {
        CartItem item = cartItemRepository.findById(cartItemID)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));
        Cart cart = item.getCart();
        cart.getCartItems().remove(item);
        cartItemRepository.delete(item);
        updateCartTotalPrice(cart); //這要刪掉嗎?
        calculateTotalPrice(cart);
        cartDao.save(cart);
    }

    // 結帳
    @Transactional
    public Orders processCheckout(Integer memberId, String recipient, String recipientPhone, String recipientEmail, String recipientAddress, Integer memberCouponId) {
        Cart cart = getCartByMemberId(memberId);
        if (cart.getCartItems().isEmpty()) {
            throw new RuntimeException("購物車是空的");
        }
        
        GeneralMember member = generalMemberService.getById(memberId);

        

        // 創建新訂單
        Orders order = new Orders();
        order.setGeneralMember(member);
        order.setRecipient(recipient);
        order.setRecipientPhone(recipientPhone);
        order.setRecipientEmail(recipientEmail);
        order.setRecipientAddress(recipientAddress);
        order.setOrderStatus(1); // 1: 未出貨
        order.setPayStatus(1); // 1: 已支付
        order.setPayTime(new Timestamp(System.currentTimeMillis()));

        // 設置收件人信息（可以從會員資料中獲取）
//        order.setRecipient("收件人姓名"); // 從會員資料中獲取
//        order.setRecipientPhone("收件人電話"); // 從會員資料中獲取
//        order.setRecipientEmail("收件人Email"); // 從會員資料中獲取
//        order.setRecipientAddress("收件地址"); // 從會員資料中獲取
        
        BigDecimal totalAmount = calculateTotalAmount(cart);
        order.setActualAmount(totalAmount);

//        if (memberCoupon != null) {
//            MemberCoupon coupon = memberCouponService.getOneMemberCoupon(memberCoupon);
//            if (coupon != null && coupon.getMemberCouponStatus() == 0) { // 0: 未使用
//                order.setMemberCoupon(coupon);
//                BigDecimal discountedAmount = applyCouponDiscount(totalAmount, coupon);
//                order.setActualAmount(discountedAmount);
//                
//                coupon.setMemberCouponStatus(1); // 1: 已使用
//                memberCouponService.updateMemberCoupon(coupon);
//            }
//        }
        
        Set<OrderItem> orderItems = new HashSet<>();
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrders(order);
            orderItem.setCommodity(cartItem.getCommodity());
            orderItem.setCommodityOrderPrice(cartItem.getCommodity().getCommodityPrice());
            orderItem.setOrderItemQuantity(cartItem.getCheckedQuantity());
            orderItem.setOrderItemTotalPrice(cartItem.getCommodity().getCommodityPrice().multiply(BigDecimal.valueOf(cartItem.getCheckedQuantity())));
            orderItems.add(orderItem);
        }
        
     // 更新商品庫存
//        Commodity commodity = cartItem.getCommodity();
//        int newStock = commodity.getCommodityStock() - cartItem.getCheckedQuantity();
//        if (newStock < 0) {
//            throw new RuntimeException("商品 " + commodity.getCommodityName() + " 庫存不足");
//        }
        
        
        order.setOrderItems(orderItems);
//        order = ordersRepository.save(order);
        
        // 清空購物車
//        clearCart(cart);
//        return order;
//         保存訂單
//        ordersRepository.save(order);
        
     // 保存訂單
        order = ordersDao.save(order);
        
        // 清空購物車
        cart.getCartItems().clear();
        cart.setCartTotalPrice(BigDecimal.ZERO);
        cartDao.save(cart);
        
        return order;

    }
    
    private BigDecimal calculateTotalAmount(Cart cart) {
        return cart.getCartItems().stream()
                .map(item -> item.getCommodity().getCommodityPrice().multiply(BigDecimal.valueOf(item.getCheckedQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal applyCouponDiscount(BigDecimal totalAmount, MemberCoupon coupon) {
        // 實現優惠券折扣邏輯
        // 這裡假設優惠券有一個折扣率屬性 discountRate
        BigDecimal discountRate = coupon.getCouponType().getCouponTypeDiscount();
        return totalAmount.multiply(discountRate);
    }

    private void clearCart(Cart cart) {
        cart.getCartItems().clear();
        cart.setCartTotalPrice(BigDecimal.ZERO);
        cartDao.save(cart);
    }

}
