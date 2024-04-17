package com.example.Kahvikauppa.repository;

import com.example.Kahvikauppa.model.Tuote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TuoteRepository extends JpaRepository<Tuote, Long> {

    @Query("SELECT t FROM Tuote t WHERE t.osasto.id = :osastoID OR t.osasto.osastoIDP = :osastoID")
    List<Tuote> findProductsByOsastoID(@Param("osastoID") Long osastoID);
}