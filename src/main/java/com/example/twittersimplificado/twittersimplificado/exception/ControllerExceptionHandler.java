package com.example.twittersimplificado.twittersimplificado.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity resourceNotFound(RuntimeException exception, HttpServletRequest request) {
        String error = "Not found";
        String message = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError(Instant.now(), status.value(), error, message, request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler({UnauthorizedUserException.class, BadCredentialsException.class})
    public ResponseEntity unauthorizedUser(RuntimeException exception, HttpServletRequest request) {
        String error = "Forbidden";
        String message = "Usúario não autorizado";
        HttpStatus status = HttpStatus.FORBIDDEN;
        StandardError standardError = new StandardError(Instant.now(), status.value(), error, message, request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(UniqueViolationException.class)
    public ResponseEntity uniqueViolation(RuntimeException exception, HttpServletRequest request) {
        String error = "Conflict";
        String message = "Usúario já cadastrado";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError standardError = new StandardError(Instant.now(), status.value(), error, message, request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity invalidPassword(RuntimeException exception, HttpServletRequest request) {
        String error = "Bad Request";
        String message = "Wrong password";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(Instant.now(), status.value(), error, message, request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }
}
