package com.webflow.fraisdeformation.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant unique de l'utilisateur

    @Column(nullable = false, unique = true)
    private String nomUtilisateur; // Nom d'utilisateur unique

    @Column(nullable = false)
    private String motDePasse; // Mot de passe de l'utilisateur

    @Column(nullable = false, unique = true)
    private String email; // Adresse e-mail de l'utilisateur

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "utilisateur_roles", // Table intermédiaire pour la relation ManyToMany
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>(); // Les rôles attribués à l'utilisateur

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

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<DeclarationFrais> getDeclarations() {
        return declarations;
    }

    public void setDeclarations(Set<DeclarationFrais> declarations) {
        this.declarations = declarations;
    }

    // Méthode pour ajouter un rôle
    public void ajouterRole(Role role) {
        this.roles.add(role);
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
