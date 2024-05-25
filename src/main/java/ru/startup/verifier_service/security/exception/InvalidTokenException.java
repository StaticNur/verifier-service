package ru.startup.verifier_service.security.exception;

public class InvalidTokenException extends CustomExceptionForBadRequest {
    public InvalidTokenException(String message) {
        super(message);
    }
}
