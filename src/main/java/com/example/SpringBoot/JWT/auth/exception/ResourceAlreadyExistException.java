package com.example.SpringBoot.JWT.auth.exception;

public class ResourceAlreadyExistException extends RuntimeException{
    public ResourceAlreadyExistException(String msg) {
        super(msg);
    }
}
