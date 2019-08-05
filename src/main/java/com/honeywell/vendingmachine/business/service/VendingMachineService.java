package com.honeywell.vendingmachine.business.service;

import com.honeywell.vendingmachine.business.request.OrderRequest;
import com.honeywell.vendingmachine.business.request.ProductRequest;
import com.honeywell.vendingmachine.business.response.*;

import java.util.List;

public interface VendingMachineService {
    ProductResponse createProduct(ProductRequest productRequest);

    DefaultSuccessResponse deleteProduct(String productName);

    List<ProductDetailsResponse> getProducts();

    InventoryDetailsResponse getInventoryDetails(String productName);

    OrderResponse createOrder(String productName, OrderRequest orderRequest);
}
