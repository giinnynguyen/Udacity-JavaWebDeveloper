package com.udacity.jwdnd.course1.cloudstorage.controller.authentication;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {
    private UserService userService;
    public SignupController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/signup")
    public String signup(User user, Model model) {
        model.addAttribute("message", "");
        return "signup";
    }

    @PostMapping("/signup")
    public String signupPost(User user, Model model) {
        try {
            Integer insertedID = this.userService.createUser(user);
        } catch (DuplicateKeyException e) {
            model.addAttribute("message", "errorUserExist");
            return "signup";
        }
        model.addAttribute("message", "success");
        return "signup";
    }
}
