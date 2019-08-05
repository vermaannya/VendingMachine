package com.honeywell.vendingmachine.data.entity;

import com.honeywell.vendingmachine.business.request.ProductRequest;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Products {
    @NotNull
    @Column(unique = true)
    private String name;
    private String description;
    @NotNull
    private Double price;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "products")
    private Inventory inventory;

    public static Products buildProduct(ProductRequest productRequest) {
        Products products = new Products();
        products.setName(productRequest.getName().toLowerCase());
        products.setDescription(productRequest.getDescription());
        products.setPrice(productRequest.getPrice());
        return products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
