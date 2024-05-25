package ru.startup.verifier_service.security.exception;

/**
 * Represents an exception response.
 * <p>
 * This record encapsulates an exception message to be returned as part of an error response.
 * </p>
 *
 * @since 1.0
 */
public record ExceptionResponse(String message) {
}
