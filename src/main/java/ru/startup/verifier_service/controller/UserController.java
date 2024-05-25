package ru.startup.verifier_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.startup.verifier_service.model.User;

@Controller
@RequestMapping("/verifier-service")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/personal-account")
    public String showPersonalAccount() {
        return "personal_account/index";
    }
}
