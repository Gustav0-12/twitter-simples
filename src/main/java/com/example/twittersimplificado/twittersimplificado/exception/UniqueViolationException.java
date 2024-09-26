package com.example.twittersimplificado.twittersimplificado.exception;

public class UniqueViolationException extends RuntimeException {
    public UniqueViolationException(String msg) {
        super(msg);
    }
}
