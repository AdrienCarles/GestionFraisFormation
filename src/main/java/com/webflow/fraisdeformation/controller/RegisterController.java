package com.webflow.fraisdeformation.controller;

import com.webflow.fraisdeformation.model.Utilisateur;
import com.webflow.fraisdeformation.model.Role;
import com.webflow.fraisdeformation.repository.UtilisateurRepository;
import com.webflow.fraisdeformation.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class RegisterController {

    @Autowired
    private UtilisateurRepository userRepository;

    @Autowired
    private RoleRepository roleRepository; // Repository pour récupérer les rôles

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String afficherFormulaireInscription() {
        return "register";
    }

    @PostMapping("/register")
    public String enregistrerUtilisateur(@RequestParam String nomUtilisateur,
                                         @RequestParam String email,
                                         @RequestParam String password,
                                         Model model) {
        // Vérifier si l'utilisateur existe déjà
        if (userRepository.findByEmail(email).isPresent()) {
            model.addAttribute("error", "Cet email est déjà utilisé.");
            return "register";
        }

        // Créer un nouvel utilisateur avec mot de passe hashé
        Utilisateur user = new Utilisateur();
        user.setNomUtilisateur(nomUtilisateur); // 🔥 Ajouter un nom d'utilisateur
        user.setEmail(email);
        user.setMotDePasse(passwordEncoder.encode(password));

        // Assigner un rôle par défaut
        Role roleProfesseur = roleRepository.findByName("PROFESSEUR")
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName("PROFESSEUR");
                    return roleRepository.save(newRole);
                });

        user.setRoles(Collections.singleton(roleProfesseur));

        // Sauvegarde de l'utilisateur
        userRepository.save(user);

        return "redirect:/login";
    }

}
