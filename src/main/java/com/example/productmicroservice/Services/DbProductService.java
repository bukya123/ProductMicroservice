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
import java.util.Optional;

@Service
@Primary
public class DbProductService implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    public DbProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Product> getAllProducts() {
        List<Product>productList=new ArrayList<>();
        productRepository.findAll().forEach(product->productList.add(product));
        return productList;
    }

    @Override
    public Product getProductById(long id) {
        Optional<Product> optionalProduct=productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new RuntimeException("Product not found");
        }
        Product product=optionalProduct.get();
        return product;
    }

    @Override
    public Product addProduct(Product product) {
        Category category=product.getCategory();
        if(category==null){
            throw new RuntimeException("Category not found");
        }
        Category category1=categoryRepository.save(category);
        product.setCategory(category1);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(long id, Product product) {

        return null;
    }

    @Override
    public void deleteProduct(long id) {
        Optional<Product> optionalProduct=productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new RuntimeException("Product not found");
        }
        Product product=optionalProduct.get();
      productRepository.deleteById(id);
    }

    @Override
    public Product replaceProduct(long id, Product product) {
        Optional<Product> optionalProduct=productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new RuntimeException("Not found any product to replace");
        }
        Product product1=optionalProduct.get();
        Category category=product.getCategory();

        // if product category is null ---> we should replace it with null
        if(category==null){
            product1.setCategory(category);
        }else{
            // if product category is not null
            //1) we should check whether the category is present in category-->
            //     yes-->set it
            //2) if no--> then we need to add to the category list and then we need to set it to product table

            Optional<Category> category1=categoryRepository.findById(category.getId());
            if(category1.isPresent()){
                product1.setCategory(category1.get());
            }else{
                Category category2=categoryRepository.save(category);
                product1.setCategory(category2);
            }

        }
        product1.setProductName(product.getProductName());
        product1.setProductPrice(product.getProductPrice());
        product1.setProductDescription(product.getProductDescription());
        product1.setImage(product.getImage());
        return productRepository.save(product1);
    }
}
