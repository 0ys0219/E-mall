package com.ethan.emall.dao;

import com.ethan.emall.dto.ProductQueryParams;
import com.ethan.emall.dto.ProductRequest;
import com.ethan.emall.model.Product;

import java.util.List;

public interface ProductDao {

    Product getProductById(Integer id);

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Integer createProduct(ProductRequest productRequest);

    void updateQuantity(Integer productId, Integer quantity);
}
