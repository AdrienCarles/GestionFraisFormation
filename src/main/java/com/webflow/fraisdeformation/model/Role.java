package com.webflow.fraisdeformation.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant du rôle

    @Column(unique = true, nullable = false)
    private String name; // Nom du rôle (ex : ADMIN, PROFESSEUR)

    @ManyToMany(mappedBy = "roles")
    private Set<Utilisateur> utilisateurs; // Liste des utilisateurs ayant ce rôle

    // Getters et setters
}
