package com.ethan.emall.service.impl;

import com.ethan.emall.dao.MemberDao;
import com.ethan.emall.dao.OrderDao;
import com.ethan.emall.dao.ProductDao;
import com.ethan.emall.dto.BuyItem;
import com.ethan.emall.dto.CreateOrderRequest;
import com.ethan.emall.model.Member;
import com.ethan.emall.model.Order;
import com.ethan.emall.model.OrderDetail;
import com.ethan.emall.model.Product;
import com.ethan.emall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private MemberDao memberDao;


    @Transactional
    @Override
    public Integer createOrder(Integer memberId, CreateOrderRequest createOrderRequest) {

        Member member = memberDao.getMemberById(memberId);

        if (member == null) {
            log.warn("此帳號 {} 不存在",memberId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        int totalAmount = 0;
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());

            //檢查庫存
            if (product == null) {
                log.warn("該產品 {} 不存在",buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else if (product.getQuantity() < buyItem.getQuantity()) {
                log.warn("該產品 {} 的庫存量不足，剩餘 {} ，想購買數量為 {}",
                        buyItem.getProductId(),product.getQuantity(),
                        buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            productDao.updateQuantity(product.getId(), product.getQuantity() - buyItem.getQuantity());

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
