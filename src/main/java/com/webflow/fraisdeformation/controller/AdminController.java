package com.webflow.fraisdeformation.controller;

import com.webflow.fraisdeformation.model.Utilisateur;
import com.webflow.fraisdeformation.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public String afficherUtilisateurs(Model model) {
        List<Utilisateur> utilisateurs = utilisateurService.obtenirTousLesUtilisateurs();
        model.addAttribute("utilisateurs", utilisateurs);
        return "admin/users";
    }

    @GetMapping("/admin/create-user")
    @PreAuthorize("hasRole('ADMIN')")
    public String afficherFormulaireCreationUtilisateur() {
        return "admin/create-user";
    }

    @PostMapping("/admin/create-user")
    @PreAuthorize("hasRole('ADMIN')")
    public String creerUtilisateur(@RequestParam String nomUtilisateur,
                                   @RequestParam String email,
                                   @RequestParam String password,
                                   @RequestParam String role,
                                   Model model) {
        try {
            String roleAvecPrefixe = role.startsWith("ROLE_") ? role : "ROLE_" + role;

            utilisateurService.creerUtilisateurParAdmin(nomUtilisateur, email, password, roleAvecPrefixe);
            model.addAttribute("success", "Utilisateur créé avec succès !");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "admin/users";
    }

    @GetMapping("/admin/edit-user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String afficherFormulaireEdition(@PathVariable Long id, Model model) {
        Utilisateur utilisateur = utilisateurService.trouverParId(id);
        model.addAttribute("utilisateur", utilisateur);
        return "admin/edit-user";
    }

    @PostMapping("/admin/edit-user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String modifierUtilisateur(@PathVariable Long id,
                                      @RequestParam String nomUtilisateur,
                                      @RequestParam String email,
                                      @RequestParam String role,
                                      RedirectAttributes redirectAttributes) {
        utilisateurService.modifierUtilisateur(id, nomUtilisateur, email, role);
        redirectAttributes.addFlashAttribute("success", "Utilisateur mis à jour !");
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/delete-user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String supprimerUtilisateur(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        utilisateurService.supprimerUtilisateur(id);
        redirectAttributes.addFlashAttribute("success", "Utilisateur supprimé !");
        return "redirect:/admin/users";
    }

}
