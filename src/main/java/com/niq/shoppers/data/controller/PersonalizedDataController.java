package com.niq.shoppers.data.controller;

import com.niq.shoppers.data.entity.Product;
import com.niq.shoppers.data.entity.Shopper;
import com.niq.shoppers.data.service.PersonalizedDataService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1/internal")
public class PersonalizedDataController {

    @Autowired
    private PersonalizedDataService personalizedDataService;

    @PostMapping("/products")
    public ResponseEntity<?> addProducts(
            @RequestBody @NotEmpty(message = "Input product list cannot be empty") List<@Valid Product> products) {
        personalizedDataService.addProducts(products);
        return new ResponseEntity<>("Products are saved successfully", HttpStatus.OK);
    }

    @PostMapping("/shoppers")
    public ResponseEntity<?> addShoppers(
            @RequestBody @NotEmpty(message = "Input shopper list cannot be empty") List<@Valid Shopper> shoppers) {
        personalizedDataService.addShoppers(shoppers);
        return new ResponseEntity<>("Shoppers are saved successfully", HttpStatus.OK);
    }
}
