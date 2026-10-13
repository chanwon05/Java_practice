package com.chanwon.practice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiError> handleResponseStatus(
            ResponseStatusException e) {

        ApiError error = new ApiError(
            e.getStatusCode().value(),
            e.getReason()
        );

        return ResponseEntity
                .status(e.getStatusCode())
                .body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(
            MethodArgumentNotValidException e) {

        FieldError fieldError = e.getBindingResult().getFieldError();

        String message = "입력값이 올바르지 않습니다.";

        if (fieldError != null) {
            message = fieldError.getDefaultMessage();
        }

        ApiError error = new ApiError(400, message);

        return ResponseEntity
                .status(400)
                .body(error);
    }
    
}