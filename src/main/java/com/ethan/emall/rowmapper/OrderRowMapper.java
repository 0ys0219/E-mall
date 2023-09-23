package com.ethan.emall.rowmapper;

import com.ethan.emall.model.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        Order order = new Order();

        order.setOrderId(resultSet.getInt("OrderId"));
        order.setMemberId(resultSet.getInt("MemberId"));
        order.setPrice(resultSet.getInt("Price"));
        order.setPayStatus(resultSet.getBoolean("PayStatus"));

        return order;
    }
}
