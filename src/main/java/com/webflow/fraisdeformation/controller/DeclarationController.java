package com.webflow.fraisdeformation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeclarationController {

    @GetMapping("/declaration")
    public String lancerDeclaration() {
        return "declaration-flow";
    }
}
