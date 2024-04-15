package com.example.Kahvikauppa.repository;

import com.example.Kahvikauppa.model.Tuote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TuoteRepository extends JpaRepository<Tuote, Long> {
    List<Tuote> findAllByOsastoIdIn(List<Long> osastoIds);
}
