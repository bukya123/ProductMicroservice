package com.example.productmicroservice.Controllers;

import com.example.productmicroservice.Modules.Product;
import com.example.productmicroservice.Services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @Test
    void getAllProducts() {

    }

    @Test
    void getSingleProduct() {
        Product product = new Product();

        when(productService.getProductById(1000L))
                .thenReturn(product);
        //when we call productservice we should return product

        assertEquals(product,productController.getSingleProduct(1000L).getBody());
    }

    @Test
    void addProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void replaceProduct() {
    }

    @Test
    void deleteProduct() {
    }
}