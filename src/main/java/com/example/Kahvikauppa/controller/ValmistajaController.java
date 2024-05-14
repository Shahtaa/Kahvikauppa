package com.example.Kahvikauppa.controller;

import com.example.Kahvikauppa.model.Valmistaja;
import com.example.Kahvikauppa.service.ValmistajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/muokkaa-valmistaja/{id}")
    public String muokkaaValmistaja(@PathVariable("id") Long id, Model model) {
        Valmistaja valmistaja = valmistajaService.findValmistajaById(id);
        if (valmistaja == null) {
            return "redirect:/valmistajat";
        }
        model.addAttribute("valmistaja", valmistaja);
        return "muokkaa-valmistaja";
    }

    @PostMapping("/muokkaa-valmistaja/{id}")
    public String tallennaMuutokset(@PathVariable("id") Long id, @ModelAttribute("valmistaja") Valmistaja valmistaja) {
        valmistaja.setId(id);
        valmistajaService.saveValmistaja(valmistaja);
        return "redirect:/valmistajat";
    }

    @PostMapping("/poista-valmistaja/{id}")
    public String poistaValmistaja(@PathVariable("id") Long id, Model model) {
        Valmistaja valmistaja = valmistajaService.findValmistajaById(id);
        if (valmistaja != null && !valmistaja.getTuotteet().isEmpty()) {
            model.addAttribute("error", "Valmistajalla on liitettyjä tuotteita. Poista ensin tuotteet.");
            model.addAttribute("valmistajat", valmistajaService.findAllValmistajat());
            return "valmistajat";
        }
        valmistajaService.deleteValmistaja(id);
        return "redirect:/valmistajat";
    }

}
