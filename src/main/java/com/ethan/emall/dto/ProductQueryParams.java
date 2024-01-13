package com.ethan.emall.dto;

import java.io.Serializable;

public class ProductQueryParams implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer quantity;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
