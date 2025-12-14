package com.maddog.articket.cart.service.impl;

import com.maddog.articket.cart.dao.CartRepository;
import com.maddog.articket.cart.entity.Cart;
import com.maddog.articket.cartitem.entity.CartItem;
import com.maddog.articket.cartitem.dao.CartItemRepository;
import com.maddog.articket.commodity.entity.Commodity;
import com.maddog.articket.commodity.service.impl.CommodityService;
import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.generalmember.service.impl.GeneralMemberService;
import com.maddog.articket.membercoupon.entity.MemberCoupon;
import com.maddog.articket.membercoupon.service.impl.MemberCouponService;
import com.maddog.articket.orderitem.entity.OrderItem;
import com.maddog.articket.orders.entity.Orders;
import com.maddog.articket.orders.dao.OrdersRepository;
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
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private GeneralMemberService generalMemberService;
    
    @Autowired
    private MemberCouponService memberCouponService;
    
    @Autowired
    private OrdersRepository ordersRepository;

    // 獲取購物車
    public Cart getCartByMemberID(Integer generalMember) {
    	return cartRepository.findByGeneralMember_MemberID(generalMember);
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
                 .filter(item -> item.getCommodity().getCommodityID().equals(commodityID))
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
         cartRepository.save(cart);
    	
    	
    	
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
    
    
    // 獲取或創建購物車
//    @Transactional
//    public Cart getOrCreateCart(Integer memberID) {
//        Cart cart = cartRepository.findByGeneralMember_MemberID(memberID);
//        if (cart == null) {
//            cart = new Cart();
//            cart.setGeneralMember(generalMemberService.getOneGeneralMember(memberID));
//            cart.setCartTotalPrice(BigDecimal.ZERO);
//            cart.setCartCreateTime(new Timestamp(System.currentTimeMillis()));
//            return cartRepository.save(cart);
//        }
//        return cart;
//    }
    
    @Transactional
    public Cart getOrCreateCart(Integer memberID) {
        Cart cart = cartRepository.findByGeneralMember_MemberID(memberID);
        if (cart == null) {
            cart = new Cart();
            GeneralMember member = generalMemberService.getOneGeneralMember(memberID);
            cart.setGeneralMember(member);
            cart.setCartTotalPrice(BigDecimal.ZERO);
            cart.setCartCreateTime(new Timestamp(System.currentTimeMillis()));
            cart = cartRepository.save(cart);
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
        cartRepository.save(cart);
    }

    // 結帳
    @Transactional
    public Orders processCheckout(Integer memberId, String recipient, String recipientPhone, String recipientEmail, String recipientAddress, Integer memberCouponId) {
        Cart cart = getCartByMemberID(memberId);
        if (cart.getCartItems().isEmpty()) {
            throw new RuntimeException("購物車是空的");
        }
        
        GeneralMember member = generalMemberService.getOneGeneralMember(memberId);

        

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
        order = ordersRepository.save(order);
        
        // 清空購物車
        cart.getCartItems().clear();
        cart.setCartTotalPrice(BigDecimal.ZERO);
        cartRepository.save(cart);
        
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
        cartRepository.save(cart);
    }
    
    

}





















//package com.tia102g5.cart.model;
//
//import com.tia102g5.cart.model.Cart;
//import com.tia102g5.cart.model.CartRepository;
//import com.tia102g5.cartitem.model.CartItem;
//import com.tia102g5.cartitem.model.CartItemRepository;
//import com.tia102g5.commodity.model.Commodity;
//import com.tia102g5.commodity.model.CommodityService;
//import com.tia102g5.generalmember.model.GeneralMember;
//import com.tia102g5.generalmember.model.GeneralMemberService;
//import com.tia102g5.membercoupon.model.MemberCoupon;
//import com.tia102g5.orders.model.Orders;
//import com.tia102g5.orders.model.OrdersRepository;
//import com.tia102g5.orderitem.model.OrderItem;
//import com.tia102g5.orderitem.model.OrderItemRepository;
//
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//import java.sql.Timestamp;
//import java.util.Optional;
//import java.util.Set;
//import java.util.HashSet;
//import java.util.List;
//
//@Service
//public class CartService {
//
//    @Autowired
//    private CartRepository cartRepository;
//
//    @Autowired
//    private CartItemRepository cartItemRepository;
//
//    @Autowired
//    private CommodityService commodityService;
//
//    @Autowired
//    private GeneralMemberService generalMemberService;
//    
//    @Autowired
//    private OrdersRepository orderRepository;
//    
////    @Transactional
////    public List<Cart> getOrCreateCart(Integer memberID) {
//        // 為了測試，我們假設總是能找到購物車
////        Optional<Cart> existingCart = cartRepository.findByMemberID(member);
////        return existingCart.orElseGet(() -> {
////            Cart newCart = new Cart();
////            // 設置新購物車的屬性...
////            return cartRepository.save(newCart);
////        });
//    	
////        return repository.findByMemberID(memberID);
////
////    }
//    
//    
//    
//    public List<Cart> getOrCreateCart(Integer memberID) {
//        List<Cart> activitiesFromCommodities = repository.findActivitiesByPartnerMemberID(memberID);
//        List<Cart> allActivities = repository.findAllActivitiesByPartnerMemberID(memberID);
//
//        // 合併兩個列表並去重
//        Set<Cart> uniqueActivities = new HashSet<>(activitiesFromCommodities);
//        uniqueActivities.addAll(allActivities);
//
//        return new ArrayList<>(uniqueActivities);
//
////        return repository.findActivitiesByPartnerMemberID(partnerMemberID);
//    }
//    
//
////    @Transactional
////    public Cart getOrCreateCart(Integer memberID) {
////        GeneralMember member = generalMemberService.getOneGeneralMember(memberID);
////        Optional<Cart> existingCart = cartRepository.findByMemberID(member);
////        memberID = 1;
////      return existingCart.get();
//////        if (existingCart.isPresent()) {
//////            return existingCart.get();
//////        } else {
//////            Cart newCart = new Cart();
//////            newCart.setGeneralMember(member);
//////            newCart.setCartTotalPrice(BigDecimal.ZERO);
//////            newCart.setCartCreateTime(new Timestamp(System.currentTimeMillis()));
//////            return cartRepository.save(newCart);
//////        }
////    }
//
////    @Transactional
////    public void addToCart(Integer member, Integer commodityID, Integer quantity) {
////        Cart cart = getOrCreateCart(member);
////        Commodity commodity = commodityService.getOneCommodity(commodityID);
////        
////        Optional<CartItem> existingItem = cart.getCartItems().stream()
////            .filter(item -> item.getCommodity().getCommodityID().equals(commodityID))
////            .findFirst();
////
////        if (existingItem.isPresent()) {
////            CartItem item = existingItem.get();
////            item.setCheckedQuantity(item.getCheckedQuantity() + quantity);
////        } else {
////            CartItem newItem = new CartItem();
////            newItem.setCart(cart);
////            newItem.setCommodity(commodity);
////            newItem.setCheckedQuantity(quantity);
////            cart.getCartItems().add(newItem);
////        }
////
////        updateCartTotalPrice(cart);
////        cartRepository.save(cart);
////    }
//
//    @Transactional
//    public void removeFromCart(Integer cartItemID) {
//        CartItem item = cartItemRepository.findById(cartItemID)
//            .orElseThrow(() -> new RuntimeException("CartItem not found"));
//        Cart cart = item.getCart();
//        cart.getCartItems().remove(item);
//        cartItemRepository.delete(item);
//        updateCartTotalPrice(cart);
//        cartRepository.save(cart);
//    }
//
//    private void updateCartTotalPrice(Cart cart) {
//        BigDecimal total = cart.getCartItems().stream()
//            .map(item -> item.getCommodity().getCommodityPrice()
//                .multiply(BigDecimal.valueOf(item.getCheckedQuantity())))
//            .reduce(BigDecimal.ZERO, BigDecimal::add);
//        cart.setCartTotalPrice(total);
//    }
//
////    public Cart getCartByMemberID(Integer memberID) {
////        GeneralMember member = generalMemberService.getOneGeneralMember(memberID);
////        return cartRepository.findByMemberID(member)
////            .orElseThrow(() -> new RuntimeException("Cart not found for member" + memberID));
////    }
//    
////    public Cart getCartByMemberID(GeneralMember member) {
////        return cartRepository.findByMemberID(member)
////            .orElseThrow(() -> new RuntimeException("Cart not found for member " + member));
////    }
//    
////    測試用
////    public Cart getCartByMemberID(Integer member) {
////        return cartRepository.findByMemberID(member)
////            .orElseThrow(() -> new RuntimeException("Cart not found for member " + member));
////    }
//    
////    public List<CartItem> getCartByMemberID(GeneralMember memberID) {
////        return CartRepository.findByMemberID(memberID);
////    }
//    
//
////    @Transactional
////    public void clearCart(Integer memberID) {
////        Cart cart = getCartByMemberID(memberID);
////        cart.getCartItems().clear();
////        cart.setCartTotalPrice(BigDecimal.ZERO);
////        cartRepository.save(cart);
////    }
//    
//
//
//    @Transactional
//    public void checkout(Integer member) {
////        Cart cart = getCartByMemberID(member);
////        GeneralMember memberID = cart.getGeneralMember();
//
//
//     // 創建新訂單
//        Orders order = new Orders();
////        order.setGeneralMember(member);
////        order.setActualAmount(cart.getCartTotalPrice());
////        order.setPayTime(new Timestamp(System.currentTimeMillis()));
////
////        // 設置收件人信息（假設從會員信息或購物車中獲取）
////        order.setRecipient(member.getMemberName());  // 假設會員模型中有 getMemberName() 方法
////        order.setRecipientPhone(member.getMemberPhone());  // 假設會員模型中有 getPhone() 方法
////        order.setRecipientEmail(member.getMemberAccount());  // 假設會員模型中有 getEmail() 方法
////        order.setRecipientAddress(member.getMemberAddress());  // 假設會員模型中有 getAddress() 方法
//
//        // 設置訂單狀態和支付狀態
//        order.setOrderStatus(1);  // 1: 未出貨
//        order.setPayStatus(1);  // 假設 1 表示已支付，0 表示未支付
//
//        // 設置會員優惠券（如果有使用優惠券的話）       
//      // 假設我們有一個方法來獲取用戶選擇的優惠券ID
////         Integer selectedCouponId = cart.getGeneralMember(member).getMemberCoupons(); // 這個方法需要您自己實現，可能從請求參數中獲取
////
////         if (selectedCouponId != null) {
////             // 從資料庫中獲取對應的 MemberCoupon
////             Optional<MemberCoupon> optionalCoupon = memberCouponRepository.findById(selectedCouponId);
////             
////             if (optionalCoupon.isPresent()) {
////                 MemberCoupon usedCoupon = optionalCoupon.get();
////                 
////                 // 檢查優惠券是否可用
////                 if (usedCoupon.getMemberCouponStatus() == 0 && // 0 表示未使用
////                     usedCoupon.getMemberCouponExpirationDate().after(new Date()) && // 檢查是否過期
////                     usedCoupon.getGeneralMember().getMemberID().equals(member.getMemberID())) { // 確保優惠券屬於當前會員
////                     
////                     order.setMemberCoupon(usedCoupon);
////                     
////                     // 更新優惠券狀態為已使用
////                     usedCoupon.setMemberCouponStatus(1); // 1 表示已使用
////                     memberCouponRepository.save(usedCoupon);
////                     
////                     // 可能需要根據優惠券類型重新計算訂單金額
////                     BigDecimal discountAmount = calculateDiscountAmount(order, usedCoupon);
////                     order.setActualAmount(order.getActualAmount().subtract(discountAmount));
////                 } else {
////                     // 優惠券不可用，可以拋出異常或者記錄日誌
////                     throw new IllegalStateException("所選優惠券不可用");
////                 }
////             } else {
////                 // 找不到對應的優惠券，可以拋出異常或者記錄日誌
////                 throw new IllegalArgumentException("找不到所選優惠券");
////             }
////         }
//
//        // 設置訂單項目
//        Set<OrderItem> orderItems = new HashSet<>();
////        for (CartItem cartItem : cart.getCartItems()) {
////            OrderItem orderItem = new OrderItem();
////            orderItem.setOrders(order);
////            orderItem.setCommodity(cartItem.getCommodity());
////            orderItem.setCommodityOrderPrice(cartItem.getCommodity().getCommodityPrice());
////            orderItem.setOrderItemQuantity(cartItem.getCheckedQuantity());
////            orderItem.setOrderItemTotalPrice(cartItem.getCommodity().getCommodityPrice()
////                    .multiply(BigDecimal.valueOf(cartItem.getCheckedQuantity())));
////            orderItems.add(orderItem);
////        }
//        order.setOrderItems(orderItems);
//
//        // 設置出貨時間為 null，因為訂單剛創建
//        order.setShipTime(null);
//
//        // 保存訂單
//        orderRepository.save(order);
//        
//        
//        
//        
//        
//        
//        
//        
//        
//
//        // 處理購物車中的每個商品
////        for (CartItem cartItem : cart.getCartItems()) {
////            Commodity commodity = cartItem.getCommodity();
////            Integer quantity = cartItem.getCheckedQuantity();
////
////            // 檢查庫存
////            if (commodity.getCommodityStock() < quantity) {
////                throw new RuntimeException("商品 " + commodity.getCommodityName() + " 庫存不足");
////            }
////
////            // 更新商品庫存
////            commodity.setCommodityStock(commodity.getCommodityStock() - quantity);
////            commodityService.updateCommodity(commodity);
////
////            // 將購物車項目轉換為訂單項目
////            OrderItem orderItem = new OrderItem();
////            orderItem.setOrders(order);
////            orderItem.setCommodity(commodity);
////            orderItem.setOrderItemQuantity(quantity);
////            orderItem.setCommodityOrderPrice(commodity.getCommodityPrice());
////            order.getOrderItems().add(orderItem);
////        }
//
//        // 保存訂單
//        orderRepository.save(order);
//
//        // 清空購物車
////        clearCart(member);
//        
//     // 輔助方法
////        private Cart getCartByMemberID(Integer memberID) {
////            return cartRepository.findByMemberID(memberID)
////                .orElseThrow(() -> new RuntimeException("找不到會員的購物車"));
//        
//    }
//    
//    
//    @Transactional
//    public void changeQuantity(Integer cartItemId, Integer change) {
//        CartItem item = cartItemRepository.findById(cartItemId)
//                .orElseThrow(() -> new RuntimeException("購物車項目不存在"));
//        int newQuantity = item.getCheckedQuantity() + change;
//        if (newQuantity < 1) {
//            throw new RuntimeException("商品數量不能小於1");
//        }
//        item.setCheckedQuantity(newQuantity);
//        cartItemRepository.save(item);
//    }
//
//    @Transactional
//    public void updateQuantity(Integer cartItemId, Integer quantity) {
//        if (quantity < 1) {
//            throw new RuntimeException("商品數量不能小於1");
//        }
//        CartItem item = cartItemRepository.findById(cartItemId)
//                .orElseThrow(() -> new RuntimeException("購物車項目不存在"));
//        item.setCheckedQuantity(quantity);
//        cartItemRepository.save(item);
//    }
//
//    @Transactional
//    public void deleteCartItem(Integer cartItemId) {
//        cartItemRepository.deleteById(cartItemId);
//    }
//    
//    
//}