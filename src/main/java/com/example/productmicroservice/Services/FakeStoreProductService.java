package com.example.productmicroservice.Services;

import com.example.productmicroservice.DTOs.FakeStoreDto;
import com.example.productmicroservice.Modules.Category;
import com.example.productmicroservice.Modules.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;
    private RedisTemplate redisTemplate;

    FakeStoreProductService(RestTemplate restTemplate,RedisTemplate redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
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

    public FakeStoreDto call(Product product){
        FakeStoreDto fakeStoreDto=new FakeStoreDto();
        fakeStoreDto.setCategory(product.getCategory().getCategoryName());
        fakeStoreDto.setId(product.getId());
        fakeStoreDto.setTitle(product.getProductName());
        fakeStoreDto.setDescription(product.getProductDescription());
        fakeStoreDto.setPrice(product.getProductPrice());
        fakeStoreDto.setImage(product.getImage());
        return fakeStoreDto;
    }


    @Override
    public Page<Product> getAllProducts(int PageNo, int PageSize,String SortType) {
       FakeStoreDto fakeStoreDto[]=restTemplate.getForObject("https://fakestoreapi.com/products",FakeStoreDto[].class);
       List<Product> products=new ArrayList<>();
       for(FakeStoreDto fakeStoreDto1:fakeStoreDto){
           products.add(convertFakeStoreDtotoProduct(fakeStoreDto1));
       }

       return null;


    }

    @Override
    public Product getProductById(long id) {
        Product product=(Product) redisTemplate.opsForHash().get("PRODUCTS", "PRODUCT_" + id);
        if(product!=null){
            return product;
        }

        ResponseEntity<FakeStoreDto>response=restTemplate.getForEntity("https://fakestoreapi.com/products/"+id,FakeStoreDto.class);
        Product product1= convertFakeStoreDtotoProduct(response.getBody());
        redisTemplate.opsForHash().put("PRODUCTS", "PRODUCT_" + id, product1);
        return product1;
    }

    @Override
    public Product addProduct(Product product) {

       ResponseEntity<FakeStoreDto>response= restTemplate.postForEntity("https://fakestoreapi.com/products",product,FakeStoreDto.class);
       return convertFakeStoreDtotoProduct(response.getBody());

    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod,String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }


    @Override
    public Product updateProduct(long id,Product product) {
        //first Method-
        // FakeStoreDto response=restTemplate.patchForObject("https://fakestoreapi.com/products",product,FakeStoreDto.class,id);

        //second method
       ResponseEntity<FakeStoreDto>response1=requestForEntity(HttpMethod.PATCH,"https://fakestoreapi.com/products/{id}",product,FakeStoreDto.class,id);
        return convertFakeStoreDtotoProduct(response1.getBody());


        //difference between restTemplate.forObject vs restTemplate.forEntity
        // forObject will directly returns object
        //forEntity will returns responseEntity -->responseEntity has responseStatus, responseBody and headers...
    }

    @Override
    public void deleteProduct(long id) {

      ;
    }

    @Override
    public Product replaceProduct(long id,Product product) {
        ResponseEntity<FakeStoreDto>response=requestForEntity(HttpMethod.PUT,"https://fakestoreapi.com/products/{id}",product,FakeStoreDto.class,id);
        return convertFakeStoreDtotoProduct(response.getBody());
    }
}
