package com.ethan.emall.service.impl;

import com.ethan.emall.dao.ProductDao;
import com.ethan.emall.dto.ProductQueryParams;
import com.ethan.emall.dto.ProductRequest;
import com.ethan.emall.model.Product;
import com.ethan.emall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        return productDao.getProducts(productQueryParams);
    }


    public Integer createProduct(ProductRequest productRequest){
        return productDao.createProduct(productRequest);
    }
}
