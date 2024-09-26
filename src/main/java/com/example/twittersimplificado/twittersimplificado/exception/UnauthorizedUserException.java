package com.example.twittersimplificado.twittersimplificado.exception;

public class UnauthorizedUserException extends RuntimeException {
    public UnauthorizedUserException(String msg) {
        super(msg);
    }
}
