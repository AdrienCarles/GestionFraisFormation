package com.webflow.fraisdeformation.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Collections;
import org.springframework.data.jpa.repository.JpaRepository;
import com.webflow.fraisdeformation.model.Utilisateur;
import com.webflow.fraisdeformation.repository.UtilisateurRepository;
import com.webflow.fraisdeformation.model.Role;
import com.webflow.fraisdeformation.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private RoleRepository roleRepository;

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

    public Utilisateur creerUtilisateurParAdmin(String nomUtilisateur, String email, String password, String role) {
        if (utilisateurRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Cet email est déjà utilisé.");
        }

        Utilisateur user = new Utilisateur();
        user.setNomUtilisateur(nomUtilisateur);
        user.setEmail(email);
        user.setMotDePasse(passwordEncoder.encode(password));

        String roleAvecPrefixe = role.startsWith("ROLE_") ? role : "ROLE_" + role;

        Role userRole = roleRepository.findByName(roleAvecPrefixe)
                .orElseThrow(() -> new RuntimeException("Rôle non trouvé : " + roleAvecPrefixe));

        user.setRoles(Collections.singleton(userRole));

        return utilisateurRepository.save(user);
    }

    public void modifierUtilisateur(Long id, String email, String role) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        utilisateur.setEmail(email);

        Role userRole = roleRepository.findByName(role)
                .orElseThrow(() -> new RuntimeException("Rôle non trouvé : " + role));
        utilisateur.setRoles(Collections.singleton(userRole));

        utilisateurRepository.save(utilisateur);
    }

    public void supprimerUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }
}
