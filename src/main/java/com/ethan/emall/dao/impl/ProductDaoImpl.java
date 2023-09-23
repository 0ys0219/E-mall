package com.ethan.emall.dao.impl;

import com.ethan.emall.dao.ProductDao;
import com.ethan.emall.dto.ProductQueryParams;
import com.ethan.emall.dto.ProductRequest;
import com.ethan.emall.model.Product;
import com.ethan.emall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Product getProductById(Integer productId) {

        String sql = "call getProductById(:productId) ";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        if (productList.size() > 0) {
            return productList.get(0);
        } else {
            return null;
        }
    }


    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        String sql = "call getProducts() ";

        HashMap<String, Object> map = new HashMap<>();


        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        if (productList.size() > 0) {
            return productList;
        } else {
            return null;
        }
    }


    public Integer createProduct(ProductRequest productRequest) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);

        HashMap<String, Object> mapIn = new HashMap<>();
        mapIn.put("in_name", productRequest.getName());
        mapIn.put("in_price", productRequest.getPrice());
        mapIn.put("in_quantity", productRequest.getQuantity());

        SqlParameterSource in = new MapSqlParameterSource(mapIn);
        Map<String, Object> mapOut = simpleJdbcCall.withProcedureName("createProduct").execute(in);

        int productId = Integer.parseInt(String.valueOf(mapOut.get("out_productId")));

        return productId;
    }

    @Override
    public void updateQuantity(Integer productId, Integer quantity) {
        String sql = "call updateQuantity(:productId, :quantity)";

        HashMap<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        map.put("quantity", quantity);

        namedParameterJdbcTemplate.update(sql, map);
    }
}
