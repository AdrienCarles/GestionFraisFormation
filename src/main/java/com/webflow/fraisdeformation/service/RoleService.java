package com.webflow.fraisdeformation.service;

import com.webflow.fraisdeformation.model.Role;
import com.webflow.fraisdeformation.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    // Ajouter ou mettre à jour un rôle
    public Role sauvegarderRole(Role role) {
        return roleRepository.save(role);
    }

    // Récupérer un rôle par ID
    public Optional<Role> obtenirRoleParId(Long id) {
        return roleRepository.findById(id);
    }

    // Récupérer un rôle par son nom (ex : ADMIN, PROFESSEUR)
    public Optional<Role> obtenirRoleParNom(String name) {
        return roleRepository.findByName(name);
    }

    // Récupérer tous les rôles
    public List<Role> obtenirTousLesRoles() {
        return roleRepository.findAll();
    }

    // Supprimer un rôle par ID
    public void supprimerRole(Long id) {
        roleRepository.deleteById(id);
    }
}
