package com.example.Kahvikauppa.controller;

import com.example.Kahvikauppa.model.Toimittaja;
import com.example.Kahvikauppa.service.ToimittajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ToimittajaController {

    private final ToimittajaService toimittajaService;

    @Autowired
    public ToimittajaController(ToimittajaService toimittajaService) {
        this.toimittajaService = toimittajaService;
    }

    @GetMapping("/toimittajat")
    public String toimittajat(Model model) {
        model.addAttribute("toimittajat", toimittajaService.findAllToimittajat());
        return "toimittajat";
    }

    @PostMapping("/lisaa-toimittaja")
    public String lisaaToimittaja(@ModelAttribute Toimittaja toimittaja) {
        toimittajaService.saveToimittaja(toimittaja);
        return "redirect:/toimittajat";
    }

    // Add other ToimittajaController endpoints here
}
