package com.example.Kahvikauppa.service;

import com.example.Kahvikauppa.model.Toimittaja;
import com.example.Kahvikauppa.repository.ToimittajaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToimittajaService {

    private final ToimittajaRepository toimittajaRepository;

    @Autowired
    public ToimittajaService(ToimittajaRepository toimittajaRepository) {
        this.toimittajaRepository = toimittajaRepository;
    }

    public List<Toimittaja> findAllToimittajat() {
        return toimittajaRepository.findAll();
    }

    public void saveToimittaja(Toimittaja toimittaja) {
        toimittajaRepository.save(toimittaja);
    }

    public Toimittaja findToimittajaById(Long id) {
        Optional<Toimittaja> optionalToimittaja = toimittajaRepository.findById(id);
        return optionalToimittaja.orElse(null);
    }

    public void deleteToimittaja(Long id) {
        toimittajaRepository.deleteById(id);
    }
}
