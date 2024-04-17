package com.example.Kahvikauppa.controller;

import com.example.Kahvikauppa.model.Valmistaja;
import com.example.Kahvikauppa.service.ValmistajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ValmistajaController {

    private final ValmistajaService valmistajaService;

    @Autowired
    public ValmistajaController(ValmistajaService valmistajaService) {
        this.valmistajaService = valmistajaService;
    }

    @GetMapping("/valmistajat")
    public String valmistajat(Model model) {
        model.addAttribute("valmistajat", valmistajaService.findAllValmistajat());
        return "valmistajat";
    }

    @PostMapping("/lisaa-valmistaja")
    public String lisaaValmistaja(@ModelAttribute Valmistaja valmistaja) {
        valmistajaService.saveValmistaja(valmistaja);
        return "redirect:/valmistajat";
    }

    // Add other ValmistajaController endpoints here
}
