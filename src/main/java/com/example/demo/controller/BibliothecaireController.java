package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDate;
import java.util.List;

import com.example.demo.service.BibliothecaireService;
import com.example.demo.entity.Bibliothecaire;
import com.example.demo.entity.Etat;
import com.example.demo.entity.TypePret;
import com.example.demo.entity.Pret;
import com.example.demo.entity.Livre;
import com.example.demo.entity.Adherent;
import com.example.demo.service.ReservationService;
import com.example.demo.service.PretService;
import com.example.demo.entity.Reservation;
import com.example.demo.service.ProlongementService;
import com.example.demo.entity.Prolongement;

@Controller
@RequestMapping("/bibliothecaire")
public class BibliothecaireController {
    @Autowired
    private BibliothecaireService biblioService;
    private ReservationService resaService;
    private ProlongementService prolongService;
    private PretService pretService;

    @Autowired
    public BibliothecaireController(
        BibliothecaireService biblioService,      
        ReservationService resaService,  
        ProlongementService prolongService,  
        PretService pretService  
    ) {
        this.biblioService = biblioService;       
        this.resaService = resaService;       
        this.prolongService = prolongService;       
        this.pretService = pretService;       
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
            // return "redirect:/pret/formPreter";
            return "dashboard";
        } else {
            // Ajouter un message d'erreur
            redirectAttributes.addFlashAttribute("error", "Nom ou mot de passe incorrect");
            return "redirect:/bibliothecaire/login";
        }
    }

    @GetMapping("validerResa")
    public String listeResa(Model model) {
        List<Reservation> lr = resaService.getResaEnAttente();
        model.addAttribute("listeResa",lr);
        return "resa-attente";
    }

    @GetMapping("acceptResa")
    public String acceptResa(@RequestParam("idResa") int idResa) {
        Etat e = new Etat();
        e.setId(1);
        resaService.accept(idResa,e);
        return "redirect:/bibliothecaire/validerResa";
    }

    @GetMapping("refusResa")
    public String refusResa(@RequestParam("idResa") int idResa) {
        Etat e = new Etat();
        e.setId(2);
        resaService.refus(idResa,e);
        return "redirect:/bibliothecaire/validerResa";
    }

    @GetMapping("prolongement")
    public String listeProlong(Model model) {
        List<Prolongement> lp = prolongService.getProlongementEnAttente();
        model.addAttribute("listeProlong", lp);
        return "prolong-attente";
    }

    @GetMapping("acceptProlong")
    public String acceptProlong(@RequestParam("idProlong") int idProlong, @RequestParam("idAdherent") int idAdh, @RequestParam("idLivre") int idLivre, @RequestParam("dateDemande") LocalDate dateDm, @RequestParam("dateFin") LocalDate fin) {
        Etat e = new Etat();
        e.setId(1);
        prolongService.accept(idProlong,e);
        Adherent a = new Adherent();
        a.setId(idAdh);
        Livre l = new Livre();
        l.setId(idLivre);
        TypePret tp = new TypePret();
        tp.setId(2);
        Pret p = new Pret(tp, a, l, fin, dateDm, null);
        pretService.savePret(p);
        return "redirect:/bibliothecaire/prolongement";
    }

    @GetMapping("refusProlong")
    public String refusProlong(@RequestParam("idProlong") int idProlong) {
        Etat e = new Etat();
        e.setId(2);
        prolongService.refus(idProlong,e);
        return "redirect:/bibliothecaire/prolongement";
    }
}
