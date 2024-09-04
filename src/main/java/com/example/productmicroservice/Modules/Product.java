package com.example.productmicroservice.Modules;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String productName;
    private String productDescription;
    private double productPrice;
    @ManyToOne
    private Category category;
    private String image;
}
