package com.honeywell.vendingmachine.data.dao;

import com.honeywell.vendingmachine.data.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersJpaDao extends JpaRepository<Orders, Long> {
}
