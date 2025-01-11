package com.webflow.fraisdeformation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.webflow.fraisdeformation.model.CoordonneesBancaires;

@Repository
public interface CoordonneesBancairesRepository extends JpaRepository<CoordonneesBancaires, Long> {

    // Rechercher les coordonnées bancaires par l'ID de la déclaration
    CoordonneesBancaires findByDeclarationId(Long declarationId);
}
