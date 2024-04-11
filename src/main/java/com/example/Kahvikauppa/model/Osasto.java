package com.example.Kahvikauppa.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Osasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long osastoIDP;

    private String nimi;

    @OneToMany(mappedBy = "osasto")
    private List<Tuote> tuotteet;
}
