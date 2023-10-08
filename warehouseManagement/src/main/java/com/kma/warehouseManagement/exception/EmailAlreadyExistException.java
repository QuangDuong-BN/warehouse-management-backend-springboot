package com.kma.warehouseManagement.exception;

public class EmailAlreadyExistException extends  RuntimeException{
    public EmailAlreadyExistException(String message) {
        super(message);
    }
}
