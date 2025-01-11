package com.webflow.fraisdeformation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.webflow.fraisdeformation.model.Frais;

import java.util.List;

@Repository
public interface FraisRepository extends JpaRepository<Frais, Long> {

    // Récupérer tous les frais d'une déclaration donnée
    List<Frais> findByDeclarationId(Long declarationId);
}
