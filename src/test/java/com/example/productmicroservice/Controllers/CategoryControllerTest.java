package com.example.productmicroservice.Controllers;

import org.junit.jupiter.api.Test;
//junit--> is the library for testing
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
//springBootTest--->to load SpringContext-->which stores all the beans
// it is not required.if we are doing simple addition function
// but we need it if we are controller,service objects
class CategoryControllerTest {

    @Test
    void getAllCategories() {

    }

    @Test
    void getCategoryById() {
    }
}