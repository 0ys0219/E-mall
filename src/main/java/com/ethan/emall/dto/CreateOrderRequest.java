package com.ethan.emall.dto;

import javax.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.List;

public class CreateOrderRequest implements Serializable{

    private static final long serialVersionUID = 1L;
	@NotEmpty
    private List<BuyItem> buyItemList;

    public List<BuyItem> getBuyItemList() {
        return buyItemList;
    }

    public void setBuyItemList(List<BuyItem> buyItemList) {
        this.buyItemList = buyItemList;
    }
}
