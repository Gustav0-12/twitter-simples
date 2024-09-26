package com.example.twittersimplificado.twittersimplificado.exception;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String msg) {
        super(msg);
    }
}
