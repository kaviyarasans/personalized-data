package com.niq.shoppers.data.service;

import com.niq.shoppers.data.entity.Product;
import com.niq.shoppers.data.entity.Shelf;
import com.niq.shoppers.data.entity.Shopper;
import com.niq.shoppers.data.exception.ResourceNotFoundException;
import com.niq.shoppers.data.repository.ProductRepository;
import com.niq.shoppers.data.repository.ShopperRepository;
import com.niq.shoppers.data.repository.specification.ProductSpecifications;
import com.niq.shoppers.data.repository.specification.filter.Filter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ECommerceService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShopperRepository shopperRepository;

    public List<Product> getShopperShelfProducts(String shopperId, String category, String brand, int limit) {
        Optional<Shopper> shopperOptional = shopperRepository.findById(shopperId);
        List<String> productIdList = shopperOptional
                .map(shopper ->
                        shopper.getShelf().stream().map(Shelf::getProductId).collect(Collectors.toList()))
                .orElseGet(ArrayList::new);
        if (productIdList.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("Products are not found for the ShopperId(%s)", shopperId));
        }
        List<Filter> productFilter = createProductFilter(productIdList, category, brand);
        Specification<Product> productSpecification = ProductSpecifications.getSpecificationFromFilters(productFilter);
        return productRepository
                .findAll(productSpecification, PageRequest.of(0, limit))
                .getContent();
    }

    private static List<Filter> createProductFilter(List<String> productIdList, String category, String brand) {
        List<Filter> filterList = new ArrayList<>();
        filterList.add(ProductSpecifications.createInOperatorFilter("id", productIdList));
        if (category != null && !category.isBlank()) {
            filterList.add(ProductSpecifications.createEqualsOperatorFilter("category", category));
        }
        if (brand != null && !brand.isBlank()) {
            filterList.add(ProductSpecifications.createEqualsOperatorFilter("brand", brand));
        }
        return filterList;
    }
}
