package com.unibank.unitech.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class CustomExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleAllOthers(Exception ex) {
        log.error("Error happened , details {},{}", ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(CommonCodes.INTERNAL_ERROR.name())
                .description(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.error("Error happened , details {}, {}", ex.getMessage(), HttpStatus.NOT_FOUND);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(CommonCodes.RESOURCE_NOT_FOUND.name())
                .description(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("Error happened , details {}, {}", ex.getMessage(), HttpStatus.BAD_REQUEST);
        List<String> errorMessages = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + "-" + fieldError.getDefaultMessage())
                .distinct()
                .collect(Collectors.toList());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(CommonCodes.VALIDATION_FAILED.name())
                .details(errorMessages)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(NoSuchFileException.class)
    public ResponseEntity<ErrorResponse> handleResourceNoSuchFileException(NoSuchFileException ex) {
        log.error("Error happened , details {}, {}", ex.getMessage(), HttpStatus.NOT_FOUND);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(CommonCodes.RESOURCE_NOT_FOUND.name())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }


    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ErrorResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        log.error("Error handleMethodNotSupported {},{}",ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(CommonCodes.HTTP_REQUEST_ERROR.name())
                .description(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(errorResponse);
    }



}
