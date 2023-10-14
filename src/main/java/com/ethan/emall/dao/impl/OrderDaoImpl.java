package com.ethan.emall.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ethan.emall.dao.OrderDao;
import com.ethan.emall.model.Order;
import com.ethan.emall.model.OrderDetail;
import com.ethan.emall.model.Product;
import com.ethan.emall.repository.OrderDetailRepository;
import com.ethan.emall.repository.OrderRepository;
import com.ethan.emall.repository.ProductRepository;

@Component
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Integer createOrder(Integer memberId, Integer totalAmount) {

		Order order = new Order();
		order.setMemberId(memberId);
		order.setPayStatus(false);
		order.setPrice(totalAmount);
		orderRepository.save(order);

		return order.getOrderId();
	}

	@Override
	public void createOrderItems(Integer orderId, List<OrderDetail> orderDetailList) {

		for (OrderDetail orderDetail : orderDetailList) {
			orderDetail.setOrderId(orderId);
		}
		orderDetailRepository.saveAll(orderDetailList);

	}

	@Override
	public Order getOrderById(Integer orderId) {

		Order order = orderRepository.findById(orderId).orElse(null);
		return order;
	}

	@Override
	public List<OrderDetail> getOrderDetailsByOrderId(Integer orderId) {

		List<OrderDetail> orderDetails = orderDetailRepository.getOrderDetailsByOrderId(orderId);
		for (OrderDetail orderDetail : orderDetails) {
			Product product = productRepository.findById(orderDetail.getProductId()).orElse(null);
			orderDetail.setProductName(product.getName());
		}
		return orderDetails;
	}

	@Override
	public List<Order> getOrdersByMember(Integer memberId) {

		List<Order> orderList = orderRepository.findByMemberIdOrderByOrderIdDesc(memberId);

		return orderList;
	}
}
