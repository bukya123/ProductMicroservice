package com.example.productmicroservice.Services;

import com.example.productmicroservice.DTOs.FakeStoreDto;
import com.example.productmicroservice.Modules.Product;

import java.util.List;

public interface CategoryService {
     public List<String> getAllCategories();
     public List<Product> getCategoryByType(String categoryType);
}
