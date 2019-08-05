package com.honeywell.vendingmachine.data.dao;

import com.honeywell.vendingmachine.data.entity.Inventory;
import com.honeywell.vendingmachine.data.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface InventoryJpaDao extends JpaRepository<Inventory, Long> {

    @Modifying
    @Query(value = "UPDATE Inventory inventory SET inventory.stock = ?1 where inventory.products = ?2")
    void updateStock(Integer stock, Products products);
}
