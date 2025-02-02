package com.webflow.fraisdeformation.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String afficherAccueil(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        // Récupérer le nom de l'utilisateur connecté
        if (userDetails != null) {
            model.addAttribute("nomUtilisateur", userDetails.getUsername());
        } else {
            model.addAttribute("nomUtilisateur", "Invité");
        }

        return "home"; // Retourne la page home.html
    }
}
