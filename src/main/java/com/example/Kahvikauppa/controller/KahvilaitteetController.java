package com.example.Kahvikauppa.controller;

import com.example.Kahvikauppa.model.Tuote;
import com.example.Kahvikauppa.service.TuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class KahvilaitteetController {

    private final TuoteService tuoteService;

    @Autowired
    public KahvilaitteetController(TuoteService tuoteService) {
        this.tuoteService = tuoteService;
    }

    @GetMapping("/kahvilaitteet")
    public String getKahvilaitteet(Model model) {
        List<Tuote> kahvilaitteet = tuoteService.getKahvilaitteet(1L); // Fetch products for osasto ID 1
        model.addAttribute("kahvilaitteet", kahvilaitteet);
        return "kahvilaitteet";
    }
}
