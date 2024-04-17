package com.example.Kahvikauppa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KulutustuotteetController {

    @GetMapping("/kulutustuotteet")
    public String kulutustuotteet() {
        return "kulutustuotteet";
    }
}
