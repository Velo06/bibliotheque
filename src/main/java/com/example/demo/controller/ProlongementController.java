package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.service.ProlongementService;
import com.example.demo.service.PretService;
import com.example.demo.entity.Prolongement;
import com.example.demo.service.AdherentService;
import com.example.demo.entity.Adherent;
import com.example.demo.entity.Etat;
import com.example.demo.entity.Pret;
import java.time.LocalDate;
import java.time.Period;

import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/prolongement")
public class ProlongementController {
    private ProlongementService prolongService;
    private AdherentService adherentService;
    private PretService pretService;

    @Autowired
    public ProlongementController(ProlongementService prolongService, AdherentService adherentService, PretService pretService) {
        this.prolongService = prolongService;
        this.adherentService = adherentService;
        this.pretService = pretService;
    }

    @GetMapping("formProlong")
    public String formProlong(@RequestParam("idPret") Long pret,
                            @RequestParam("idAdherent") Long adherent,
                            Model model) {
        model.addAttribute("pret", pret);
        model.addAttribute("adherent", adherent);
        return "form-prolonger";
    }

    @GetMapping("prolonger")
    public String prolonger(@RequestParam("pret") int pret,
                            @RequestParam("adh") Long adherent,
                            @RequestParam("dateDm") LocalDate dmd,
                            @RequestParam("dateFin") LocalDate fin,
                            RedirectAttributes redirectAttributes) {
        boolean abonne = adherentService.estAbonne(adherent);
        LocalDate now = LocalDate.now();
        boolean nonPenalise = adherentService.nonSanctionne(adherent, now);
        if (abonne) {
            if (nonPenalise) {
                LocalDate datePrevu = pretService.getDateRetourPrevu(pret);
                Period diff = Period.between(datePrevu, dmd);
                if (diff.getDays() >= 2) {
                    int quota = adherentService.getQuotaProlongement(adherent);
                    int count = prolongService.countProlongementsByAdherent(adherent);
                    if (count < quota) {
                        Pret p = new Pret();
                        p.setId(pret);
                        Etat e = new Etat();
                        e.setId(3); // en attente
                        Prolongement pg = new Prolongement(p, fin, dmd, e);
                        prolongService.saveProlongement(pg);
                        redirectAttributes.addFlashAttribute("error", "Demande de prolongement enregistrée.");
                        // return "redirect:/pret/nonRendus"; 
                    } else {
                        redirectAttributes.addFlashAttribute("error", "Quota prolongement atteint.");
                    }
                } else {
                    redirectAttributes.addFlashAttribute("error", "La demande doit être faite au moins 2 jours à l'avance.");
                }
            } else {
                redirectAttributes.addFlashAttribute("error", "Vous êtes encore pénalisé.");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Vous n'êtes pas abonné.");
        }
        return "redirect:/prolongement/formProlong?idPret=" + pret + "&idAdherent=" + adherent;
    }

}
