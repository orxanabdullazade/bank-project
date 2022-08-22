package com.unibank.unitech.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.SignatureException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomErrorException.class)
    public ErrorResponse handleCustomException(CustomErrorException customException){

        return ErrorResponse.builder()
                 .message(customException.getMessage())
                 .code(customException.getCode())
                 .build();
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ErrorResponse handleAllOthers(Exception ex) {

        return ErrorResponse.builder()
                .message(ex.getMessage())
                .code(ErrorCodeEnum.UNKNOWN_ERROR.getCode())
                .build();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = io.jsonwebtoken.SignatureException.class)
    public ErrorResponse handleUnauthorized(io.jsonwebtoken.SignatureException ex) {

        return ErrorResponse.builder()
                .message(ErrorCodeEnum.UNAUTHORIZED.getMessage())
                .code(ErrorCodeEnum.UNAUTHORIZED.getCode())
                .build();
    }


}
