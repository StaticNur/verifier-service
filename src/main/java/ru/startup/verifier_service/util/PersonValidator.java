package ru.startup.verifier_service.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.startup.verifier_service.model.dto.SignInDto;
import ru.startup.verifier_service.service.UserService;

@Component
public class PersonValidator implements Validator {
    private final UserService userService;

    public PersonValidator(UserService userService) {this.userService = userService;}

    @Override
    public boolean supports(Class<?> aClass) {
        return userService.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SignInDto user = (SignInDto) o;
        if (userService.findByEmail(user.getEmail()).isPresent())
            errors.rejectValue("email","","Это почта уже занято");
    }
}
