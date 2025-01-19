package com.example.productmicroservice.advices;

import com.example.productmicroservice.DTOs.ExceptionDto;
import com.example.productmicroservice.Exceptions.InvalidProductException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {


    @ExceptionHandler(InvalidProductException.class)
    public ResponseEntity<ExceptionDto> handleInvalidProductIdException(InvalidProductException exception) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setProductId(exception.getProductId());
        exceptionDto.setMessage("Invalid productId passed, please retry with a valid productId");
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }
}
