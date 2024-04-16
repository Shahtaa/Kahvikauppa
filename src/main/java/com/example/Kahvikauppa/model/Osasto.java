package com.example.Kahvikauppa.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Osasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Reference to the parent osasto
    private Long osastoIDP;

    private String nimi;

    // Define the one-to-many relationship with Tuote
    @OneToMany(mappedBy = "osasto")
    private List<Tuote> tuotteet = new ArrayList<>();
}
