package com.example.productmicroservice.Controllers;


import com.example.productmicroservice.DTOs.FakeStoreDto;
import com.example.productmicroservice.Modules.Product;
import com.example.productmicroservice.Services.CategoryService;
import com.example.productmicroservice.Services.FakeStoreCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping("/products/categories")
    @GetMapping()
    public List<String> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @RequestMapping("/products/category")
    @GetMapping("/{categoryType}")
    public List<Product> getCategoryById(@PathVariable("categoryType") String categoryType) {

        return categoryService.getCategoryByType(categoryType);
    }
}
