package com.example.productmicroservice;


import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class SampleController {

    @GetMapping("/{name}")
    public String printString(@PathVariable String name) {
        return "Hello " + name+" this is your application";
    }
}
