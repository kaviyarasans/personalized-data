package com.niq.shoppers.data.service;

import com.niq.shoppers.data.entity.Product;
import com.niq.shoppers.data.entity.Shopper;
import com.niq.shoppers.data.repository.ProductRepository;
import com.niq.shoppers.data.repository.ShopperRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalizedDataService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShopperRepository shopperRepository;

    public void addProducts(List<Product> products) {
        productRepository.saveAll(products);
    }

    public void addShoppers(List<Shopper> shoppers) {
        shopperRepository.saveAll(shoppers);
    }
}
