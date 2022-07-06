package com.Prograd.EmployeeManagement.Exceptions;


import com.Prograd.EmployeeManagement.Modals.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice              // make exception handler of controller
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFound.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(EmployeeNotFound error) {
        String message = error.getMessage();
        return new ResponseEntity<ApiResponse>(new ApiResponse(message, false), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AssetNotFound.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(AssetNotFound error) {
        String message = error.getMessage();
        return new ResponseEntity<ApiResponse>(new ApiResponse(message, false), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrganisationNotFound.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(OrganisationNotFound error) {
        String message = error.getMessage();
        return new ResponseEntity<ApiResponse>(new ApiResponse(message, false), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoAccessException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(NoAccessException error) {
        String message = error.getMessage();
        return new ResponseEntity<ApiResponse>(new ApiResponse(message, false), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException e){
        Map<String,String> resp = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error)->{
            String FieldName = ((FieldError)error).getField();   // type cast object 'error' in FieldError  & get field name that produces error.
            String message = error.getDefaultMessage();
            resp.put(FieldName,message);
        });
        return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
    }
}
