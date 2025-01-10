package com.webflow.fraisdeformation.model;

public enum StatutDeclaration {
    BROUILLON,  // En cours de rédaction
    EN_COURS,   // En cours de validation
    VALIDE,     // Validé par l'administration
    REFUSE,     // Refusé après vérification
    PAYE        // Frais remboursés
}