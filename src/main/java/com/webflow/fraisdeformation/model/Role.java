package com.webflow.fraisdeformation.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant du rôle

    @Column(unique = true, nullable = false)
    private String name; // Nom du rôle (ex : ADMIN, PROFESSEUR)

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<Utilisateur> utilisateurs = new HashSet<>(); // Liste des utilisateurs ayant ce rôle

    // Constructeur sans paramètre (obligatoire pour JPA)
    public Role() {
    }

    // Constructeur avec paramètre
    public Role(String name) {
        this.name = name;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    // Méthode pour ajouter un utilisateur
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        this.utilisateurs.add(utilisateur);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
