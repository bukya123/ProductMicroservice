package com.example.productmicroservice.Modules;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category  extends BaseModel{
    @OneToMany
    private List<Product> products;
    private String categoryName;
}
