package com.maddog.articket.orders.dao;

import com.maddog.articket.orders.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 訂單 DAO
 */
@Mapper
public interface OrdersDao {

    /**
     * 新增
     *
     * @param orders
     *          訂單
     * @return 成功筆數
     */
    int insert(Orders orders);

    /**
     * 依訂單ID查詢
     *
     * @param orderId
     *          訂單ID
     * @return 訂單
     */
    Orders findById(Integer orderId);

    /**
     * 依會員ID查詢訂單清單
     *
     * @param memberId
     *          會員ID
     * @return 訂單清單
     */
    List<Orders> findByGeneralMemberId(Integer memberId);

}