package com.honeywell.vendingmachine.api;

import com.honeywell.vendingmachine.business.request.OrderRequest;
import com.honeywell.vendingmachine.business.request.ProductRequest;
import com.honeywell.vendingmachine.business.service.VendingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class VendingMachineController {
    private final VendingMachineService vendingMachineService;

    @Autowired
    public VendingMachineController(VendingMachineService vendingMachineService) {
        this.vendingMachineService = vendingMachineService;
    }

    @PostMapping(value = "/products")
    public ResponseEntity<?> createProduct(@RequestBody @NotNull @Valid final ProductRequest productRequest) {
        return new ResponseEntity<>(vendingMachineService.createProduct(productRequest), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/products/{productName}")
    public ResponseEntity<?> deleteProduct(@PathVariable @NotNull final String productName) {
        return new ResponseEntity<>(vendingMachineService.deleteProduct(productName), HttpStatus.OK);
    }

    @GetMapping(value = "/products")
    public ResponseEntity<?> getAllProducts() {
        return new ResponseEntity<>(vendingMachineService.getProducts(), HttpStatus.OK);
    }

    @GetMapping(value = "/products/{productName}/inventory")
    public ResponseEntity<?> getInventoryDetails(@PathVariable @NotNull final String productName) {
        return new ResponseEntity<>(vendingMachineService.getInventoryDetails(productName), HttpStatus.OK);
    }

    @PostMapping(value = "/products/{productName}/orders")
    public ResponseEntity<?> createOrder(@PathVariable @NotNull final String productName, @RequestBody @NotNull @Valid final OrderRequest orderRequest) {
        return new ResponseEntity<>(vendingMachineService.createOrder(productName, orderRequest), HttpStatus.OK);
    }

}
