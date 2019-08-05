package com.honeywell.vendingmachine.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public final class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<Object> handleProductNotFound(ProductNotFoundException exception, WebRequest webRequest) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage(), null);
        return new ResponseEntity<>(apiErrorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ProductOutOfStockException.class})
    public ResponseEntity<Object> handleProductOutOfStock(ProductOutOfStockException exception, WebRequest webRequest) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), null);
        return new ResponseEntity<>(apiErrorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException exception, WebRequest webRequest) {
        Set<? extends ConstraintViolation<?>> violations = exception.getConstraintViolations();
        List<ApiErrorResponse.ApiErrorObject> apiErrorObjectList = violations.stream().map(input -> {
            Path keyPropertyPath = input.getPropertyPath();
            String field = keyPropertyPath != null ? String.valueOf(keyPropertyPath) : "";
            return new ApiErrorResponse.ApiErrorObject(field, input.getMessage());
        }).collect(Collectors.toList());
        String message = "Validation failed. " + apiErrorObjectList.size() + " errors";
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), message, apiErrorObjectList);
        return new ResponseEntity<>(apiErrorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }


    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        List<ApiErrorResponse.ApiErrorObject> apiErrorObjects = exception.getBindingResult().getFieldErrors().stream().map(input -> {
            return new ApiErrorResponse.ApiErrorObject(input.getField(), input.getDefaultMessage());
        }).collect(Collectors.toList());
        exception.getBindingResult().getGlobalErrors().forEach(error -> {
            apiErrorObjects.add(new ApiErrorResponse.ApiErrorObject(error.getObjectName(), error.getDefaultMessage()));
        });
        String message = "Validation failed. " + apiErrorObjects.size() + " errors";
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), message, apiErrorObjects);
        return new ResponseEntity<>(apiErrorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
