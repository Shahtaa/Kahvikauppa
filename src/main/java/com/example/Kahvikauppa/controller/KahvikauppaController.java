package com.example.Kahvikauppa.controller;

import com.example.Kahvikauppa.model.Osasto;
import com.example.Kahvikauppa.repository.OsastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class KahvikauppaController {

    private final OsastoRepository osastoRepository;

    @Autowired
    public KahvikauppaController(OsastoRepository osastoRepository) {
        this.osastoRepository = osastoRepository;
    }

    @GetMapping("/")
    public String etusivu() {
        return "index";
    }

    @GetMapping("/osastot")
    public String osastot(Model model) {
        model.addAttribute("osasto", new Osasto());
        return "osastot";
    }

    @PostMapping("/lisaa-osasto")
    public String lisaaOsasto(@ModelAttribute Osasto osasto) {
        // Tallenna osasto tietokantaan
        osastoRepository.save(osasto);
        return "redirect:/osastot"; // Ohjaa takaisin osastot-sivulle
    }

    @GetMapping("/kahvilaitteet")
    public String kahvilaitteet() {
        return "kahvilaitteet";
    }

    @GetMapping("/kulutustuotteet")
    public String kulutustuotteet() {
        return "kulutustuotteet";
    }
}
