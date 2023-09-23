package com.ethan.emall.controller;

import com.ethan.emall.dto.CreateOrderRequest;
import com.ethan.emall.model.Order;
import com.ethan.emall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/members/{memberId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer memberId,
                                         @RequestBody @Valid CreateOrderRequest createOrderRequest) {
            Integer orderId = orderService.createOrder(memberId, createOrderRequest);

            Order order = orderService.getOrderById(orderId);

            return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
