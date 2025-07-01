package com.exemple.controller;

import com.exemple.entity.Bibliothecaire;
import com.exemple.service.BibliothecaireService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/bibliothecaire")
public class BibliothecaireController {

    private final BibliothecaireService bibliothecaireService;

    public BibliothecaireController(BibliothecaireService bibliothecaireService) {
        this.bibliothecaireService = bibliothecaireService;
    }

    @GetMapping("/connexion")
    public String showLoginForm(@RequestParam(required = false) String errorType, Model model) {
        if (errorType != null) {
            String errorMessage = switch (errorType) {
                case "pseudo" -> "Pseudo incorrect";
                case "password" -> "Mot de passe incorrect";
                default -> "Identifiants incorrects";
            };
            model.addAttribute("errorMessage", errorMessage);
        }
        return "bibliothecaire/connexion";
    }

    @PostMapping("/connexion")
    public String processLogin(@RequestParam String pseudo,
                             @RequestParam String motDePasse,
                             HttpSession session) {
        
        if (!bibliothecaireService.pseudoExists(pseudo)) {
            return "redirect:/bibliothecaire/connexion?errorType=pseudo";
        }
        
        Bibliothecaire bibliothecaire = bibliothecaireService.authentifier(pseudo, motDePasse);
        if (bibliothecaire == null) {
            return "redirect:/bibliothecaire/connexion?errorType=password";
        }
        
        session.setAttribute("bibliothecaire", bibliothecaire);
        return "redirect:/bibliothecaire/liste-entites";
    }

    @GetMapping("/liste-entites")
    public String showEntityList(HttpSession session) {
        if (session.getAttribute("bibliothecaire") == null) {
            return "redirect:/bibliothecaire/connexion";
        }
        return "bibliothecaire/liste_entites";
    }

    @GetMapping("/deconnexion")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/bibliothecaire/connexion";
    }
}