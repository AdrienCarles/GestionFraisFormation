package com.webflow.fraisdeformation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/declaration")
public class DeclarationController {

    @GetMapping("")
    public String startFlow() {
        return "redirect:/flows/declaration-flow";
    }
}