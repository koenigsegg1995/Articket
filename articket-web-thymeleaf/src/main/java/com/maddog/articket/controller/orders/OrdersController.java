package com.maddog.articket.controller.orders;

import com.maddog.articket.orderitem.service.pri.OrderItemService;
import com.maddog.articket.orders.entity.Orders;
import com.maddog.articket.orders.service.impl.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 訂單 Controller
 */
@Controller
@RequestMapping("/orders")
@Validated
public class OrdersController {

    /**
     * 訂單 Service
     */
    @Autowired
    private OrdersService ordersService;

//    @Autowired
//    private OrderItemService orderItemService;

    /**
     * 完成訂單
     *
     * @param orderId
     *          訂單 ID
     * @param model
     *          Model
     * @return mallComplete.html
     */
    @GetMapping("/complete")
    public String showOrderComplete(@RequestParam(required = false) Integer orderId, Model model) {
        if (orderId == null) {
            return "redirect:error";
        }

        Orders order = ordersService.getOrderById(orderId);

        if (order == null) {
            return "redirect:/error";
        }

        model.addAttribute("order", order);

        return "front-end/mall/mallComplete";
    }

//    @GetMapping("/view")
//	public String viewOrder(@RequestParam Integer orderID, Model model) {
//        Orders order = ordersService.getOrderById(orderID);
//		model.addAttribute("order", order);
//		return "/front-end/mall/mallCart";
//	}

    @GetMapping("/test")
    public String viewCart() {
        return "front-end/mall/mallComplete";
    }

}
