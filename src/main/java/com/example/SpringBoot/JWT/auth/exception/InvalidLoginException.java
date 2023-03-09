package com.example.SpringBoot.JWT.auth.exception;

public class InvalidLoginException extends RuntimeException{
    public InvalidLoginException(String msg) {
        super(msg);
    }
}
