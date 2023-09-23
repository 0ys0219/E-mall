package com.ethan.emall.rowmapper;

import com.ethan.emall.model.OrderDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailRowMapper implements RowMapper<OrderDetail> {
    @Override
    public OrderDetail mapRow(ResultSet resultSet, int i) throws SQLException {
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setOrderItemSN(resultSet.getInt("OrderItemSN"));
        orderDetail.setOrderId(resultSet.getInt("OrderId"));
        orderDetail.setProductId(resultSet.getInt("ProductId"));
        orderDetail.setQuantity(resultSet.getInt("Quantity"));
        orderDetail.setStandPrice(resultSet.getInt("StandPrice"));
        orderDetail.setItemPrice(resultSet.getInt("ItemPrice"));

        orderDetail.setProductName(resultSet.getString("Name"));

        return orderDetail;
    }
}
