package com.webflow.fraisdeformation.service;

import com.webflow.fraisdeformation.model.CoordonneesBancaires;
import com.webflow.fraisdeformation.repository.CoordonneesBancairesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoordonneesBancairesService {

    @Autowired
    private CoordonneesBancairesRepository coordonneesBancairesRepository;

    // Ajouter ou mettre à jour des coordonnées bancaires
    public CoordonneesBancaires sauvegarderCoordonneesBancaires(CoordonneesBancaires coordonneesBancaires) {
        return coordonneesBancairesRepository.save(coordonneesBancaires);
    }

    // Récupérer les coordonnées bancaires par ID
    public Optional<CoordonneesBancaires> obtenirCoordonneesBancairesParId(Long id) {
        return coordonneesBancairesRepository.findById(id);
    }

    // Récupérer les coordonnées bancaires par l'ID de la déclaration
    public Optional<CoordonneesBancaires> obtenirCoordonneesBancairesParDeclarationId(Long declarationId) {
        return Optional.ofNullable(coordonneesBancairesRepository.findByDeclarationId(declarationId));
    }

    // Supprimer les coordonnées bancaires par ID
    public void supprimerCoordonneesBancaires(Long id) {
        coordonneesBancairesRepository.deleteById(id);
    }
}
