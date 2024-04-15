package com.example.Kahvikauppa.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tuote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nimi;
    private String kuvaus;
    private BigDecimal hinta;

    @Lob
    private String tuotekuva; // Tallentaa tuotekuvan base64-muodossa

    @ManyToOne
    @JoinColumn(name = "toimittaja_id")
    private Toimittaja toimittaja;

    @ManyToOne
    @JoinColumn(name = "valmistaja_id")
    private Valmistaja valmistaja;

    @ManyToOne
    @JoinColumn(name = "osasto_id")
    private Osasto osasto;
}
