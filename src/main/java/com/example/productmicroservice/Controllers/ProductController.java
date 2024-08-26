package com.example.productmicroservice.Controllers;


import com.example.productmicroservice.Modules.Product;
import com.example.productmicroservice.Services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }



    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping({"/{productId}"})
    public Product getSingleProduct(@PathVariable("productId") long productId) {

        return productService.getProductById(productId);

    }

    @PostMapping
    public void  addProduct(@RequestBody Product product) {

        return ;
    }

    @PatchMapping({"/productId"})
    public void updateProduct(@PathVariable("productId") long productId, @RequestBody Product product) {

        return ;
    }

    @PutMapping({"/productId"})
    public void replaceProduct(@PathVariable("productId") long productId, @RequestBody Product product) {

        return ;
    }

    @DeleteMapping("/{productId}")
    public void  deleteProduct(@PathVariable("productId") long productId, @RequestBody Product product) {
        return ;
    }
}
