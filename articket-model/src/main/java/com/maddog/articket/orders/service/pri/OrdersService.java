package com.maddog.articket.orders.service.pri;

import com.maddog.articket.orders.entity.Orders;

import java.util.List;

/**
 * 訂單 Service Interface
 */
public interface OrdersService {

    /**
     * 依訂單ID查詢
     *
     * @param orderId
     *          訂單ID
     * @return 訂單
     */
    Orders getOrderById(Integer orderId);

    /**
     * 依會員ID查詢訂單清單
     *
     * @param memberId
     *          會員ID
     * @return 訂單清單
     */
    List<Orders> getOrdersByMemberId(Integer memberId);

}