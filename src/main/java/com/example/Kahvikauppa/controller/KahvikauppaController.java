package com.example.Kahvikauppa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.Kahvikauppa.model.Osasto;
import com.example.Kahvikauppa.model.Tuote;
import com.example.Kahvikauppa.repository.OsastoRepository;
import com.example.Kahvikauppa.repository.TuoteRepository;

@Controller
public class KahvikauppaController {

    private final OsastoRepository osastoRepository;
    private final TuoteRepository tuoteRepository;

    @Autowired
    public KahvikauppaController(OsastoRepository osastoRepository, TuoteRepository tuoteRepository) {
        this.osastoRepository = osastoRepository;
        this.tuoteRepository = tuoteRepository;
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

    @GetMapping("/tuotteet")
    public String tuotteet(Model model) {
        model.addAttribute("tuotteet", tuoteRepository.findAll());
        model.addAttribute("osastot", osastoRepository.findAll());
        return "tuotteet";
    }

    @PostMapping("/lisaa-tuote")
    public String lisaaTuote(@ModelAttribute Tuote tuote) {
        tuoteRepository.save(tuote);
        return "redirect:/tuotteet";
    }

    @GetMapping("/muokkaa-tuote/{id}")
    public String muokkaaTuote(@PathVariable Long id, Model model) {
        Tuote tuote = tuoteRepository.findById(id).orElse(null);
        if (tuote != null) {
            model.addAttribute("tuote", tuote);
            return "muokkaa-tuote";
        } else {
            return "redirect:/tuotteet";
        }
    }

    @PostMapping("/paivita-tuote")
    public String paivitaTuote(@ModelAttribute Tuote tuote) {
        tuoteRepository.save(tuote);
        return "redirect:/tuotteet";
    }

    @GetMapping("/poista-tuote/{id}")
    public String poistaTuote(@PathVariable Long id) {
        tuoteRepository.deleteById(id);
        return "redirect:/tuotteet";
    }

    @GetMapping("/toimittajat")
    public String toimittajat() {
        return "toimittajat";
    }

    @GetMapping("/valmistajat")
    public String valmistajat() {
        return "valmistajat";
    }
}
