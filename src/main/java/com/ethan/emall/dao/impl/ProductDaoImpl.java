package com.ethan.emall.dao.impl;

import com.ethan.emall.dao.ProductDao;
import com.ethan.emall.dto.ProductQueryParams;
import com.ethan.emall.dto.ProductRequest;
import com.ethan.emall.model.Product;
import com.ethan.emall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Product getProductById(Integer productId) {

        String sql = "select id, name, price, quantity from Product where id = :productId;";

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
        String sql = "select id, name, price, quantity from Product where 1=1";

        HashMap<String, Object> map = new HashMap<>();

        if (productQueryParams.getQuantity() != null) {
            sql = sql + " and quantity > :quantity ";
            map.put("quantity",productQueryParams.getQuantity());
        }

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        if (productList.size() > 0) {
            return productList;
        } else {
            return null;
        }
    }


    public Integer createProduct(ProductRequest productRequest) {
        String sql = "insert into Product(name, price, quantity)" +
                "values" +
                "(:name,:price,:quantity)";

        HashMap<String, Object> map = new HashMap<>();
        map.put("name",productRequest.getName());
        map.put("price",productRequest.getPrice());
        map.put("quantity",productRequest.getQuantity());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);

        return keyHolder.getKey().intValue();
    }
}
