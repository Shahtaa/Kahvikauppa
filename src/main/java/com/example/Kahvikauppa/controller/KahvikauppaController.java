package com.example.Kahvikauppa.controller;

import org.springframework.http.MediaType;
import com.example.Kahvikauppa.model.Osasto;
import com.example.Kahvikauppa.model.Tuote;
import com.example.Kahvikauppa.model.Toimittaja;
import com.example.Kahvikauppa.model.Valmistaja;
import com.example.Kahvikauppa.service.OsastoService;
import com.example.Kahvikauppa.service.TuoteService;
import com.example.Kahvikauppa.service.ToimittajaService;
import com.example.Kahvikauppa.service.ValmistajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
public class KahvikauppaController {

    private final OsastoService osastoService;
    private final TuoteService tuoteService;
    private final ToimittajaService toimittajaService;
    private final ValmistajaService valmistajaService;

    @Autowired
    public KahvikauppaController(OsastoService osastoService, TuoteService tuoteService,
            ToimittajaService toimittajaService, ValmistajaService valmistajaService) {
        this.osastoService = osastoService;
        this.tuoteService = tuoteService;
        this.toimittajaService = toimittajaService;
        this.valmistajaService = valmistajaService;
    }

    @GetMapping("/")
    public String etusivu() {
        return "index";
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

    @GetMapping("/kahvilaitteet")
    public String getKahvilaitteet(Model model) {
        List<Tuote> kahvilaitteet = tuoteService.getKahvilaitteet(1L); // Fetch products for osasto ID 1
        model.addAttribute("kahvilaitteet", kahvilaitteet);
        return "kahvilaitteet";
    }

    @GetMapping("/kulutustuotteet")
    public String kulutustuotteet() {
        return "kulutustuotteet";
    }

    @GetMapping("/tuotteet")
    public String tuotteet(Model model) {
        model.addAttribute("tuotteet", tuoteService.findAllTuotteet());
        model.addAttribute("osastot", osastoService.findAllOsastot());
        model.addAttribute("toimittajat", toimittajaService.findAllToimittajat());
        model.addAttribute("valmistajat", valmistajaService.findAllValmistajat());
        return "tuotteet";
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

    @PostMapping("/lisaa-tuote")
    public String lisaaTuote(@ModelAttribute Tuote tuote, @RequestParam("kuva") MultipartFile file) throws IOException {
        // Save the Tuote object along with the file content
        tuoteService.saveTuote(tuote, file);

        return "redirect:/tuotteet";
    }

    @GetMapping("/muokkaa-tuote/{id}")
    public String muokkaaTuote(@PathVariable Long id, Model model) {
        Tuote tuote = tuoteService.findTuoteById(id);
        if (tuote != null) {
            model.addAttribute("tuote", tuote);
            return "muokkaa-tuote";
        } else {
            return "redirect:/tuotteet";
        }
    }

    @PostMapping("/paivita-tuote")
    public String paivitaTuote(@ModelAttribute Tuote tuote) {
        tuoteService.saveTuote(tuote);
        return "redirect:/tuotteet";
    }

    @PostMapping("/poista-tuote/{id}")
    public String poistaTuote(@PathVariable Long id) {
        tuoteService.deleteTuote(id);
        return "redirect:/tuotteet";
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
    public String muokkaaToimittaja(@PathVariable Long id, Model model) {
        Toimittaja toimittaja = toimittajaService.findToimittajaById(id);
        if (toimittaja != null) {
            model.addAttribute("toimittaja", toimittaja);
            return "muokkaa-toimittaja";
        } else {
            return "redirect:/toimittajat";
        }
    }

    @PostMapping("/paivita-toimittaja")
    public String paivitaToimittaja(@ModelAttribute Toimittaja toimittaja) {
        toimittajaService.saveToimittaja(toimittaja);
        return "redirect:/toimittajat";
    }

    @PostMapping("/poista-toimittaja/{id}")
    public String poistaToimittaja(@PathVariable Long id) {
        toimittajaService.deleteToimittaja(id);
        return "redirect:/toimittajat";
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
    public String muokkaaValmistaja(@PathVariable Long id, Model model) {
        Valmistaja valmistaja = valmistajaService.findValmistajaById(id);
        if (valmistaja != null) {
            model.addAttribute("valmistaja", valmistaja);
            return "muokkaa-valmistaja";
        } else {
            return "redirect:/valmistajat";
        }
    }

    @PostMapping("/paivita-valmistaja")
    public String paivitaValmistaja(@ModelAttribute Valmistaja valmistaja) {
        valmistajaService.saveValmistaja(valmistaja);
        return "redirect:/valmistajat";
    }

    @PostMapping("/poista-valmistaja/{id}")
    public String poistaValmistaja(@PathVariable Long id) {
        valmistajaService.deleteValmistaja(id);
        return "redirect:/valmistajat";
    }
}