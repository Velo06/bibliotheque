package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.entity.Bibliothecaire;
import com.example.demo.service.BibliothecaireService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BibliothecaireController {
    
    @Autowired
    private BibliothecaireService bibliothecaireService;
    
    @GetMapping("/login")
    public String showLoginForm() {
        return "bibliothecaire/login";
    }

    @GetMapping("/bibliothecaire/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        // Vérification de la session
        Bibliothecaire bibliothecaire = (Bibliothecaire) session.getAttribute("bibliothecaire");
        
        if (bibliothecaire == null) {
            return "redirect:/login";
        }
        
        // Ajoutez des données au modèle si nécessaire
        model.addAttribute("bibliothecaire", bibliothecaire);
        
        return "bibliothecaire/dashboard";
    }
    
    @PostMapping("/login")
    public String login(@RequestParam String pseudo, 
                    @RequestParam String motDePasse,
                    HttpSession session,
                    Model model) {
        
        System.out.println("Tentative de connexion - Pseudo: " + pseudo + ", MDP: " + motDePasse);
        
        Bibliothecaire bibliothecaire = bibliothecaireService.authentifier(pseudo, motDePasse);
        
        if (bibliothecaire != null) {
            System.out.println("Connexion réussie pour: " + bibliothecaire.getPseudo());
            session.setAttribute("bibliothecaire", bibliothecaire);
            return "redirect:/bibliothecaire/dashboard";
        } else {
            System.out.println("Échec de connexion");
            model.addAttribute("error", "Pseudo ou mot de passe incorrect");
            return "bibliothecaire/login";
        }
}
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}