package com.ethan.emall.model;

public class OrderDetail {

    private Integer orderItemSN;
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private Integer standPrice;
    private Integer itemPrice;

    private String productName;

    public Integer getOrderItemSN() {
        return orderItemSN;
    }

    public void setOrderItemSN(Integer orderItemSN) {
        this.orderItemSN = orderItemSN;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

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

    public Integer getStandPrice() {
        return standPrice;
    }

    public void setStandPrice(Integer standPrice) {
        this.standPrice = standPrice;
    }

    public Integer getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Integer itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
