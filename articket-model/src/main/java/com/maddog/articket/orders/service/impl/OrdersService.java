package com.maddog.articket.orders.service.impl;

import com.maddog.articket.orders.dao.OrdersDao;
import com.maddog.articket.orders.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    public Orders getOrderById(Integer orderId) {
        return ordersDao.findById(orderId);
    }

    public List<Orders> getOrdersByMemberId(Integer memberId) {
        return ordersDao.findByGeneralMemberId(memberId);
    }

}