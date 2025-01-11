package com.webflow.fraisdeformation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.webflow.fraisdeformation.model.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    // Rechercher un rôle par son nom (ex : ADMIN, PROFESSEUR)
    Optional<Role> findByName(String name);
}
