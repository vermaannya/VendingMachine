package com.honeywell.vendingmachine.data.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Integer stock;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Products products;

    public static Inventory buildInventory(Integer stock) {
        Inventory inventory = new Inventory();
        inventory.setStock(stock);
        return inventory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

}
