package com.ethan.emall.dao;

import com.ethan.emall.model.Order;
import com.ethan.emall.model.OrderDetail;

import java.util.List;

public interface OrderDao {

    Integer createOrder(Integer memberId, Integer totalAmount);

    Order getOrderById(Integer orderId);

    List<OrderDetail> getOrderDetailsByOrderId(Integer orderId);

    void createOrderItems(Integer orderId, List<OrderDetail> orderDetailList);
}
