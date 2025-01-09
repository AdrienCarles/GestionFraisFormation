package com.webflow.fraisdeformation.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant unique de la formation

    @NotNull(message = "La date de la formation est obligatoire")
    private LocalDate dateFormation; // Date de la formation

    @NotNull(message = "Le lieu est obligatoire")
    @Size(min = 2, max = 100, message = "Le lieu doit contenir entre 2 et 100 caractères")
    private String lieu; // Lieu de la formation

    @NotNull(message = "L'intitulé est obligatoire")
    @Size(min = 2, max = 200, message = "L'intitulé doit contenir entre 2 et 200 caractères")
    private String intitule; // Intitulé de la formation

    @OneToMany(mappedBy = "formation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DeclarationFrais> declarations = new HashSet<>(); // Liste des déclarations liées à la formation

    // Constructeur sans paramètre
    public Formation() {
    }

    // Constructeur avec paramètres
    public Formation(LocalDate dateFormation, String lieu, String intitule) {
        this.dateFormation = dateFormation;
        this.lieu = lieu;
        this.intitule = intitule;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateFormation() {
        return dateFormation;
    }

    public void setDateFormation(LocalDate dateFormation) {
        this.dateFormation = dateFormation;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Set<DeclarationFrais> getDeclarations() {
        return declarations;
    }

    public void setDeclarations(Set<DeclarationFrais> declarations) {
        this.declarations = declarations;
    }

    // Méthode pour ajouter une déclaration
    public void ajouterDeclaration(DeclarationFrais declarationFrais) {
        declarations.add(declarationFrais);
        declarationFrais.setFormation(this);
    }

    // Méthode pour supprimer une déclaration
    public void supprimerDeclaration(DeclarationFrais declarationFrais) {
        declarations.remove(declarationFrais);
        declarationFrais.setFormation(null);
    }

    // equals et hashCode pour les comparaisons
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Formation formation = (Formation) o;
        return Objects.equals(id, formation.id) &&
                Objects.equals(dateFormation, formation.dateFormation) &&
                Objects.equals(lieu, formation.lieu) &&
                Objects.equals(intitule, formation.intitule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateFormation, lieu, intitule);
    }
}
