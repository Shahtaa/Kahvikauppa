package com.example.Kahvikauppa.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Toimittaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nimi;
    private String yhteystieto;
    private String yhteyshenkilonEmail;

    @OneToMany(mappedBy = "toimittaja")
    private List<Tuote> tuotteet;
}
