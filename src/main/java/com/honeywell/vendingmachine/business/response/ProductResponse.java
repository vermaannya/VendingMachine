package com.honeywell.vendingmachine.business.response;

public final class ProductResponse {
    private String productName;

    public ProductResponse(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }
}
