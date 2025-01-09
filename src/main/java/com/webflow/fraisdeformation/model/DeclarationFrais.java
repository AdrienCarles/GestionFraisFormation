package com.webflow.fraisdeformation.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class DeclarationFrais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Utilisateur utilisateur; // Référence vers l'utilisateur

    @ManyToOne
    @JoinColumn(name = "formation_id", nullable = false)
    private Formation formation; // Référence vers la formation

    private String status; // Statut de la déclaration (BROUILLON, EN_COURS...)

    private LocalDateTime dateCreation;

    @OneToMany(mappedBy = "declaration", cascade = CascadeType.ALL)
    private Set<Frais> frais; // Liste des frais associés

    @OneToOne(mappedBy = "declaration", cascade = CascadeType.ALL)
    private CoordonneesBancaires coordonneesBancaires; // Coordonnées bancaires associées

    // Getters et setters
}

