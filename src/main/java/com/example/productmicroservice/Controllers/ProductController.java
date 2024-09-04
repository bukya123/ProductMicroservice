package com.example.productmicroservice.Controllers;


import com.example.productmicroservice.DTOs.FakeStoreDto;
import com.example.productmicroservice.Modules.Product;
import com.example.productmicroservice.Services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;


    public ProductController( ProductService productService) {
        this.productService = productService;
    }


    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping({"/{productId}"})
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") long productId) {

        Product product = productService.getProductById(productId);
        ResponseEntity<Product>responseEntity=new ResponseEntity<>(product, HttpStatus.OK);
        return responseEntity;

    }

    @PostMapping()
    public ResponseEntity<Product>addProduct(@RequestBody Product product) {
        Product product1=productService.addProduct(product);
        ResponseEntity<Product>productResponseEntity=new ResponseEntity<>(product1, HttpStatus.OK);
        return productResponseEntity;
    }

    @PatchMapping({"/{productId}"})
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") long productId, @RequestBody Product product) {

        Product product1=productService.updateProduct(productId, product);
        ResponseEntity<Product>productResponseEntity1=new ResponseEntity<>(product1, HttpStatus.OK);
        return productResponseEntity1;
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> replaceProduct(@PathVariable("productId") long productId, @RequestBody Product product) {

        Product product1= productService.replaceProduct(productId,product) ;
        ResponseEntity<Product>productResponseEntity=new ResponseEntity<>(product1, HttpStatus.OK);
        return productResponseEntity;

    }

    @DeleteMapping("/{productId}")
    public void  deleteProduct(@PathVariable("productId") long productId) {
        productService.deleteProduct(productId);
    }
}
