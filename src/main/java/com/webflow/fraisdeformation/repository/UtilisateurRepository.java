package com.webflow.fraisdeformation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.webflow.fraisdeformation.model.Utilisateur;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    // Rechercher un utilisateur par son nom d'utilisateur
    Optional<Utilisateur> findByNomUtilisateur(String nomUtilisateur);

    // Vérifier si un email existe déjà dans la base
    boolean existsByEmail(String email);

    // Rechercher un utilisateur par son email
    Optional<Utilisateur> findByEmail(String email);
}
