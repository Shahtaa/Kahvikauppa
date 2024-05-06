package com.example.Kahvikauppa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInController {

    @GetMapping("/signin")
    public String showLoginForm(Model model) {
        // Add any additional model attributes if needed
        return "signin"; // Assuming your Thymeleaf template is named "signin.html"
    }

    // Add any additional controller methods for handling the sign-in request, if
    // needed
}
