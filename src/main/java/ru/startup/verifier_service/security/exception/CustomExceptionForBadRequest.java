package ru.startup.verifier_service.security.exception;

public class CustomExceptionForBadRequest extends RuntimeException {
    public CustomExceptionForBadRequest(String message) {
        super(message);
    }
}

