package ru.startup.verifier_service.controller.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.startup.verifier_service.model.CustomFieldError;
import ru.startup.verifier_service.model.User;
import ru.startup.verifier_service.security.exception.SuccessResponse;
import ru.startup.verifier_service.security.model.dto.ChangeUserRightsDto;
import ru.startup.verifier_service.service.UserService;
import ru.startup.verifier_service.util.GeneratorResponseMessage;

import java.util.List;

/**
 * Controller class for managing user-related operations.
 */
@RestController
@RequestMapping("/verifier-service")
@RequiredArgsConstructor
public class UserController {
    /**
     * Service for user-related operations
     */
    private final UserService userService;

    /**
     * Utility for generating custom error response messages
     */
    private final GeneratorResponseMessage generatorResponseMessage;

    /**
     * Changes user rights.
     *
     * @param uuid                UUID of the user
     * @param changeUserRightsDto DTO containing user rights to be changed
     * @param bindingResult       BindingResult for validating the request body
     * @return ResponseEntity containing a SuccessResponse object
     */
    @PatchMapping("/users/{uuid}/access")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> changeUserRights(@PathVariable("uuid") String uuid,
                                              @RequestBody @Valid ChangeUserRightsDto changeUserRightsDto,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<CustomFieldError> customFieldErrors = generatorResponseMessage.generateErrorMessage(bindingResult);
            return ResponseEntity.badRequest().body(customFieldErrors);
        }
        User user = userService.changeUserPermissions(uuid, changeUserRightsDto);

        return ResponseEntity.ok(new SuccessResponse("Права доступа успешно изменена! " +
                                                     "Теперь для данного пользователя " + user.getEmail()
                                                     + " требуется повторная авторизация для обновления токена."));
    }
}
