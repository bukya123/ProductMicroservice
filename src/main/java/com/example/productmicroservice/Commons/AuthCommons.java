package com.example.productmicroservice.Commons;

import com.example.productmicroservice.DTOs.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthCommons {
    private RestTemplate restTemplate;
    public AuthCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String token) {
        ResponseEntity<UserDto>userDtoResponseEntity=restTemplate.postForEntity("http://localhost:2028/user/validate/"+token, null, UserDto.class);
        if(userDtoResponseEntity.getBody()==null){
            return null;
        }
        return userDtoResponseEntity.getBody();
    }
}
