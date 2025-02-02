package com.webflow.fraisdeformation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String afficherLogin() {
        return "login";  // Retourne la page login.html
    }
}
