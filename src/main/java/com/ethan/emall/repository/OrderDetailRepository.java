package com.ethan.emall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ethan.emall.model.OrderDetail;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer>{
	
	@Query(value = "call getOrderDetailsByOrderId(:orderId)", nativeQuery = true)
	List<OrderDetail> getOrderDetailsByOrderId(@Param("orderId") Integer orderId);
	
	
}
