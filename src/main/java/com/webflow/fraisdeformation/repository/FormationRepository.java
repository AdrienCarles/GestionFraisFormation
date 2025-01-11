package com.webflow.fraisdeformation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.webflow.fraisdeformation.model.Formation;

import java.util.List;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Long> {

    // Rechercher une formation par son intitulé
    List<Formation> findByIntituleContainingIgnoreCase(String intitule);

    // Récupérer les formations par date
    List<Formation> findByDateFormation(String dateFormation);
}
