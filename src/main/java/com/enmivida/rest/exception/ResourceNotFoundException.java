package com.enmivida.rest.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 7951949130500737202L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
