package com.ethan.emall.dao;

import com.ethan.emall.model.OrderDetail;

import java.util.List;

public interface OrderDao {

    Integer createOrder(Integer memberId, Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderDetail> orderDetailList);
}
