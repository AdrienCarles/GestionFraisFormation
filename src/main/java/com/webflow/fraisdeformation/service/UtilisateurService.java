package com.webflow.fraisdeformation.service;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import com.webflow.fraisdeformation.model.Utilisateur;
import com.webflow.fraisdeformation.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Utilisateur> obtenirTousLesUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur trouverParId(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID : " + id));
    }


    public Optional<Utilisateur> obtenirUtilisateurParNom(String nomUtilisateur) {
        return utilisateurRepository.findByNomUtilisateur(nomUtilisateur);
    }

    public boolean emailExisteDeja(String email) {
        return utilisateurRepository.existsByEmail(email);
    }

    public Utilisateur sauvegarderUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur creerUtilisateurParAdmin(String nomUtilisateur, String email, String password, int role) {
        if (utilisateurRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Cet email est déjà utilisé.");
        }

        Utilisateur user = new Utilisateur();
        user.setNomUtilisateur(nomUtilisateur);
        user.setEmail(email);
        user.setMotDePasse(passwordEncoder.encode(password));
        user.setRole(role); // 1 = PROFESSEUR, 2 = ADMIN

        return utilisateurRepository.save(user);
    }

    public void modifierUtilisateur(Long id, String nomUtilisateur, String email, int role) {

        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        utilisateur.setNomUtilisateur(nomUtilisateur);
        utilisateur.setEmail(email);
        utilisateur.setRole(role);

        utilisateurRepository.save(utilisateur);
    }

    public void supprimerUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }
}
