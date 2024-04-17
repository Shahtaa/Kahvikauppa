package com.example.Kahvikauppa.controller;

import com.example.Kahvikauppa.model.Tuote;
import com.example.Kahvikauppa.service.TuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Controller
public class KulutustuotteetController {

    private final TuoteService tuoteService;

    @Autowired
    public KulutustuotteetController(TuoteService tuoteService) {
        this.tuoteService = tuoteService;
    }

    @GetMapping("/kulutustuotteet")
    public String getKulutustuotteet(Model model) {
        List<Tuote> osasto2Tuotteet = tuoteService.getKahvilaitteet(2L); // Fetch products for osasto ID 2

        List<Tuote> osasto7Tuotteet = tuoteService.getKahvilaitteet(7L);
        // Yhdistetään
        osasto2Tuotteet.addAll(osasto7Tuotteet);

        Set<Tuote> yhdistetytTuotteet = new LinkedHashSet<>(osasto2Tuotteet);

        model.addAttribute("kulutustuotteet", new ArrayList<>(yhdistetytTuotteet));
        return "kulutustuotteet";
    }
}
