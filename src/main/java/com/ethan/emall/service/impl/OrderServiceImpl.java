package com.ethan.emall.service.impl;

import com.ethan.emall.dao.OrderDao;
import com.ethan.emall.dao.ProductDao;
import com.ethan.emall.dto.BuyItem;
import com.ethan.emall.dto.CreateOrderRequest;
import com.ethan.emall.model.Order;
import com.ethan.emall.model.OrderDetail;
import com.ethan.emall.model.Product;
import com.ethan.emall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;




    @Transactional
    @Override
    public Integer createOrder(Integer memberId, CreateOrderRequest createOrderRequest) {
        int totalAmount = 0;
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());

            //計算總花費
            int amount = product.getPrice() * buyItem.getQuantity();
            totalAmount += amount;

            // 放入OrderDetail
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProductId(buyItem.getProductId());
            orderDetail.setQuantity(buyItem.getQuantity());
            orderDetail.setStandPrice(product.getPrice());
            orderDetail.setItemPrice(amount);

            orderDetailList.add(orderDetail);
        }
        //創建訂單
        Integer orderId = orderDao.createOrder(memberId,totalAmount);

        orderDao.createOrderItems(orderId, orderDetailList);

        return orderId;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);

        List<OrderDetail> orderDetailList = orderDao.getOrderDetailsByOrderId(orderId);

        order.setOrderDetailList(orderDetailList);

        return order;
    }
}
