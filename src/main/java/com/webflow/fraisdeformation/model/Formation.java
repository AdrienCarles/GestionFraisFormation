package com.webflow.fraisdeformation.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateFormation;

    private String lieu;

    private String intitule;

    @OneToMany(mappedBy = "formation")
    private Set<DeclarationFrais> declarations; // Liste des déclarations liées

    // Getters et setters
}
