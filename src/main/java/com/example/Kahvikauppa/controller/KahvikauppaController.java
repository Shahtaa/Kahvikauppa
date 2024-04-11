package com.example.Kahvikauppa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.Kahvikauppa.model.Osasto;
import com.example.Kahvikauppa.repository.OsastoRepository;

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
        model.addAttribute("osastot", osastoRepository.findAll());
        return "osastot";
    }

    @PostMapping("/lisaa-osasto")
    public String lisaaOsasto(@ModelAttribute Osasto osasto) {
        osastoRepository.save(osasto);
        return "redirect:/osastot";
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
