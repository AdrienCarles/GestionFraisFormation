package com.webflow.fraisdeformation.model;

import javax.persistence.*;

@Entity
public class Frais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "declaration_id")
    private DeclarationFrais declaration; // Référence vers la déclaration

    private String type; // Type de frais (par exemple "Transport", "Repas")

    private Double montant;

    @Lob
    private byte[] justificatif; // Justificatif sous forme de fichier

    // Getters et setters
}
