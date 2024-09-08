package com.example.productmicroservice.Controllers;


import com.example.productmicroservice.DTOs.FakeStoreDto;
import com.example.productmicroservice.DTOs.UserDto;
import com.example.productmicroservice.Modules.Product;
import com.example.productmicroservice.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    @Autowired
    private RestTemplate restTemplate;
    public ProductController( ProductService productService) {

        this.productService = productService;
    }


    @GetMapping("/all/{token}")
    public List<Product> getAllProducts(@PathVariable String token) {
       ResponseEntity<UserDto>userDto= restTemplate.postForEntity("http://localhost:2028/user/{token}",null,UserDto.class,token);
       if(userDto.getBody()==null){
           throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Unauthorized");
       }
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
