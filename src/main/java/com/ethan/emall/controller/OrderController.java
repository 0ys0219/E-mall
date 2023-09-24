package com.ethan.emall.controller;

import com.ethan.emall.dto.CreateOrderRequest;
import com.ethan.emall.model.Order;
import com.ethan.emall.model.OrderDetail;
import com.ethan.emall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:5173")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/members/{memberId}/orders")
    public ResponseEntity<?> createOrder(
            @PathVariable Integer memberId,
             @RequestBody @Valid CreateOrderRequest createOrderRequest) {
            Integer orderId = orderService.createOrder(memberId, createOrderRequest);

            Order order = orderService.getOrderById(orderId);

            return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

@GetMapping("/members/{memberId}/orders")
    public ResponseEntity<List<Order>> getOrdersByMember(
            @PathVariable Integer memberId) {
        List<Order> orderList = orderService.getOrdersByMember(memberId);

        return ResponseEntity.status(HttpStatus.OK).body(orderList);
    }
}
