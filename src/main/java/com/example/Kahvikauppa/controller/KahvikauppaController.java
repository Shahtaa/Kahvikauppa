package com.example.Kahvikauppa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.Kahvikauppa.model.Osasto;
import com.example.Kahvikauppa.model.Tuote;
import com.example.Kahvikauppa.model.Toimittaja;
import com.example.Kahvikauppa.model.Valmistaja; // Import the Valmistaja class
import com.example.Kahvikauppa.repository.OsastoRepository;
import com.example.Kahvikauppa.repository.TuoteRepository;
import com.example.Kahvikauppa.repository.ToimittajaRepository;
import com.example.Kahvikauppa.repository.ValmistajaRepository; // Import the ValmistajaRepository class

@Controller
public class KahvikauppaController {

    private final OsastoRepository osastoRepository;
    private final TuoteRepository tuoteRepository;
    private final ToimittajaRepository toimittajaRepository;
    private final ValmistajaRepository valmistajaRepository; // Inject ValmistajaRepository

    @Autowired
    public KahvikauppaController(OsastoRepository osastoRepository, TuoteRepository tuoteRepository,
            ToimittajaRepository toimittajaRepository, ValmistajaRepository valmistajaRepository) { // Update
                                                                                                    // constructor to
                                                                                                    // include
                                                                                                    // ValmistajaRepository
        this.osastoRepository = osastoRepository;
        this.tuoteRepository = tuoteRepository;
        this.toimittajaRepository = toimittajaRepository;
        this.valmistajaRepository = valmistajaRepository; // Initialize ValmistajaRepository
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
        model.addAttribute("toimittajat", toimittajaRepository.findAll());
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
    public String toimittajat(Model model) {
        model.addAttribute("toimittajat", toimittajaRepository.findAll());
        return "toimittajat";
    }

    @PostMapping("/lisaa-toimittaja")
    public String lisaaToimittaja(@ModelAttribute Toimittaja toimittaja) {
        toimittajaRepository.save(toimittaja);
        return "redirect:/toimittajat";
    }

    @GetMapping("/muokkaa-toimittaja/{id}")
    public String muokkaaToimittaja(@PathVariable Long id, Model model) {
        Toimittaja toimittaja = toimittajaRepository.findById(id).orElse(null);
        if (toimittaja != null) {
            model.addAttribute("toimittaja", toimittaja);
            return "muokkaa-toimittaja";
        } else {
            return "redirect:/toimittajat";
        }
    }

    @PostMapping("/paivita-toimittaja")
    public String paivitaToimittaja(@ModelAttribute Toimittaja toimittaja) {
        toimittajaRepository.save(toimittaja);
        return "redirect:/toimittajat";
    }

    @GetMapping("/poista-toimittaja/{id}")
    public String poistaToimittaja(@PathVariable Long id) {
        toimittajaRepository.deleteById(id);
        return "redirect:/toimittajat";
    }

    @GetMapping("/valmistajat")
    public String valmistajat(Model model) {
        model.addAttribute("valmistajat", valmistajaRepository.findAll());
        return "valmistajat";
    }

    @PostMapping("/lisaa-valmistaja")
    public String lisaaValmistaja(@ModelAttribute Valmistaja valmistaja) {
        valmistajaRepository.save(valmistaja);
        return "redirect:/valmistajat";
    }

    @GetMapping("/muokkaa-valmistaja/{id}")
    public String muokkaaValmistaja(@PathVariable Long id, Model model) {
        Valmistaja valmistaja = valmistajaRepository.findById(id).orElse(null);
        if (valmistaja != null) {
            model.addAttribute("valmistaja", valmistaja);
            return "muokkaa-valmistaja";
        } else {
            return "redirect:/valmistajat";
        }
    }

    @PostMapping("/paivita-valmistaja")
    public String paivitaValmistaja(@ModelAttribute Valmistaja valmistaja) {
        valmistajaRepository.save(valmistaja);
        return "redirect:/valmistajat";
    }

    @GetMapping("/poista-valmistaja/{id}")
    public String poistaValmistaja(@PathVariable Long id) {
        valmistajaRepository.deleteById(id);
        return "redirect:/valmistajat";
    }
}
