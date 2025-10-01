package com.registration_service.exception;

public class FieldAlreadyExistsException extends RuntimeException {

     public FieldAlreadyExistsException(String fieldName, String fieldValue) {
        super(String.format("%s '%s' already exists", fieldName, fieldValue));
    }
    
}
