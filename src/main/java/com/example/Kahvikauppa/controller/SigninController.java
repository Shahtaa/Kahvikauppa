package com.example.Kahvikauppa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SigninController {

    @GetMapping("/signin")
    public String signinForm() {
        return "signin"; // This will resolve to signin.html
    }

    @PostMapping("/signin")
    public String signinSubmit(@RequestParam String username, @RequestParam String password) {
        // Check username and password against your authentication mechanism
        // For demonstration purposes, let's just print the credentials
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        // Redirect to the appropriate page based on authentication result
        // For example, you can redirect to a dashboard page if authentication is
        // successful
        // or redirect back to the signin page with an error message if authentication
        // fails
        return "redirect:/dashboard"; // Assuming there's a dashboard page
    }
}
