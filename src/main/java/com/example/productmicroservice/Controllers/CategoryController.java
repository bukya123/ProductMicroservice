package com.example.productmicroservice.Controllers;


import com.example.productmicroservice.DTOs.FakeStoreDto;
import com.example.productmicroservice.Services.FakeStoreCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    private FakeStoreCategoryService fakeStoreCategoryService;
    public CategoryController(FakeStoreCategoryService fakeStoreCategoryService) {
        this.fakeStoreCategoryService = fakeStoreCategoryService;
    }

    @RequestMapping("/products/categories")
    @GetMapping()
    public List<String> getAllCategories() {
        return fakeStoreCategoryService.getAllCategories();
    }

    @RequestMapping("/products/category")
    @GetMapping("/{categoryType}")
    public List<FakeStoreDto> getCategoryById(@PathVariable("categoryType") String categoryType) {

        return fakeStoreCategoryService.getCategoryByType(categoryType);
    }
}
