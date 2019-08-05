package com.honeywell.vendingmachine.business.response;

import com.honeywell.vendingmachine.data.entity.Products;

public final class ProductDetailsResponse {
    private String name;
    private String description;
    private Double price;

    public ProductDetailsResponse(Products products) {
        this.name = products.getName();
        this.description = products.getDescription();
        this.price = products.getPrice();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }
}
