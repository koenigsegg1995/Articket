package com.maddog.articket.orders.service.impl;

import com.maddog.articket.orderitem.entity.OrderItem;
import com.maddog.articket.orderitem.dao.OrderItemRepository;
import com.maddog.articket.orders.dao.OrdersRepository;
import com.maddog.articket.orders.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

//    public Optional<Orders> getOrderById(Integer orderID) {
//        return ordersRepository.findById(orderID);
//    }
    public Orders getOrderById(Integer orderID) {
        return ordersRepository.findById(orderID)
            .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderID));
    }

//    public List<Orders> getOrdersByMemberID(Integer memberID) {
//        return ordersRepository.findByGeneralMember_MemberID(memberID);
//    }
    
    public List<Orders> getOrdersByMemberID(Integer memberID) {
        return ordersRepository.findByGeneralMemberMemberID(memberID);
    }


    public List<Orders> getOrdersByStatus(Integer status) {
        return ordersRepository.findByOrderStatus(status);
    }

    @Transactional
    public Orders createOrder(Orders order) {
        Orders savedOrder = ordersRepository.save(order);
        if (order.getOrderItems() != null) {
            for (OrderItem item : order.getOrderItems()) {
                item.setOrders(savedOrder);
                orderItemRepository.save(item);
            }
        }
        return savedOrder;
    }

    @Transactional
    public Orders updateOrder(Orders order) {
        return ordersRepository.save(order);
    }

    @Transactional
    public void deleteOrder(Integer orderID) {
        ordersRepository.deleteByOrderID(orderID);
    }

    public List<Orders> getOrdersByMemberAndStatus(Integer memberID, Integer status) {
        return ordersRepository.findByMemberIDAndStatus(memberID, status);
    }
    
   
        }