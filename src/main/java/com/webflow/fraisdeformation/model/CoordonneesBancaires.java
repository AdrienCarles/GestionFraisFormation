package com.webflow.fraisdeformation.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class CoordonneesBancaires {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant unique des coordonnées bancaires

    @OneToOne
    @JoinColumn(name = "declaration_id", nullable = false)
    private DeclarationFrais declaration; // Référence vers la déclaration associée

    @NotNull(message = "L'IBAN est obligatoire")
    @Size(min = 15, max = 34, message = "L'IBAN doit contenir entre 15 et 34 caractères")
    @Pattern(regexp = "[A-Z0-9]*", message = "L'IBAN doit contenir uniquement des lettres majuscules et des chiffres")
    private String iban; // IBAN du compte bancaire

    @NotNull(message = "Le BIC est obligatoire")
    @Size(min = 8, max = 11, message = "Le BIC doit contenir entre 8 et 11 caractères")
    private String bic; // BIC de la banque

    @NotNull(message = "Le nom du titulaire est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom du titulaire doit contenir entre 2 et 100 caractères")
    private String titulaire; // Nom du titulaire du compte

    // Constructeur sans paramètre (obligatoire pour JPA)
    public CoordonneesBancaires() {
    }

    // Constructeur avec paramètres
    public CoordonneesBancaires(DeclarationFrais declaration, String iban, String bic, String titulaire) {
        this.declaration = declaration;
        this.iban = iban;
        this.bic = bic;
        this.titulaire = titulaire;
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

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getTitulaire() {
        return titulaire;
    }

    public void setTitulaire(String titulaire) {
        this.titulaire = titulaire;
    }

    // toString pour le débogage
    @Override
    public String toString() {
        return "CoordonneesBancaires{" +
                "id=" + id +
                ", iban='" + iban + '\'' +
                ", bic='" + bic + '\'' +
                ", titulaire='" + titulaire + '\'' +
                '}';
    }

    // equals et hashCode pour les comparaisons
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordonneesBancaires that = (CoordonneesBancaires) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(iban, that.iban) &&
                Objects.equals(bic, that.bic) &&
                Objects.equals(titulaire, that.titulaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, iban, bic, titulaire);
    }
}
