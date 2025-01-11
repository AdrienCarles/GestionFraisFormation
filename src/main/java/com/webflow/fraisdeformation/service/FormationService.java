package com.webflow.fraisdeformation.service;

import com.webflow.fraisdeformation.model.Formation;
import com.webflow.fraisdeformation.repository.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormationService {

    @Autowired
    private FormationRepository formationRepository;

    // Ajouter ou mettre à jour une formation
    public Formation sauvegarderFormation(Formation formation) {
        return formationRepository.save(formation);
    }

    // Récupérer une formation par ID
    public Optional<Formation> obtenirFormationParId(Long id) {
        return formationRepository.findById(id);
    }

    // Récupérer les formations par mot-clé dans l'intitulé
    public List<Formation> rechercherFormationParIntitule(String intitule) {
        return formationRepository.findByIntituleContainingIgnoreCase(intitule);
    }

    // Supprimer une formation par ID
    public void supprimerFormation(Long id) {
        formationRepository.deleteById(id);
    }

    // Récupérer toutes les formations
    public List<Formation> obtenirToutesLesFormations() {
        return formationRepository.findAll();
    }
}
