package ru.startup.verifier_service.security.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.startup.verifier_service.security.model.Role;

/**
 * Data transfer object (DTO) representing user registration information.
 * <p>
 * This class encapsulates the email address, password, and role for user registration.
 * </p>
 *
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {
    /**
     * The email address of the user.
     */
    @Pattern(regexp = "^(?!\\d+$).+", message = "Не должен содержать одни цифры!")
    @NotNull(message = "Обязательное поля!")
    @NotBlank(message = "Не должен быть пустым!")
    private String email;

    /**
     * The password of the user.
     */
    @NotNull(message = "Обязательное поля!")
    @NotBlank(message = "Не должен быть пустым!")
    private String password;
}

