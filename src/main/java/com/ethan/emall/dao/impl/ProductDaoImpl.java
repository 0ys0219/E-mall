package com.ethan.emall.dao.impl;

import com.ethan.emall.dao.ProductDao;
import com.ethan.emall.dto.ProductQueryParams;
import com.ethan.emall.dto.ProductRequest;
import com.ethan.emall.model.Product;
import com.ethan.emall.repository.ProductRepository;
import com.ethan.emall.rowmapper.ProductRowMapper;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

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
	private ProductRepository productRepository;

	@Override
	public Product getProductById(Integer productId) {

		Product product = productRepository.findById(productId).orElse(null);
		return product;
	}

	public List<Product> getProducts(ProductQueryParams productQueryParams) {

		List<Product> productList = (List<Product>) productRepository.findAll();
		return productList;

	}

	public Integer createProduct(ProductRequest productRequest) {

		Product product = new Product();
		product.setName(productRequest.getName());
		product.setPrice(productRequest.getPrice());
		product.setQuantity(productRequest.getQuantity());
		productRepository.save(product);
		return product.getId();

	}

	@Override
	public void updateQuantity(Integer productId, Integer quantity) {

		Product product = productRepository.findById(productId).orElse(null);

		product.setQuantity(quantity);

		productRepository.save(product);

	}
}
