package com.niq.shoppers.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Data;

@Entity
@Data
public class Shopper {

    @Id
    @Column(name = "id")
    @NotEmpty
    private String shopperId;

    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Shelf> shelf;
}
