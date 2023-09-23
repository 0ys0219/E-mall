package com.ethan.emall.dao.impl;

import com.ethan.emall.dao.OrderDao;
import com.ethan.emall.model.Order;
import com.ethan.emall.model.OrderDetail;
import com.ethan.emall.rowmapper.OrderDetailRowMapper;
import com.ethan.emall.rowmapper.OrderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createOrder(Integer memberId, Integer totalAmount) {
       String sql = "call createOrder(:memberId, :price)";

        HashMap<String, Object> map = new HashMap<>();
        map.put("memberId",memberId);
        map.put("price",totalAmount);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);

        int orderId = keyHolder.getKey().intValue();

        return orderId;
    }


    @Override
    public void createOrderItems(Integer orderId, List<OrderDetail> orderDetailList) {
        String sql = "call createOrderItems(:orderId, :productId, :quantity, :standPrice, :itemPrice)";

        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[orderDetailList.size()];

        for (int i = 0; i < orderDetailList.size(); i++) {
            OrderDetail orderDetail = orderDetailList.get(i);

            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("orderId",orderId);
            parameterSources[i].addValue("productId",orderDetail.getProductId());
            parameterSources[i].addValue("quantity",orderDetail.getQuantity());
            parameterSources[i].addValue("standPrice",orderDetail.getStandPrice());
            parameterSources[i].addValue("itemPrice",orderDetail.getItemPrice());

        }

        namedParameterJdbcTemplate.batchUpdate(sql,parameterSources);
    }

    @Override
    public Order getOrderById(Integer orderId) {
        String sql = "call getOrderById(:orderId)";

        HashMap<String, Object> map = new HashMap<>();
        map.put("orderId",orderId);

        List<Order> orderList = namedParameterJdbcTemplate.query(sql,map,new OrderRowMapper());

        if (orderList.size() > 0) {
            return orderList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrderId(Integer orderId) {
        String sql = "call getOrderDetailsByOrderId(:orderId)";

        HashMap<String, Object> map = new HashMap<>();
        map.put("orderId",orderId);

        List<OrderDetail> orderDetailList = namedParameterJdbcTemplate.query(sql,map,new OrderDetailRowMapper());

        return orderDetailList;
    }

    @Override
    public List<Order> getOrdersByMember(Integer memberId) {
        String sql ="call getOrdersByMember(:memberId)";

        HashMap<String, Object> map = new HashMap<>();
        map.put("memberId",memberId);

        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());

        return orderList;
    }
}
