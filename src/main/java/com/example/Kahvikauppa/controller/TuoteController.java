package com.example.Kahvikauppa.controller;

import com.example.Kahvikauppa.model.Tuote;
import com.example.Kahvikauppa.service.TuoteService;
import com.example.Kahvikauppa.service.ValmistajaService;
import com.example.Kahvikauppa.service.ToimittajaService;
import com.example.Kahvikauppa.service.OsastoService; // Import OsastoService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class TuoteController {

    private final TuoteService tuoteService;
    private final ValmistajaService valmistajaService;
    private final ToimittajaService toimittajaService;
    private final OsastoService osastoService; // Inject OsastoService

    @Autowired
    public TuoteController(TuoteService tuoteService, ValmistajaService valmistajaService,
            ToimittajaService toimittajaService, OsastoService osastoService) {
        this.tuoteService = tuoteService;
        this.valmistajaService = valmistajaService;
        this.toimittajaService = toimittajaService;
        this.osastoService = osastoService; // Initialize OsastoService
    }

    @GetMapping("/tuotteet")
    public String tuotteet(Model model) {
        model.addAttribute("tuotteet", tuoteService.findAllTuotteet());
        model.addAttribute("valmistajat", valmistajaService.findAllValmistajat());
        model.addAttribute("toimittajat", toimittajaService.findAllToimittajat());
        model.addAttribute("osastot", osastoService.findAllOsastot()); // Add osastot to the model
        return "tuotteet";
    }

    @PostMapping("/lisaa-tuote")
    public String lisaaTuote(@ModelAttribute Tuote tuote, @RequestParam("kuva") MultipartFile file) throws IOException {
        tuoteService.saveTuote(tuote, file);
        return "redirect:/tuotteet";
    }

    @PostMapping("/poista-tuote/{id}")
    public String poistaTuote(@PathVariable Long id) {
        tuoteService.deleteTuote(id);
        return "redirect:/tuotteet";
    }

    @GetMapping("/productImage/{id}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long id) {
        Tuote product = tuoteService.findTuoteById(id);
        if (product != null && product.getTuotekuva() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(product.getTuotekuva(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Add other TuoteController endpoints here
}
