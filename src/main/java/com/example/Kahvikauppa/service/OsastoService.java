package com.example.Kahvikauppa.service;

import com.example.Kahvikauppa.model.Osasto;
import com.example.Kahvikauppa.repository.OsastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OsastoService {

    private final OsastoRepository osastoRepository;

    @Autowired
    public OsastoService(OsastoRepository osastoRepository) {
        this.osastoRepository = osastoRepository;
    }

    public List<Osasto> findAllOsastot() {
        return osastoRepository.findAll();
    }

    public void saveOsasto(Osasto osasto) {
        osastoRepository.save(osasto);
    }

    // Add other methods as needed
}
