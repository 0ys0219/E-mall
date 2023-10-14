package com.ethan.emall.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ethan.emall.model.Order;


public interface OrderRepository extends CrudRepository<Order, Integer>{

	List<Order> findByMemberIdOrderByOrderIdDesc(Integer memberId);
	
	
}
