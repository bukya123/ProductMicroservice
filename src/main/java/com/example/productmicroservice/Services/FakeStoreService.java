package com.example.productmicroservice.Services;

import com.example.productmicroservice.DTOs.FakeStoreDto;
import com.example.productmicroservice.Modules.Category;
import com.example.productmicroservice.Modules.Product;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.util.Objects.nonNull;

@Service
public class FakeStoreService implements ProductService {
    private RestTemplate restTemplate;

    FakeStoreService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product convertFakeStoreDtotoProduct(FakeStoreDto fakeStoreDto){
        Product product= new Product();
        Category c=new Category();
        c.setCategoryName(fakeStoreDto.getCategory());

        product.setId(fakeStoreDto.getId());
        product.setProductName(fakeStoreDto.getTitle());
        product.setProductDescription(fakeStoreDto.getDescription());
        product.setProductPrice(fakeStoreDto.getPrice());
        product.setImage(fakeStoreDto.getImage());
        product.setCategory(c);
        return product;
    }


    @Override
    public List<Product> getAllProducts() {
       FakeStoreDto fakeStoreDto[]=restTemplate.getForObject("https://fakestoreapi.com/products",FakeStoreDto[].class);
       List<Product> products=new ArrayList<>();
       for(FakeStoreDto fakeStoreDto1:fakeStoreDto){
           products.add(convertFakeStoreDtotoProduct(fakeStoreDto1));
       }

       return products;


    }

    @Override
    public Product getProductById(long id) {
        ResponseEntity<FakeStoreDto>response=restTemplate.getForEntity("https://fakestoreapi.com/products/"+id,FakeStoreDto.class);
        return convertFakeStoreDtotoProduct(response.getBody());
    }

    @Override
    public Product addProduct(FakeStoreDto product) {
       ResponseEntity<FakeStoreDto>response= restTemplate.postForEntity("https://fakestoreapi.com/products",product,FakeStoreDto.class);
       return convertFakeStoreDtotoProduct(response.getBody());

    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod,String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }


    @Override
    public Product updateProduct(long id,FakeStoreDto product) {
        //first Method-
        // FakeStoreDto response=restTemplate.patchForObject("https://fakestoreapi.com/products",product,FakeStoreDto.class,id);

        //second method
       ResponseEntity<FakeStoreDto>response1=requestForEntity(HttpMethod.PATCH,"https://fakestoreapi.com/products",product,FakeStoreDto.class,id);
        return convertFakeStoreDtotoProduct(response1.getBody());


        //difference between restTemplate.forObject vs restTemplate.forEntity
        // forObject will directly returns object
        //forEntity will returns responseEntity -->responseEntity has responseStatus, responseBody and headers...
    }

    @Override
    public Product deleteProduct(long id) {

        return null;
    }

    @Override
    public Product replaceProduct(long id,FakeStoreDto product) {
        ResponseEntity<FakeStoreDto>response=requestForEntity(HttpMethod.PUT,"https://fakestoreapi.com/products/{id}",product,FakeStoreDto.class,id);
        return convertFakeStoreDtotoProduct(response.getBody());
    }
}
