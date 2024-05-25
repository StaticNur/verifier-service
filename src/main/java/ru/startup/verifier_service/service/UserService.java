package ru.startup.verifier_service.service;

import ru.startup.verifier_service.model.User;
import ru.startup.verifier_service.model.dto.SignInDto;

import java.util.Optional;

/**
 * Service interface for user-related operations.
 * <p>
 * This interface defines methods for user registration, email, changing user permissions,
 * retrieving audit logs, and accessing user information.
 * </p>
 *
 * @since 1.0
 */
public interface UserService {

    /**
     * Registers a new user with the provided registration data.
     *
     * @param registrationDto The registration data of the user.
     * @return The registered user.
     */
    void registerUser(SignInDto registrationDto);

    /**
     * Finds a user by their email address.
     *
     * @param email The email address of the user to find.
     * @return An optional containing the user if found, otherwise empty.
     */
    Optional<User> findByEmail(String email);
}

