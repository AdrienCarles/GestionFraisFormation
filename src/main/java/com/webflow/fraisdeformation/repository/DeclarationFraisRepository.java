package com.webflow.fraisdeformation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.webflow.fraisdeformation.model.DeclarationFrais;

import java.util.List;

@Repository
public interface DeclarationFraisRepository extends JpaRepository<DeclarationFrais, Long> {

    // Récupérer toutes les déclarations d'un utilisateur spécifique
    List<DeclarationFrais> findByUtilisateurId(Long utilisateurId);

    // Récupérer les déclarations par statut
    List<DeclarationFrais> findByStatus(String status);

    // Récupérer les déclarations par formation
    List<DeclarationFrais> findByFormationId(Long formationId);
}
