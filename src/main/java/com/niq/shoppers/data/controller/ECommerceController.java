package com.niq.shoppers.data.controller;

import com.niq.shoppers.data.entity.Product;
import com.niq.shoppers.data.service.ECommerceService;
import jakarta.validation.constraints.Max;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/v1/external")
public class ECommerceController {

    @Autowired
    private ECommerceService eCommerceService;

    @GetMapping("/shopper/{shopperId}/products")
    public ResponseEntity<List<Product>> getShopperShelfProducts(
            @PathVariable String shopperId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false, defaultValue = "10") @Max(100) int limit) {
        List<Product> productList = eCommerceService.getShopperShelfProducts(shopperId, category, brand, limit);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
}
