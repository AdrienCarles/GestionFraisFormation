package com.webflow.fraisdeformation.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccueilController {

    // Affiche la page d'accueil
    @GetMapping("/")
    public String afficherAccueil(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            return "redirect:/home";
        }
        return "accueil";
    }

}
