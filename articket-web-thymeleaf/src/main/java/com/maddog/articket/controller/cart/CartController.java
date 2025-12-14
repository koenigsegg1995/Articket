package com.maddog.articket.controller.cart;

import com.maddog.articket.cart.entity.Cart;
import com.maddog.articket.cart.service.impl.CartService;
import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.generalmember.service.impl.GeneralMemberService;
import com.maddog.articket.cartitem.service.impl.CartItemService;
import com.maddog.articket.membercoupon.service.impl.MemberCouponService;
import com.maddog.articket.orders.entity.Orders;
import com.maddog.articket.orders.service.impl.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cart")
@Validated
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private GeneralMemberService memberService;

	@Autowired
	private MemberCouponService couponService;

	@Autowired
	private OrdersService ordersService;

	@GetMapping("/view")
	public String viewCart(Model model) {
		Integer generalMember = 1; // 假定會員ID為1
		Cart cart = cartService.getCartByMemberID(generalMember);
		cartService.calculateTotalPrice(cart); // 添加這行
		model.addAttribute("cart", cart);
		return "/front-end/mall/mallCart";
	}

	@PostMapping("/add")
	public String addToCart(@RequestParam Integer commodityID, 
            @RequestParam Integer quantity,
            @RequestParam(required = false) String redirect) {
			Integer generalMember = 1; // 假定會員ID為1
		cartService.addToCart(generalMember, commodityID, quantity);
		
		if ("checkout".equals(redirect)) {
            return "redirect:/cart/checkout";
        }
        return "redirect:/cart/view";
	}
	
	@GetMapping("/add")
    public String addToCartAndRedirect(@RequestParam Integer commodityID, 
                                       @RequestParam Integer quantity,
                                       @RequestParam(required = false) String redirect) {
        return addToCart(commodityID, quantity, redirect);
    }
	
	@PostMapping("/addAjax")
    @ResponseBody
    public ResponseEntity<?> addToCartAjax(@RequestParam Integer commodityID, 
                                           @RequestParam Integer quantity) {
        try {
            Integer memberId = 1; // 假設會員ID為1
            cartService.addToCart(memberId, commodityID, quantity);
            return ResponseEntity.ok().body("商品已成功加入購物車！");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("添加商品到購物車失敗：" + e.getMessage());
        }
    }
	
	
	

	@PostMapping("/update/{cartItemId}")
	public String updateCartItem(@PathVariable Integer cartItemId, @RequestParam(required = false) Integer change,
			@RequestParam(required = false) Integer quantity) {
		try {
			if (change != null) {
				cartService.changeQuantity(cartItemId, change);
			} else if (quantity != null) {
				cartService.updateQuantity(cartItemId, quantity);
			}
		} catch (Exception e) {
			// 可以添加錯誤處理，比如添加一個錯誤消息到 Model
		}
		return "redirect:/cart/view";
	}

	@PostMapping("/remove/{cartItemId}")
	public String removeCartItem(@PathVariable Integer cartItemId) {
		try {
			cartService.removeFromCart(cartItemId);
		} catch (Exception e) {
			// 錯誤處理
		}
		return "redirect:/cart/view";
	}

	@GetMapping("/continue-shopping")
	public String continueShopping() {
		// 這裡可以重定向到商品列表頁面
		return "redirect:/commodity/mall_activity";
	}

	@GetMapping("/checkout")
	public String showCheckoutPage(Model model) {
		Integer memberID = 1; // 假設當前登錄的會員 ID
		Cart cart = cartService.getCartByMemberID(memberID);
		GeneralMember member = memberService.getOneGeneralMember(memberID);
		Orders order = new Orders();
		// 添加日誌
		System.out.println("Member info: " + member);

		// 檢查購物車是否為空
	    if (cart == null || cart.getCartItems().isEmpty()) {
	        model.addAttribute("errorMessage", "您的購物車是空的，請先添加商品。");
			return "redirect:/commodity/mall_activity";
	    }else
		model.addAttribute("cart", cart);
		model.addAttribute("member", member);
		model.addAttribute("order", order);
//        return "cartCheck";

		return "front-end/mall/mallCheckout"; 
	}
	
	
//	@PostMapping("/checkout")
//	public String processCheckout(@RequestParam String recipient,
//	                              @RequestParam String recipientPhone,
//	                              @RequestParam String recipientEmail,
//	                              @RequestParam String recipientAddress,
//	                              @RequestParam(required = false) Integer memberCouponId,
//	                              RedirectAttributes redirectAttributes) {
//	    Integer memberId = 1; // 假設當前登錄的會員 ID
//	    try {
//	        Orders order = cartService.processCheckout(memberId, recipient, recipientPhone, recipientEmail, recipientAddress, memberCouponId);
//	        
//	        // 使用 addAttribute 方法將 orderID 添加到 URL
//	        return "redirect:/orders/complete?orderID=" + order.getOrderID();
//	    } catch (Exception e) {
//	        redirectAttributes.addFlashAttribute("error", e.getMessage());
//	        return "redirect:/cart/checkout";
//	    }
//	}
	
	
	
	@PostMapping("/checkout")
	public String processCheckout(@RequestParam String recipient,
	                              @RequestParam String recipientPhone,
	                              @RequestParam String recipientEmail,
	                              @RequestParam String recipientAddress,
	                              @RequestParam(required = false) Integer memberCouponId,
	                              RedirectAttributes redirectAttributes) {
	    try {
	        Integer memberId = 1; // 假設當前登錄的會員 ID
	        Orders order = cartService.processCheckout(memberId, recipient, recipientPhone, recipientEmail, recipientAddress, memberCouponId);
//	        return "redirect:/orders/complete";
	        return "redirect:/orders/complete?orderID=" + order.getOrderID();
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("error", e.getMessage());
	        return "redirect:/cart/view";
	    }
	}
}

//	@PostMapping("/checkout")
//	public String processCheckout(@RequestParam String recipient, @RequestParam String recipientPhone,
//			@RequestParam String recipientEmail, @RequestParam String recipientAddress,
//			@RequestParam(required = false) Integer memberCouponId, RedirectAttributes redirectAttributes) {
//		Integer memberId = 1; // 假設當前登錄的會員 ID
//		try {
//			Orders order = cartService.processCheckout(memberId, recipient, recipientPhone, recipientEmail,
//					recipientAddress, memberCouponId);
//
//			// 將訂單ID添加到重定向屬性中
//			redirectAttributes.addAttribute("orderID", order.getOrderID());
//
//			return "redirect:/orders/complete";
//
//		} catch (Exception e) {
//            // 處理錯誤，將錯誤信息添加到重定向屬性中
//            redirectAttributes.addFlashAttribute("error", e.getMessage());
//            return "redirect:/cart/checkout";
//        }
//
//		// 處理結帳邏輯
//		// 1. 驗證表單數據
//		// 2. 創建訂單
//		// 3. 處理支付
//		// 4. 清空購物車
//		// 5. 重定向到訂單完成頁面
////        return "redirect:/order/complete";
//	}





































//package com.tia102g5.cart.controller;
//
//import com.tia102g5.cart.model.Cart;
//import com.tia102g5.cart.model.CartService;
//import com.tia102g5.cartitem.model.CartItem;
//import com.tia102g5.cartitem.model.CartItemService;
//import com.tia102g5.generalmember.model.GeneralMember;
//
//import org.springframework.ui.Model;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/cart")
//public class CartController {
//
//    @Autowired
//    private CartService cartService;
//
//    @Autowired
//    private CartItemService cartItemService;
//    
// // 暫時固定 memberID 為 1
//    private static final Integer member = 1;
////    GeneralMember member = TEMP_MEMBER_ID;
//
//    
//    @GetMapping("/cart")
//    public String viewCart(Model model) {
//        // 使用固定的 memberID
//        Cart cart = cartService.getCartByMemberID(member);
//        List<CartItem> cartItems = new ArrayList<>(cart.getCartItems());
//        BigDecimal totalPrice = cartItems.stream()
//                .map(item -> item.getCommodity().getCommodityPrice().multiply(BigDecimal.valueOf(item.getCheckedQuantity())))
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//        model.addAttribute("cartItems", cartItems);
//        model.addAttribute("totalPrice", totalPrice);
//        
//        
//        
//        Integer memberID = 1;
//        List<CartItem> cartItems = cartService.getCartByMemberID(memberID);
//        model.addAttribute("commodityList", commodities);
//        return "front-end/mall/mallCart";
//    }
//    
////    @GetMapping("/cart")
////    public String viewCart(Model model) {
////        // 這裡應該從 session 或其他地方獲取當前用戶的 ID
////    	
////    	
////        Integer currentUserId = getCurrentUserId();
////        Cart cart = cartService.getCartByMemberID(currentUserId);
////        List<CartItem> cartItems = new ArrayList<>(cart.getCartItems());
////        BigDecimal totalPrice = cartItems.stream()
////                .map(item -> item.getCommodity().getCommodityPrice().multiply(BigDecimal.valueOf(item.getCheckedQuantity())))
////                .reduce(BigDecimal.ZERO, BigDecimal::add);
////
////            model.addAttribute("cartItems", cartItems);
////            model.addAttribute("totalPrice", totalPrice);
////    		return "front-end/mall/mallCart";
////    }
//    
//    @PutMapping("/api/cart/item/{cartItemId}")
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> updateCartItem(@PathVariable Integer cartItemId, 
//                                                              @RequestParam(required = false) Integer change,
//                                                              @RequestParam(required = false) Integer quantity) {
//        try {
//            if (change != null) {
//                cartService.changeQuantity(cartItemId, change);
//            } else if (quantity != null) {
//                cartService.updateQuantity(cartItemId, quantity);
//            }
//            return ResponseEntity.ok(Map.of("success", true));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
//        }
//    }
//
//    @DeleteMapping("/api/cart/item/{cartItemId}")
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> removeCartItem(@PathVariable Integer cartItemId) {
//        try {
//            cartItemService.deleteCartItem(cartItemId);
//            return ResponseEntity.ok(Map.of("success", true));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
//        }
//    }
//
//    // 這個方法應該在實際應用中實現
////    private Integer getCurrentUserId() {
////        // 從 session 或 Spring Security 中獲取當前用戶 ID
////        return 1; // 這裡僅為示例
////    }
//    
//    
//    
//    
//    
//    
//    
//    
//
//    // 購物車相關操作
//    
////    @PostMapping("/add")
////    public ResponseEntity<?> addToCart(@RequestParam Integer commodityID,
////                                       @RequestParam Integer quantity) {
////        cartService.addToCart(TEMP_MEMBER_ID, commodityID, quantity);
////        return ResponseEntity.ok("商品已添加到購物車");
////    }
////
////    @GetMapping("/view")
////    public ResponseEntity<Cart> viewCart() {
////        Cart cart = cartService.getCartByMemberID(TEMP_MEMBER_ID);
////        return ResponseEntity.ok(cart);
////    }
//
////    @PostMapping("/add")
////    public ResponseEntity<?> addToCart(@RequestParam Integer memberID,
////                                       @RequestParam Integer commodityID,
////                                       @RequestParam Integer quantity) {
////        cartService.addToCart(memberID, commodityID, quantity);
////        return ResponseEntity.ok("商品已添加到購物車");
////    }
////
////    @GetMapping("/view/{memberID}")
////    public ResponseEntity<Cart> viewCart(@PathVariable Integer memberID) {
////        Cart cart = cartService.getCartByMemberID(memberID);
////        return ResponseEntity.ok(cart);
////    }
//    
//    
//
//    // 購物車項目相關操作
//
//    @GetMapping("/items/{cartID}")
//    public ResponseEntity<List<CartItem>> listCartItems(@PathVariable Integer cartID) {
//        List<CartItem> items = cartItemService.getCartItemsByCartID(cartID);
//        return ResponseEntity.ok(items);
//    }
//
//    @PutMapping("/item/{cartItemID}")
//    public ResponseEntity<?> updateCartItemQuantity(@PathVariable Integer cartItemID,
//                                                    @RequestParam Integer newQuantity) {
//        cartItemService.updateCartItemQuantity(cartItemID, newQuantity);
//        return ResponseEntity.ok("購物車項目數量已更新");
//    }
//
//    @DeleteMapping("/item/{cartItemID}")
//    public ResponseEntity<?> removeFromCart(@PathVariable Integer cartItemID) {
//        cartService.removeFromCart(cartItemID);
//        return ResponseEntity.ok("商品已從購物車移除");
//    }
//
//    // 可以根據需要添加更多的方法，例如清空購物車、批量更新等
//
////    @PostMapping("/clear/{memberID}")
////    public ResponseEntity<?> clearCart(@PathVariable Integer memberID) {
////        cartService.clearCart(memberID);
////        return ResponseEntity.ok("購物車已清空");
////    }
//
////    @PostMapping("/checkout/{memberID}")
////    public ResponseEntity<?> checkout(@PathVariable Integer memberID) {
////        // 實現結賬邏輯
////        cartService.checkout(memberID);
////        return ResponseEntity.ok("結賬成功");
////    }
//}