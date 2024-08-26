package com.example.productmicroservice.Services;

import com.example.productmicroservice.DTOs.FakeStoreDto;
import com.example.productmicroservice.Modules.Category;
import com.example.productmicroservice.Modules.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreService implements ProductService {
    private RestTemplate restTemplate;

    FakeStoreService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        for(long i=1;i<=20;i++){
            FakeStoreDto productDTO=restTemplate.getForObject("https://fakestoreapi.com/products/"+i,FakeStoreDto.class);
            Product product= new Product();
            Category c=new Category();
            c.setCategoryName(productDTO.getCategory());

            product.setId(productDTO.getId());
            product.setProductName(productDTO.getTitle());
            product.setProductDescription(productDTO.getDescription());
            product.setProductPrice(productDTO.getPrice());
            product.setImage(productDTO.getImageUrl());
            product.setCategory(c);
            products.add(product);
        }
        return products;
    }

    @Override
    public Product getProductById(long id) {
        FakeStoreDto productDTO=restTemplate.getForObject("https://fakestoreapi.com/products/"+id,FakeStoreDto.class);

        if(productDTO==null){
            throw new InvalidParameterException();
        }

        Product product=new Product();
        Category c=new Category();
        c.setCategoryName(productDTO.getCategory());

        product.setId(productDTO.getId());
        product.setProductName(productDTO.getTitle());
        product.setProductDescription(productDTO.getDescription());
        product.setProductPrice(productDTO.getPrice());
        product.setImage(productDTO.getImageUrl());
        product.setCategory(c);

        return product;
    }

    @Override
    public void addProduct(Product product) {

    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public void deleteProduct(int id) {

    }

    @Override
    public void replaceProduct(Product product) {

    }
}
