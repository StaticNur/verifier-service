package ru.startup.verifier_service.controller.UiController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.startup.verifier_service.service.UserService;
import ru.startup.verifier_service.util.PersonValidator;

@Controller
@RequestMapping("/verifier-service/auth")
@RequiredArgsConstructor
public class SecurityControllerUI {
    private final PersonValidator personValidator;
    private final UserService userService;

    @GetMapping("/sign-up")
    public String showSignUpForm() {
        return "auth/sign-up";
    }

    @GetMapping("/sign-in")
    public String authentication() {
        return "auth/sign-in";
    }
}
