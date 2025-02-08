package com.webflow.fraisdeformation.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String afficherAccueil(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        if (userDetails != null) {
            model.addAttribute("nomUtilisateur", userDetails.getUsername());
            model.addAttribute("roles", userDetails.getAuthorities());
        } else {
            model.addAttribute("nomUtilisateur", "Invité");
            model.addAttribute("roles", List.of());
        }
        System.out.println("Rôles utilisateur : " + userDetails.getAuthorities());

        return "home";
    }
}
