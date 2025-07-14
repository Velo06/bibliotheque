package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.service.BibliothecaireService;
import com.example.demo.entity.Bibliothecaire;

@Controller
@RequestMapping("/bibliothecaire")
public class BibliothecaireController {
    @Autowired
    private BibliothecaireService biblioService;

    @Autowired
    public BibliothecaireController(
        BibliothecaireService biblioService      
    ) {
        this.biblioService = biblioService;       
    }

    @GetMapping("/login")
    public String AdherentForm() {
        return "login-biblio";
    }

    @PostMapping("/checkLogin") 
    public String checkLogin(
        @RequestParam("nom") String nom,
        @RequestParam("mdp") String mdp,
        HttpSession session,
        RedirectAttributes redirectAttributes) {
        
        boolean check = biblioService.authenticate(nom, mdp);
        
        if(check) {
            // Stocker l'utilisateur en session
            session.setAttribute("currentUser", nom);
            return "redirect:/pret/formPreter";
        } else {
            // Ajouter un message d'erreur
            redirectAttributes.addFlashAttribute("error", "Nom ou mot de passe incorrect");
            return "redirect:/bibliothecaire/login";
        }
    }
}
