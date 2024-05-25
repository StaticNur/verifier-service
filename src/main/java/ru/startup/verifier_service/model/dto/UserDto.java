package ru.startup.verifier_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.startup.verifier_service.security.model.Role;

import java.util.UUID;

/**
 * Data transfer object (DTO) representing a user.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private UUID uuid;
    /**
     * The email address of the user
     */
    private String email;
    /**
     * The role of the user (e.g., ADMIN, USER)
     */
    private Role role;
}
