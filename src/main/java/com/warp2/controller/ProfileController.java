package com.warp2.controller;

import com.warp2.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    private final UserRepository userRepositoryJPA;

    public ProfileController(UserRepository userRepositoryJPA) {
        this.userRepositoryJPA = userRepositoryJPA;
    }

    @GetMapping("/prof")
    public String mainPage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.warp2.model.User xUser;
        xUser = userRepositoryJPA.findByEmail(user.getUsername());

        model.addAttribute("xUser", xUser);
        return "prof";
    }
}
