package com.niq.shoppers.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
public class Product {

    @Id
    @Column(name = "id")
    @NotEmpty
    private String productId;

    private String name;

    private String category;

    private String brand;
}
