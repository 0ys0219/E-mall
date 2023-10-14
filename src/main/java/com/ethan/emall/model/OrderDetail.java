package com.ethan.emall.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "orderdetail")
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderitemsn")
    private Integer orderItemSN;
	
	@Column(name = "orderid")
    private Integer orderId;
	
	@Column(name = "productid")
    private Integer productId;
	
	@Column
    private Integer quantity;
	
	@Column(name = "standprice")
    private Integer standPrice;
	
	@Column(name = "itemprice")
    private Integer itemPrice;

	@Transient
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
