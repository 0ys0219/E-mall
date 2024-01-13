package com.ethan.emall.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class BuyItem implements Serializable{

    private static final long serialVersionUID = 1L;

	@NotNull
    private Integer productId;

    @NotNull
    private Integer quantity;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
