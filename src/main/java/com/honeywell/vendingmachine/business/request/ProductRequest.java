package com.honeywell.vendingmachine.business.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public final class ProductRequest {
    @NotNull
    @NotEmpty
    private String name;
    private String description;
    @NotNull
    private Double price;
    @NotNull
    private Integer quantity;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
