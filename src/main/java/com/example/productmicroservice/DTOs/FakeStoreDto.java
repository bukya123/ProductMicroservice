package com.example.productmicroservice.DTOs;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreDto {
    private long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;

}
