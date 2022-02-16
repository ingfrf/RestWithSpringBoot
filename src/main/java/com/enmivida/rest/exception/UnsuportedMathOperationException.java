package com.enmivida.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UnsuportedMathOperationException extends RuntimeException {

    private static final long serialVersionUID = -5171592180518680942L;

    public UnsuportedMathOperationException(String message) {
        super(message);
    }
}
