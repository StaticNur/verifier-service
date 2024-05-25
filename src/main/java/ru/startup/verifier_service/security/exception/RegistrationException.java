package ru.startup.verifier_service.security.exception;

/**
 * Exception thrown when there is an issue during the user registration process.
 */
public class RegistrationException extends CustomExceptionForBadRequest {
    public RegistrationException(String message) {
        super(message);
    }
}
