package com.example.Kahvikauppa.controller;

import com.example.Kahvikauppa.model.Osasto;
import com.example.Kahvikauppa.service.OsastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OsastoController {

    private final OsastoService osastoService;

    @Autowired
    public OsastoController(OsastoService osastoService) {
        this.osastoService = osastoService;
    }

    @GetMapping("/osastot")
    public String osastot(Model model) {
        List<Osasto> osastot = osastoService.findAllOsastot();
        model.addAttribute("osastot", osastot);
        return "osastot";
    }

    @PostMapping("/lisaa-osasto")
    public String lisaaOsasto(@ModelAttribute Osasto osasto) {
        osastoService.saveOsasto(osasto);
        return "redirect:/osastot";
    }
}
