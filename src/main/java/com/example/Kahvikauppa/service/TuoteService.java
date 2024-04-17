package com.example.Kahvikauppa.service;

import com.example.Kahvikauppa.model.Tuote;
import com.example.Kahvikauppa.repository.TuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

    public void saveTuote(Tuote tuote, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            tuote.setTuotekuva(file.getBytes());
            tuoteRepository.save(tuote);
        } else {
            throw new IllegalArgumentException("File is empty or null");
        }
    }

    public List<Tuote> getKahvilaitteet(Long osastoID) {
        return tuoteRepository.findProductsByOsastoID(osastoID);
    }

    public void deleteTuote(Long tuoteId) {
        tuoteRepository.deleteById(tuoteId);
    }

    public Tuote findTuoteById(Long id) {
        return tuoteRepository.findById(id).orElse(null);
    }
}
