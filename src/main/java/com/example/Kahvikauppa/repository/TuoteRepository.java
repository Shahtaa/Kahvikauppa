package com.example.Kahvikauppa.repository;

import com.example.Kahvikauppa.model.Tuote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TuoteRepository extends JpaRepository<Tuote, Long> {
}
