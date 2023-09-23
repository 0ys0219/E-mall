package com.ethan.emall.dto;


import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;

public class ProductRequest {

    @NotNull
    private String name;

    @NotNull
    private Integer price;

    @NonNull
    private Integer quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
