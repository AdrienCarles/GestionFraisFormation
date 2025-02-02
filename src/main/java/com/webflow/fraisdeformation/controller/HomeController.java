package com.webflow.fraisdeformation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Affiche la page d'accueil
    @GetMapping("/")
    public String afficherAccueil() {
        return "home";
    }
}
