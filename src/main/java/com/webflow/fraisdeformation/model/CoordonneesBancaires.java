package com.webflow.fraisdeformation.model;

import javax.persistence.*;

@Entity
public class CoordonneesBancaires {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "declaration_id", nullable = false)
    private DeclarationFrais declaration; // Référence vers la déclaration

    private String iban;

    private String bic;

    private String titulaire; // Nom du titulaire du compte

    // Getters et setters
}
