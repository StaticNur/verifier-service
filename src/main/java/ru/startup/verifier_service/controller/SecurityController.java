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

/**
 * Controller class for handling authentication operations.
 */
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
