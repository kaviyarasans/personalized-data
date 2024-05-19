package com.niq.shoppers.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Embeddable
@Data
public class Shelf {

    @Column(name = "productId")
    private String productId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false, name = "productId", referencedColumnName = "id")
    private Product product;

    private double relevancyScore;
}
