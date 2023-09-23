package com.ethan.emall.rowmapper;

import com.ethan.emall.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("Id"));
        product.setName(resultSet.getString("Name"));
        product.setPrice(resultSet.getInt("Price"));
        product.setQuantity(resultSet.getInt("Quantity"));
        return product;
    }
}
