package com.webflow.fraisdeformation.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant unique de l'utilisateur

    @Column(nullable = false, unique = true)
    private String nomUtilisateur; // Nom d'utilisateur unique

    @Column(nullable = false)
    @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères.")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{8,}$",
            message = "Le mot de passe doit contenir au moins une majuscule, une minuscule et un chiffre.")
    private String motDePasse; // Mot de passe de l'utilisateur

    @Column(nullable = false, unique = true)
    private String email; // Adresse e-mail de l'utilisateur

    @Column(nullable = false)
    private int role;

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DeclarationFrais> declarations = new HashSet<>(); // Déclarations de frais de l'utilisateur

    public Utilisateur() {
    }

    // Constructeur avec paramètres
    public Utilisateur(String nomUtilisateur, String motDePasse, String email) {
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
        this.email = email;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getEmail() {
        return email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<DeclarationFrais> getDeclarations() {
        return declarations;
    }

    public void setDeclarations(Set<DeclarationFrais> declarations) {
        this.declarations = declarations;
    }

    // Méthode pour ajouter une déclaration de frais
    public void ajouterDeclaration(DeclarationFrais declaration) {
        this.declarations.add(declaration);
        declaration.setUtilisateur(this);
    }

    // Méthode pour supprimer une déclaration de frais
    public void supprimerDeclaration(DeclarationFrais declaration) {
        this.declarations.remove(declaration);
        declaration.setUtilisateur(null);
    }
}
