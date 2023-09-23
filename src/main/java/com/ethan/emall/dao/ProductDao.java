package com.ethan.emall.dao;

import com.ethan.emall.model.Product;

import java.util.List;

public interface ProductDao {

    Product getProductById(Integer id);

    List<Product> getAllProducts();
}
