package ru.startup.verifier_service.security.model.dto;

import jakarta.validation.constraints.NotNull;
import ru.startup.verifier_service.security.model.Role;

/**
 * Data transfer object (DTO) representing the change of user rights request.
 * <p>
 * This DTO encapsulates the new role to be assigned to a user.
 * </p>
 *
 * @since 1.0
 */
public record ChangeUserRightsDto(
        @NotNull(message = "newRole не должен быть пустым!")
        Role newRole) {
}
