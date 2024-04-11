package com.example.Kahvikauppa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KahvikauppaController {

    @GetMapping("/")
    public String index() {
        return "index"; // Return the name of the index.html file
    }

    @GetMapping("/osastot")
    public String osastotPage() {
        return "osastot";
    }

    @GetMapping("/kahvilaitteet")
    public String kahvilaitteetPage() {
        return "kahvilaitteet";
    }

    @GetMapping("/kulutustuotteet")
    public String kulutustuotteetPage() {
        return "kulutustuotteet";
    }
}
