package com.example.Kahvikauppa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Osasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Changed the field name to 'id' and its type to Long
    private String nimi; // No changes needed for the 'nimi' field
    private Long osastoIDP; // Added a new field 'osastoIDP' of type Long
}
