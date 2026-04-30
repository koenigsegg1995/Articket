package com.maddog.articket.orders.service.impl;

import com.maddog.articket.orders.dao.OrdersDao;
import com.maddog.articket.orders.entity.Orders;
import com.maddog.articket.orders.service.pri.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 訂單 Service Implementation
 */
@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {

    /**
     * 訂單 DAO
     */
    @Autowired
    private OrdersDao ordersDao;

    /**
     * 依訂單ID查詢
     *
     * @param orderId
     *          訂單ID
     * @return 訂單
     */
    @Override
    @Transactional(readOnly = true)
    public Orders getOrderById(Integer orderId) {
        return ordersDao.findById(orderId);
    }

    /**
     * 依會員ID查詢訂單清單
     *
     * @param memberId
     *          會員ID
     * @return 訂單清單
     */
    @Override
    @Transactional(readOnly = true)
    public List<Orders> getOrdersByMemberId(Integer memberId) {
        return ordersDao.findByGeneralMemberId(memberId);
    }

}