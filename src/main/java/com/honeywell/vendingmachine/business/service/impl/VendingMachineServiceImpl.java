package com.honeywell.vendingmachine.business.service.impl;

import com.honeywell.vendingmachine.business.request.OrderRequest;
import com.honeywell.vendingmachine.business.request.ProductRequest;
import com.honeywell.vendingmachine.business.response.*;
import com.honeywell.vendingmachine.business.service.VendingMachineService;
import com.honeywell.vendingmachine.data.dao.InventoryJpaDao;
import com.honeywell.vendingmachine.data.dao.OrdersJpaDao;
import com.honeywell.vendingmachine.data.dao.ProductsJpaDao;
import com.honeywell.vendingmachine.data.entity.Inventory;
import com.honeywell.vendingmachine.data.entity.Orders;
import com.honeywell.vendingmachine.data.entity.Products;
import com.honeywell.vendingmachine.exception.ProductNotFoundException;
import com.honeywell.vendingmachine.exception.ProductOutOfStockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VendingMachineServiceImpl implements VendingMachineService {
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private final ProductsJpaDao productsJpaDao;
    private final InventoryJpaDao inventoryJpaDao;
    private final OrdersJpaDao ordersJpaDao;

    @Autowired
    public VendingMachineServiceImpl(ProductsJpaDao productsJpaDao, InventoryJpaDao inventoryJpaDao, OrdersJpaDao ordersJpaDao) {
        this.productsJpaDao = productsJpaDao;
        this.inventoryJpaDao = inventoryJpaDao;
        this.ordersJpaDao = ordersJpaDao;
        decimalFormat.setRoundingMode(RoundingMode.UP);
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Products products = Products.buildProduct(productRequest);
        Inventory inventory = Inventory.buildInventory(productRequest.getQuantity());
        inventory.setProducts(products);
        products = productsJpaDao.save(products);
        inventoryJpaDao.save(inventory);
        return new ProductResponse(products.getName());
    }

    @Override
    public DefaultSuccessResponse deleteProduct(String productName) {
        productsJpaDao.deleteByName(productName);
        return new DefaultSuccessResponse("product successfully deleted");
    }

    @Override
    public List<ProductDetailsResponse> getProducts() {
        return productsJpaDao.findAll().stream().map(ProductDetailsResponse::new).collect(Collectors.toList());
    }

    @Override
    public InventoryDetailsResponse getInventoryDetails(String productName) {
        Products products = productsJpaDao.findByName(productName).orElse(new Products());
        return new InventoryDetailsResponse(products);
    }

    @Override
    public OrderResponse createOrder(String productName, OrderRequest orderRequest) {
        Products products = productsJpaDao.findByName(productName).orElseThrow(() ->
                new ProductNotFoundException("product not present"));
        Integer stockQuantity = products.getInventory().getStock();
        if (orderRequest.getQuantity() > stockQuantity)
            throw new ProductOutOfStockException("out of stock");
        int actualDeliveredQuantity = (int) (orderRequest.getAmount() / products.getPrice());
        inventoryJpaDao.updateStock(stockQuantity - actualDeliveredQuantity, products);
        double actualAmount = actualDeliveredQuantity * products.getPrice();
        String change = decimalFormat.format(orderRequest.getAmount() - actualAmount);
        createOrder(actualDeliveredQuantity, actualAmount, products);
        return new OrderResponse(products.getName(), change, actualDeliveredQuantity);
    }

    private void createOrder(Integer orderQuantity, Double orderAmount, Products products) {
        Orders orders = new Orders();
        orders.setOrderAmount(orderAmount);
        orders.setOrderQuantity(orderQuantity);
        orders.setProducts(products);
        ordersJpaDao.save(orders);
    }
}
