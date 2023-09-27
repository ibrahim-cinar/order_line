package com.cinarcorp.orderLine.exception;

public class EmailAlreadyExistsException extends  RuntimeException{
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
