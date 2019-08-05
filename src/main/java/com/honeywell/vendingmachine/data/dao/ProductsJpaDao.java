package com.honeywell.vendingmachine.data.dao;

import com.honeywell.vendingmachine.data.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductsJpaDao extends JpaRepository<Products, Long> {

    @Modifying
    @Query(value = "DELETE FROM Products products where products.name = ?1")
    void deleteByName(String productName);

    @Query(value = "FROM Products products where products.name = ?1")
    Optional<Products> findByName(String productName);

}
