package com.example.Kahvikauppa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Kahvikauppa.model.Tuote;

public interface TuoteRepository extends JpaRepository<Tuote, Long> {
    List<Tuote> findByOsastoIdIn(List<Long> osastoIds);
}
