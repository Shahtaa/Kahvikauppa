package com.example.Kahvikauppa.service;

import com.example.Kahvikauppa.model.Tuote;
import com.example.Kahvikauppa.repository.TuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TuoteService {

    private final TuoteRepository tuoteRepository;

    @Autowired
    public TuoteService(TuoteRepository tuoteRepository) {
        this.tuoteRepository = tuoteRepository;
    }

    public List<Tuote> findAllTuotteet() {
        return tuoteRepository.findAll();
    }

    public void saveTuote(Tuote tuote) {
        tuoteRepository.save(tuote);
    }

    public List<Tuote> getKahvilaitteet() {
        List<Tuote> allTuotteet = tuoteRepository.findAll();
        // Filter products with OSASTO_ID 1, 3, 4, and 5
        return allTuotteet.stream()
                .filter(tuote -> tuote.getOsasto().getId() == 1 ||
                        tuote.getOsasto().getId() == 3 ||
                        tuote.getOsasto().getId() == 4 ||
                        tuote.getOsasto().getId() == 5)
                .collect(Collectors.toList());
    }

    public void deleteTuote(Long tuoteId) {
        tuoteRepository.deleteById(tuoteId);
    }

    public Tuote findTuoteById(Long id) {
        return tuoteRepository.findById(id).orElse(null);
    }

}
