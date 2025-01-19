package com.example.productmicroservice.Controllers;


import com.example.productmicroservice.Commons.AuthCommons;
import com.example.productmicroservice.DTOs.FakeStoreDto;
import com.example.productmicroservice.DTOs.UserDto;
import com.example.productmicroservice.Exceptions.InvalidProductException;
import com.example.productmicroservice.Modules.Product;
import com.example.productmicroservice.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final RestTemplate restTemplate;
    private ProductService productService;
    private AuthCommons authCommons;
    public ProductController(ProductService productService, AuthCommons authCommons, RestTemplate restTemplate) {

        this.productService = productService;
        this.authCommons = authCommons;
        this.restTemplate = restTemplate;
    }


//    @GetMapping("/all/{token}")
    @GetMapping("/")
    public Page<Product> getAllProducts(@RequestParam("PageNo") int PageNo, @RequestParam("PageSize") int PageSize,@RequestParam("SortType") String SortType) {
       //u should not write the validate token using Userservice because the same authencation is needed for category controller also.so it leads to code duplicacy.so create a new file commons.
//        UserDto userDto=authCommons.validateToken(token);
//        if(userDto==null) {
//            return new ArrayList<>();
//        }
        return productService.getAllProducts(PageNo,PageSize,SortType);
    }

    @GetMapping({"/{productId}"})
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") long productId) throws InvalidProductException {


        ResponseEntity<String> res=restTemplate.getForEntity("http://UserMicroservice/user/1",String.class);

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
