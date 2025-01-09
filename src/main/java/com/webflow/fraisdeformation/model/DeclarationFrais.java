package com.webflow.fraisdeformation.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class DeclarationFrais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant unique de la déclaration

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "L'utilisateur est obligatoire")
    private Utilisateur utilisateur; // Référence vers l'utilisateur

    @ManyToOne
    @JoinColumn(name = "formation_id", nullable = false)
    @NotNull(message = "La formation est obligatoire")
    private Formation formation; // Référence vers la formation

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Le statut est obligatoire")
    private StatutDeclaration status; // Statut de la déclaration

    @NotNull(message = "La date de création est obligatoire")
    private LocalDateTime dateCreation; // Date de création de la déclaration

    @OneToMany(mappedBy = "declaration", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Frais> frais = new HashSet<>(); // Liste des frais associés

    @OneToOne(mappedBy = "declaration", cascade = CascadeType.ALL, orphanRemoval = true)
    private CoordonneesBancaires coordonneesBancaires; // Coordonnées bancaires associées

    // Constructeur sans paramètre (obligatoire pour JPA)
    public DeclarationFrais() {
    }

    // Constructeur avec paramètres
    public DeclarationFrais(Utilisateur utilisateur, Formation formation, StatutDeclaration status, LocalDateTime dateCreation) {
        this.utilisateur = utilisateur;
        this.formation = formation;
        this.status = status;
        this.dateCreation = dateCreation;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public StatutDeclaration getStatus() {
        return status;
    }

    public void setStatus(StatutDeclaration status) {
        this.status = status;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Set<Frais> getFrais() {
        return frais;
    }

    public void setFrais(Set<Frais> frais) {
        this.frais = frais;
    }

    public CoordonneesBancaires getCoordonneesBancaires() {
        return coordonneesBancaires;
    }

    public void setCoordonneesBancaires(CoordonneesBancaires coordonneesBancaires) {
        this.coordonneesBancaires = coordonneesBancaires;
    }

    // Méthodes utilitaires
    public void ajouterFrais(Frais frais) {
        this.frais.add(frais);
        frais.setDeclaration(this);
    }

    public void supprimerFrais(Frais frais) {
        this.frais.remove(frais);
        frais.setDeclaration(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeclarationFrais that = (DeclarationFrais) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(utilisateur, that.utilisateur) &&
                Objects.equals(formation, that.formation) &&
                status == that.status &&
                Objects.equals(dateCreation, that.dateCreation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, utilisateur, formation, status, dateCreation);
    }
}
