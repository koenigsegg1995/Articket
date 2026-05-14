package com.maddog.articket.controller.cart;

import com.maddog.articket.cart.entity.Cart;
import com.maddog.articket.cart.service.pri.CartService;
import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.cartitem.service.impl.CartItemService;
import com.maddog.articket.generalmember.service.pri.GeneralMemberService;
import com.maddog.articket.membercoupon.service.pri.MemberCouponService;
import com.maddog.articket.orders.entity.Orders;
import com.maddog.articket.orders.service.pri.OrdersService;
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
		Cart cart = cartService.getCartByMemberId(generalMember);
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
		Cart cart = cartService.getCartByMemberId(memberID);
		GeneralMember member = memberService.getById(memberID);
		Orders order = new Orders();
		// 添加日誌
		System.out.println("Member info: " + member);

		// 檢查購物車是否為空
	    if (cart == null || cartItemService.getCartItemsByCartID(cart.getCartId()).isEmpty()) {
	        model.addAttribute("errorMessage", "您的購物車是空的，請先添加商品。");
			return "redirect:/commodity/mall_activity";
	    }

		model.addAttribute("cart", cart);
		model.addAttribute("member", member);
		model.addAttribute("order", order);

		return "front-end/mall/mallCheckout"; 
	}

	@PostMapping("/checkout")
	public String processCheckout(@RequestParam String recipient,
	                              @RequestParam String recipientPhone,
	                              @RequestParam String recipientEmail,
	                              @RequestParam String recipientAddress,
	                              RedirectAttributes redirectAttributes) {
	    try {
	        Integer memberId = 1; // 假設當前登錄的會員 ID
	        Orders order = cartService.processCheckout(memberId, recipient, recipientPhone, recipientEmail, recipientAddress);
	        return "redirect:/orders/complete?orderID=" + order.getOrderId();
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("error", e.getMessage());
	        return "redirect:/cart/view";
	    }
	}
}