package com.maddog.articket.orders.dao;

import com.maddog.articket.orders.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface OrdersDao {

    Orders findById(Integer orderId);

    //查詢訂單
    List<Orders> findByGeneralMemberId(Integer memberId);

}