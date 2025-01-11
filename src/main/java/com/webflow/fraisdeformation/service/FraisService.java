package com.webflow.fraisdeformation.service;

import com.webflow.fraisdeformation.model.Frais;
import com.webflow.fraisdeformation.repository.FraisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FraisService {

    @Autowired
    private FraisRepository fraisRepository;

    // Ajouter ou mettre à jour un frais
    public Frais sauvegarderFrais(Frais frais) {
        return fraisRepository.save(frais);
    }

    // Récupérer un frais par ID
    public Optional<Frais> obtenirFraisParId(Long id) {
        return fraisRepository.findById(id);
    }

    // Récupérer tous les frais associés à une déclaration
    public List<Frais> obtenirFraisParDeclaration(Long declarationId) {
        return fraisRepository.findByDeclarationId(declarationId);
    }

    // Supprimer un frais par ID
    public void supprimerFrais(Long id) {
        fraisRepository.deleteById(id);
    }

    // Récupérer tous les frais
    public List<Frais> obtenirTousLesFrais() {
        return fraisRepository.findAll();
    }
}
