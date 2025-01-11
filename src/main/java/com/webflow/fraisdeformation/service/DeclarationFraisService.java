package com.webflow.fraisdeformation.service;

import com.webflow.fraisdeformation.model.DeclarationFrais;
import com.webflow.fraisdeformation.repository.DeclarationFraisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeclarationFraisService {

    @Autowired
    private DeclarationFraisRepository declarationFraisRepository;

    // Ajouter ou mettre à jour une déclaration
    public DeclarationFrais sauvegarderDeclaration(DeclarationFrais declarationFrais) {
        return declarationFraisRepository.save(declarationFrais);
    }

    // Récupérer une déclaration par son ID
    public Optional<DeclarationFrais> obtenirDeclarationParId(Long id) {
        return declarationFraisRepository.findById(id);
    }

    // Récupérer toutes les déclarations d'un utilisateur
    public List<DeclarationFrais> obtenirDeclarationsParUtilisateur(Long utilisateurId) {
        return declarationFraisRepository.findByUtilisateurId(utilisateurId);
    }

    // Récupérer toutes les déclarations par statut
    public List<DeclarationFrais> obtenirDeclarationsParStatut(String status) {
        return declarationFraisRepository.findByStatus(status);
    }

    // Supprimer une déclaration
    public void supprimerDeclaration(Long id) {
        declarationFraisRepository.deleteById(id);
    }

    // Récupérer toutes les déclarations
    public List<DeclarationFrais> obtenirToutesLesDeclarations() {
        return declarationFraisRepository.findAll();
    }
}
