package com.example.Kahvikauppa.service;

import com.example.Kahvikauppa.model.Tuote;
import com.example.Kahvikauppa.repository.TuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public void saveFileContent(MultipartFile file) throws IOException {
        // Check if the file is not empty
        if (!file.isEmpty()) {
            // Define the directory path where you want to save the file
            String directoryPath = "Kahvikauppa/src/main/resources/static/images";

            // Create a Path object for the directory
            Path directory = Paths.get(directoryPath);

            // If the directory doesn't exist, create it
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            // Get the original filename
            String originalFileName = file.getOriginalFilename();

            // Create a Path object for the file
            Path filePath = Paths.get(directoryPath, originalFileName);

            // Write the file content to the specified path
            Files.write(filePath, file.getBytes());
        }
    }

    public List<Tuote> getKahvilaitteet() {
        // Filter products with OSASTO_ID 1, 3, 4, and 5
        return tuoteRepository.findAllByOsastoIdIn(List.of(1L, 3L, 4L, 5L));
    }

    public void deleteTuote(Long tuoteId) {
        tuoteRepository.deleteById(tuoteId);
    }

    public Tuote findTuoteById(Long id) {
        return tuoteRepository.findById(id).orElse(null);
    }

}
