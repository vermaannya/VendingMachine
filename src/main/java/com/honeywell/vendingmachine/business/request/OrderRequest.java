package com.honeywell.vendingmachine.business.request;

import javax.validation.constraints.NotNull;

public final class OrderRequest {
    @NotNull
    private Integer quantity;
    @NotNull
    private Double amount;

    public Integer getQuantity() {
        return quantity;
    }

    public Double getAmount() {
        return amount;
    }
}
