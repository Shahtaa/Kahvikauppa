package com.example.Kahvikauppa.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Valmistaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nimi;
    private String url;

    @OneToMany(mappedBy = "valmistaja")
    private List<Tuote> tuotteet;
}
