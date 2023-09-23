package com.ethan.emall.service;

import com.ethan.emall.dto.CreateOrderRequest;

public interface OrderService {

    Integer createOrder(Integer memberId, CreateOrderRequest createOrderRequest);
}
