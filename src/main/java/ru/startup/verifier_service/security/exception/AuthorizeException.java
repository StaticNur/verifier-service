package ru.startup.verifier_service.security.exception;


public class AuthorizeException extends RuntimeException {
    public AuthorizeException(String message) {
        super(message);
    }
}

