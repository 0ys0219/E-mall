package com.ethan.emall.repository;

import org.springframework.data.repository.CrudRepository;

import com.ethan.emall.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
