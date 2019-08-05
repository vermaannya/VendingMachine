package com.honeywell.vendingmachine.business.response;

public final class OrderResponse {
    private String name;
    private String change;
    private Integer quantity;

    public OrderResponse(String name, String change, Integer quantity) {
        this.name = name;
        this.change = change;
        this.quantity = quantity;
    }

    public String getChange() {
        return change;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
