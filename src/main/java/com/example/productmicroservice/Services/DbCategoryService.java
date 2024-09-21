package com.example.productmicroservice.Services;

import com.example.productmicroservice.DTOs.FakeStoreDto;
import com.example.productmicroservice.Modules.Category;
import com.example.productmicroservice.Modules.Product;
import com.example.productmicroservice.Repositories.CategoryRepository;
import com.example.productmicroservice.Repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Primary
public class DbCategoryService implements CategoryService {
    private CategoryRepository categoryRepository;
    DbCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<String> getAllCategories() {
        List<String> categoryList = new ArrayList<>();
        List<Category>categoryList2 = categoryRepository.findAll();
        for (Category category : categoryList2) {
            categoryList.add(category.getCategoryName());
        }
        return categoryList;
    }

    @Override
    public List<Product> getCategoryByType(String categoryType) {
        List<Product> productList = new ArrayList<>();
        productList=categoryRepository.findByCategoryName(categoryType);
        if(productList.size()==0){
            throw new RuntimeException("Products not found");
        }
        return productList;
    }
}
