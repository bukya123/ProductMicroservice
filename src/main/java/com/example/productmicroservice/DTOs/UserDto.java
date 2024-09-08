package com.example.productmicroservice.DTOs;

import lombok.Getter;
import lombok.Setter;

import javax.management.relation.Role;
import java.util.List;

@Getter
@Setter
public class UserDto {
    private String name;
    private String email;
    private List<Role> roles;
    private boolean isVerified;
}
