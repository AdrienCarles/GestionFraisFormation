package com.webflow.fraisdeformation.controller;

import com.webflow.fraisdeformation.model.Utilisateur;
import com.webflow.fraisdeformation.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')") // Vérifie que l'utilisateur est ADMIN
public class AdminController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/users")
    public String afficherUtilisateurs(Model model) {
        List<Utilisateur> utilisateurs = utilisateurService.obtenirTousLesUtilisateurs();
        model.addAttribute("utilisateurs", utilisateurs);
        return "admin/users";
    }

    @GetMapping("/create-user")
    public String afficherFormulaireCreationUtilisateur() {
        return "admin/create-user";
    }

    @PostMapping("/create-user")
    public String creerUtilisateur(@RequestParam String nomUtilisateur,
                                   @RequestParam String email,
                                   @RequestParam String password,
                                   @RequestParam int role,
                                   RedirectAttributes redirectAttributes) {
        try {
            utilisateurService.creerUtilisateurParAdmin(nomUtilisateur, email, password, role);
            redirectAttributes.addFlashAttribute("success", "Utilisateur créé avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/edit-user/{id}")
    public String afficherFormulaireEdition(@PathVariable Long id, Model model) {
        Utilisateur utilisateur = utilisateurService.trouverParId(id);
        model.addAttribute("utilisateur", utilisateur);
        return "admin/edit-user";
    }

    @PostMapping("/edit-user/{id}")
    public String modifierUtilisateur(@PathVariable Long id,
                                      @RequestParam String nomUtilisateur,
                                      @RequestParam String email,
                                      @RequestParam int role,
                                      RedirectAttributes redirectAttributes) {
        utilisateurService.modifierUtilisateur(id, nomUtilisateur, email, role);
        redirectAttributes.addFlashAttribute("success", "Utilisateur mis à jour !");
        return "redirect:/admin/users";
    }

    @GetMapping("/delete-user/{id}")
    public String supprimerUtilisateur(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        utilisateurService.supprimerUtilisateur(id);
        redirectAttributes.addFlashAttribute("success", "Utilisateur supprimé !");
        return "redirect:/admin/users";
    }

}
