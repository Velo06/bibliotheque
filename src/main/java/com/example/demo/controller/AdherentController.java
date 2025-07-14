package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.service.AdherentService;
import com.example.demo.entity.Adherent;

@Controller
@RequestMapping("/adherent")
public class AdherentController {
    @Autowired
    private AdherentService adherentService;

    @Autowired
    public AdherentController(
        AdherentService adherentService      
    ) {
        this.adherentService = adherentService;       
    }

    // @GetMapping("/formAdherent")
    // public String AdherentForm() {
    //     return "formAdherent";
    // }

    // @PostMapping("/saveAdherent")
    // public String insertAdherent(@ModelAttribute Adherent adh) {
    //     adherentService.saveAdherent(adh);
    //     return "redirect:/adherent";
    // }

    @GetMapping("/login")
    public String loginAdherent() {
        return "loginAdherent";
    }

    @PostMapping("/checkLogin") 
    public String checkLogin(
        @RequestParam("nom") String nom,
        @RequestParam("mdp") String mdp,
        HttpSession session,
        RedirectAttributes redirectAttributes) {
        
        boolean check = adherentService.authenticate(nom, mdp);
        
        if(check) {
            // Stocker l'utilisateur en session
            session.setAttribute("currentUserName", nom);
            return "redirect:/livre/listeLivre";
        } else {
            // Ajouter un message d'erreur
            redirectAttributes.addFlashAttribute("error", "Nom ou mot de passe incorrect");
            return "redirect:/adherent/login";
        }
    }

}
