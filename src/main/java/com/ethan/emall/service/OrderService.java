package com.ethan.emall.service;

import com.ethan.emall.dto.CreateOrderRequest;
import com.ethan.emall.model.Order;

public interface OrderService {

    Integer createOrder(Integer memberId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);
}
