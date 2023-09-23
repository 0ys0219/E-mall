package com.ethan.emall.service;

import com.ethan.emall.dto.CreateOrderRequest;
import com.ethan.emall.model.Order;
import com.ethan.emall.model.OrderDetail;

import java.util.List;

public interface OrderService {

    Integer createOrder(Integer memberId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);

    List<Order> getOrdersByMember(Integer memberId);
}
