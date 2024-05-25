package ru.startup.verifier_service.controller.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.startup.verifier_service.mapper.UserMapper;
import ru.startup.verifier_service.model.CustomFieldError;
import ru.startup.verifier_service.model.User;
import ru.startup.verifier_service.security.model.JwtResponse;
import ru.startup.verifier_service.security.model.dto.LoginDto;
import ru.startup.verifier_service.security.model.dto.RefreshTokenDto;
import ru.startup.verifier_service.security.model.dto.RegistrationDto;
import ru.startup.verifier_service.service.UserService;
import ru.startup.verifier_service.util.GeneratorResponseMessage;

import java.util.List;

/**
 * Controller class for handling authentication operations.
 */
@RestController
@RequestMapping("/verifier-service/auth")
@RequiredArgsConstructor
public class SecurityController {

    /**
     * The service for user-related operations.
     */
    private final UserService userService;

    /**
     * Utility for generating custom error response messages
     */
    private final GeneratorResponseMessage generatorResponseMessage;

    /**
     * The mapper for mapping user entities to DTOs.
     */
    private final UserMapper userMapper;

    /**
     * Registers a new user.
     *
     * @param registrationDto The SecurityRequest object containing user registration information.
     * @return ResponseEntity containing the registered User object.
     */
    @PostMapping("/sign-up")
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationDto registrationDto,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<CustomFieldError> customFieldErrors = generatorResponseMessage.generateErrorMessage(bindingResult);
            return ResponseEntity.badRequest().body(customFieldErrors);
        }
        User user = userService.registerUser(registrationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toDto(user));
    }

    /**
     * Authorizes a user.
     *
     * @param loginDto The SecurityRequest object containing user authorization information.
     * @return ResponseEntity containing the authorization token.
     */
    @PostMapping("/sign-in")
    public ResponseEntity<?> authorize(@RequestBody @Valid LoginDto loginDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<CustomFieldError> customFieldErrors = generatorResponseMessage.generateErrorMessage(bindingResult);
            return ResponseEntity.badRequest().body(customFieldErrors);
        }
        JwtResponse response = userService.login(loginDto);
        return ResponseEntity.ok(response);
    }

    /**
     * update the token a user.
     *
     * @param refreshTokenDto The SecurityRequest object containing user refresh token.
     * @return ResponseEntity containing the authorization token.
     */
    @PostMapping("/refresh-token")
    public ResponseEntity<?> extendTheLifeOfTheToken(@RequestBody @Valid RefreshTokenDto refreshTokenDto,
                                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<CustomFieldError> customFieldErrors = generatorResponseMessage.generateErrorMessage(bindingResult);
            return ResponseEntity.badRequest().body(customFieldErrors);
        }
        JwtResponse response = userService.updateToken(refreshTokenDto.refreshToken().trim());
        return ResponseEntity.ok(response);
    }
}

/*
package ru.startup.verifier_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.startup.verifier_service.model.User;
import ru.startup.verifier_service.model.dto.SignInDto;
import ru.startup.verifier_service.service.UserService;
import ru.startup.verifier_service.util.PersonValidator;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class SecurityController {

    private final PersonValidator personValidator;
    private final UserService userService;

    @GetMapping("/sign-up")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "auth/sign-up";
    }

    @PostMapping("/sign-up")
    public String registration(@ModelAttribute("user") @Valid SignInDto registrationDto,
                               BindingResult bindingResult) {
        personValidator.validate(registrationDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "auth/sign-up";
        }
        userService.registerUser(registrationDto);
        return "redirect:/auth/sign-in";
    }

    @GetMapping("/sign-in")
    public String authentication() {
        return "auth/sign-in";
    }
}
*/
