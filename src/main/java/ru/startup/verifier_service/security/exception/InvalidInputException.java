package ru.startup.verifier_service.security.exception;

public class InvalidInputException extends CustomExceptionForBadRequest {
    public InvalidInputException(String message) {
        super(message);
    }
}
