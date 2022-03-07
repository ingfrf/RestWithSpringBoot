package com.enmivida.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.AuthenticationException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidJwtAuthenticationException extends AuthenticationException {

    private static final long serialVersionUID = 1536035703431191241L;

    public InvalidJwtAuthenticationException(String message) {
        super(message);
    }
}
