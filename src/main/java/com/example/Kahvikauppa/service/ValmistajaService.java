package com.example.Kahvikauppa.service;

import com.example.Kahvikauppa.model.Valmistaja;
import com.example.Kahvikauppa.repository.ValmistajaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ValmistajaService {

    private final ValmistajaRepository valmistajaRepository;

    @Autowired
    public ValmistajaService(ValmistajaRepository valmistajaRepository) {
        this.valmistajaRepository = valmistajaRepository;
    }

    public List<Valmistaja> findAllValmistajat() {
        return valmistajaRepository.findAll();
    }

    public Valmistaja findValmistajaById(Long id) {
        return valmistajaRepository.findById(id).orElse(null);
    }

    public void saveValmistaja(Valmistaja valmistaja) {
        valmistajaRepository.save(valmistaja);
    }

    public void deleteValmistaja(Long id) {
        valmistajaRepository.deleteById(id);
    }

    public void updateValmistaja(Long id, Valmistaja valmistaja) {
        Optional<Valmistaja> existingValmistajaOptional = valmistajaRepository.findById(id);
        if (existingValmistajaOptional.isPresent()) {
            Valmistaja existingValmistaja = existingValmistajaOptional.get();
            existingValmistaja.setNimi(valmistaja.getNimi());
            existingValmistaja.setUrl(valmistaja.getUrl());
            valmistajaRepository.save(existingValmistaja);
        } else {
            // Handle the case where the Valmistaja with the given id is not found
            throw new IllegalArgumentException("Valmistaja not found with id: " + id);
        }
    }
}
