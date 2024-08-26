package com.example.productmicroservice.Modules;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private long id;
    private String productName;
    private String productDescription;
    private double productPrice;
    private Category category;
    private String image;
}
