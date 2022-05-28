package com.warp2.controller;

import com.warp2.model.User;
import com.warp2.repository.UserRepository;
import com.warp2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registerMethod() {

        return "/registration";
    }

    User buffer = new User();


    @PostMapping("/registration")
    public String registerMethod(User user, BindingResult bindingResult, Map<String, Object> map) {

        if (bindingResult.hasErrors()) {
            return "/register";
        }

        userService.addUser(user);

        return "/success";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {


        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("message", "User successfully activated");


        } else {
            model.addAttribute("message", "Activation code is not found");
        }
        return "/login";
    }
}
