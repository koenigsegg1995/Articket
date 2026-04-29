package com.maddog.articket.orderitem.service.impl;

import com.maddog.articket.orderitem.dao.OrderItemDao;
import com.maddog.articket.orderitem.entity.OrderItem;
import com.maddog.articket.orderitem.service.pri.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 訂單明細 Service Implementation
 */
@Service("orderItemService")
public class OrderItemServiceImpl implements OrderItemService {

//    @Autowired
//    private OrderItemDao orderItemDao;
//
//    public List<OrderItem> getAllOrderItems() {
//        return orderItemDao.findAll();
//    }
//
//    public OrderItem getOrderItemById(Integer orderItemID) {
//        return orderItemDao.findById(orderItemID);
//    }
//
//    public List<OrderItem> getOrderItemsByOrderID(Integer orderID) {
//        return orderItemDao.findByOrders_OrderID(orderID);
//    }
//
//    @Transactional
//    public OrderItem createOrderItem(OrderItem orderItem) {
//        return orderItemDao.save(orderItem);
//    }
//
//    @Transactional
//    public OrderItem updateOrderItem(OrderItem orderItem) {
//        return orderItemDao.save(orderItem);
//    }
//
//    @Transactional
//    public void deleteOrderItem(Integer orderItemID) {
//        orderItemDao.deleteByOrderItemID(orderItemID);
//    }

}