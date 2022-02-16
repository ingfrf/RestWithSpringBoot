package com.enmivida.rest.exception.handler;

import com.enmivida.rest.exception.ExceptionResponse;
import com.enmivida.rest.exception.ResourceNotFoundException;
import com.enmivida.rest.exception.UnsuportedMathOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomizedReponseEntityExceptionHandler {

    // catch any exception
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(Exception exception, WebRequest request) {
        ExceptionResponse errorResponse = ExceptionResponse.builder()
                .message(exception.getMessage())
                .timestamp(System.currentTimeMillis())
                .details(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

     // catch UnsuportedMathOperationException
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(UnsuportedMathOperationException exception, WebRequest request) {
        ExceptionResponse errorResponse = ExceptionResponse.builder()
                .message(exception.getMessage())
                .timestamp(System.currentTimeMillis())
                .details(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(ResourceNotFoundException exception, WebRequest request) {
        ExceptionResponse errorResponse = ExceptionResponse.builder()
                .message(exception.getMessage())
                .timestamp(System.currentTimeMillis())
                .details(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
