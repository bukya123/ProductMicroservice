package com.example.productmicroservice.Services;


import com.example.productmicroservice.DTOs.FakeStoreDto;
import com.example.productmicroservice.Modules.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreCategoryService implements CategoryService {
    private RestTemplate restTemplate ;

    FakeStoreCategoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<String> getAllCategories() {
        String categories[] = restTemplate.getForEntity("https://fakestoreapi.com/products/categories",String[].class).getBody();

        List<String> categoriesList = new ArrayList<>();
        for(String str:categories){
            categoriesList.add(str);
        }
        return categoriesList;
    }

    @Override
    public List<Product> getCategoryByType(String categoryType) {
        FakeStoreDto[] fakeStoreDto = restTemplate.getForEntity("https://fakestoreapi.com/products/category/{categoryType}",FakeStoreDto[].class,categoryType).getBody();
        List<FakeStoreDto> fakeStoreDtoList = new ArrayList<>();
        for (FakeStoreDto fakeStoreDto1 : fakeStoreDto) {
            fakeStoreDtoList.add(fakeStoreDto1);
        }
        return null;
    }

}
