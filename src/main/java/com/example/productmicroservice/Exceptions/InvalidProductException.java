package com.example.productmicroservice.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidProductException extends Exception{
    private Long productId;
    public InvalidProductException(Long productId, String message) {
        super(message);
        this.productId = productId;
    }
}
