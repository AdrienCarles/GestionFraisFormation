package com.webflow.fraisdeformation.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class Frais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant unique du frais

    @ManyToOne
    @JoinColumn(name = "declaration_id", nullable = false) // clé étrangère vers la déclaration
    private DeclarationFrais declaration; // Référence vers la déclaration de frais

    @NotNull
    @Size(min = 2, max = 50)
    private String type; // Type de frais (ex : Transport, Repas)

    @NotNull
    private Double montant; // Montant du frais

    @Lob
    private byte[] justificatif; // Justificatif sous forme de fichier binaire (ex : image, PDF)

    // Constructeur sans paramètre (obligatoire pour JPA)
    public Frais() {
    }

    // Constructeur avec paramètres
    public Frais(DeclarationFrais declaration, String type, Double montant, byte[] justificatif) {
        this.declaration = declaration;
        this.type = type;
        this.montant = montant;
        this.justificatif = justificatif;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeclarationFrais getDeclaration() {
        return declaration;
    }

    public void setDeclaration(DeclarationFrais declaration) {
        this.declaration = declaration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public byte[] getJustificatif() {
        return justificatif;
    }

    public void setJustificatif(byte[] justificatif) {
        this.justificatif = justificatif;
    }

    public void mettreAJourJustificatif(byte[] nouveauJustificatif) {
        this.justificatif = nouveauJustificatif;
    }

    // equals et hashCode pour éviter des problèmes lors de l'utilisation dans des collections
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frais frais = (Frais) o;
        return Objects.equals(id, frais.id) &&
                Objects.equals(type, frais.type) &&
                Objects.equals(montant, frais.montant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, montant);
    }
}
