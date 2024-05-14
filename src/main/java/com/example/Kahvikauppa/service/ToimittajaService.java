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

    public Toimittaja findToimittajaById(Long id) {
        return toimittajaRepository.findById(id).orElse(null);
    }

    public void saveToimittaja(Toimittaja toimittaja) {
        toimittajaRepository.save(toimittaja);
    }

    public void deleteToimittaja(Long id) {
        toimittajaRepository.deleteById(id);
    }
}
