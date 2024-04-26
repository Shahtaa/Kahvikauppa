package com.example.Kahvikauppa.controller;

import com.example.Kahvikauppa.model.Toimittaja;
import com.example.Kahvikauppa.service.ToimittajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/muokkaa-toimittaja/{id}")
    public String näytäMuokkauslomake(@PathVariable("id") String id, Model model) {
        try {
            Long toimittajaId = Long.parseLong(id);
            Toimittaja toimittaja = toimittajaService.findToimittajaById(toimittajaId);
            if (toimittaja == null) {
                return "redirect:/toimittajat";
            }
            model.addAttribute("toimittaja", toimittaja);
            return "muokkaa-toimittaja";
        } catch (NumberFormatException e) {
            return "redirect:/toimittajat";
        }
    }

    @PostMapping("/muokkaa-toimittaja/{id}")
    public String tallennaMuutokset(@PathVariable("id") Long id, @ModelAttribute("toimittaja") Toimittaja toimittaja) {
        toimittaja.setId(id);
        toimittajaService.saveToimittaja(toimittaja);
        return "redirect:/toimittajat";
    }

    @PostMapping("/poista-toimittaja/{id}")
    public String poistaToimittaja(@PathVariable("id") Long id) {
        toimittajaService.deleteToimittaja(id);
        return "redirect:/toimittajat";
    }
}
