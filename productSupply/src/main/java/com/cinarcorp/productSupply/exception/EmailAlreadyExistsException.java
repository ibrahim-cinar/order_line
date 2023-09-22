package com.cinarcorp.productSupply.exception;

public class EmailAlreadyExistsException extends  RuntimeException{
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
