package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {
    @Autowired
    private UserService userService;

//    public SignupController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping("/signup")
    public String signupView() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUpUser(@ModelAttribute User user, Model model) {
        String signupError = null;
        if(userService.isUserAvailable(user.getUserName())) {
            signupError = "The username already exists";
        }

        if(signupError == null) {
            int userId = userService.createUser(user);
            if(userId < 0) {
                signupError = "Error in signup, Please try again";
            }
        }

        if(signupError == null) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError",signupError);
        }
        return "login";
    }
}
