package com.example.productmicroservice.Services;

import com.example.productmicroservice.Modules.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();
    public Product getProductById(long id);
    public void addProduct(Product product);
    public void updateProduct(Product product);
    public void deleteProduct(int id);
    public void replaceProduct(Product product);
}
