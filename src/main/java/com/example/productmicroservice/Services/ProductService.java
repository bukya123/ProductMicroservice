package com.example.productmicroservice.Services;

import com.example.productmicroservice.DTOs.FakeStoreDto;
import com.example.productmicroservice.Modules.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();
    public Product getProductById(long id);
    public Product addProduct(Product product);
    public Product updateProduct(long id,Product product);
    public void deleteProduct(long id);
    public Product replaceProduct(long id, Product product);
}
