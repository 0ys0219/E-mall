package com.ethan.emall.service;

import com.ethan.emall.dto.ProductQueryParams;
import com.ethan.emall.dto.ProductRequest;
import com.ethan.emall.model.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Integer productId);

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Integer createProduct(ProductRequest productRequest);
}
