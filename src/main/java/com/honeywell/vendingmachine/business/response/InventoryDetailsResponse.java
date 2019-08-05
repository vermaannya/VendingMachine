package com.honeywell.vendingmachine.business.response;

import com.honeywell.vendingmachine.data.entity.Inventory;
import com.honeywell.vendingmachine.data.entity.Products;

public final class InventoryDetailsResponse {
    private String name;
    private Integer quantity;

    public InventoryDetailsResponse(Products products) {
        Inventory inventory = products.getInventory();
        this.name = products.getName();
        this.quantity = inventory != null ? inventory.getStock() : null;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
