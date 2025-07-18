package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.service.PretService;
import com.example.demo.service.TypePretService;
import com.example.demo.service.AdherentService;
import com.example.demo.service.LivreService;
import com.example.demo.service.ExemplaireService;
import com.example.demo.service.PenalisationService;
import com.example.demo.service.JourFerieService;
import com.example.demo.entity.Pret;
import com.example.demo.entity.TypePret;
import com.example.demo.entity.JourFerie;
import com.example.demo.entity.Adherent;
import com.example.demo.entity.Livre;
import com.example.demo.entity.Exemplaire;
import com.example.demo.entity.Penalisation;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Controller
@RequestMapping("/pret")
public class PretController {
    @Autowired
    private PretService pretService;
    private TypePretService typePretService;
    private AdherentService adherentService;
    private LivreService livreService;
    private ExemplaireService exemplaireService;
    private PenalisationService penaliteService;
    private JourFerieService jourFerieService;

    @Autowired
    public PretController(
        PretService pretService,
        TypePretService typePretService,
        AdherentService adherentService,
        LivreService livreService,
        ExemplaireService exemplaireService,
        PenalisationService penaliteService,
        JourFerieService jourFerieService
    ) {
        this.pretService = pretService;  
        this.typePretService = typePretService;       
        this.adherentService = adherentService;  
        this.livreService = livreService;  
        this.exemplaireService = exemplaireService;  
        this.penaliteService = penaliteService;  
        this.jourFerieService = jourFerieService;  
    }

    @GetMapping("/formPreter")
    public String PretForm(Model model) {
        List<TypePret> lt = typePretService.findAllTypePret();
        List<Adherent> la = adherentService.findAllAdherent();
        List<Livre> li = livreService.getAllLivre();
        model.addAttribute("listeTypes", lt);
        model.addAttribute("listeAdherents", la);
        model.addAttribute("listeLivres", li);
        return "form-pret";
    }

    @PostMapping("/preter")
    public String preter(RedirectAttributes redirectAttributes, @RequestParam("adherentId") Long idAdherent, @RequestParam("livreId") Long idLivre, @RequestParam("typePretId") int idType, @RequestParam("dateEmprunt") LocalDate date, @RequestParam("datePrevu") LocalDate prevu) {
        // adherent abonne 
        boolean abonne = adherentService.estAbonne(idAdherent);
        if(abonne == true) {
            // adherent non penalise 
            LocalDate ajd = LocalDate.now();
            boolean nonPenalise = adherentService.nonSanctionne(idAdherent, ajd);
            if(nonPenalise == true) {
                // quota livre atteint
                int quota = adherentService.getQuotaLivre(idAdherent);
                int count = pretService.countQuotaAdherent(idAdherent);
                if(count < quota) {
                    // exemplaire disponible
                    int exDispo = exemplaireService.exemplaireDispo(idLivre);
                    if(exDispo != 0) {
                        int age = adherentService.getAgeAdherent(idAdherent);
                        // int restrict = livreService.getAgeRestriction(idLivre);
                        // if(age > restrict) {
                            // Récupération des entités nécessaires
                            Optional<Adherent> optionalAdherent = adherentService.getById(idAdherent);
                            Adherent adherent = optionalAdherent.get();
                            Optional<Livre> optionalLivre = livreService.getById(idLivre);
                            Livre livre = optionalLivre.get();
                            TypePret typePret = new TypePret();
                            typePret.setId(idType);
                            if(prevu == null) {
                                int dureePret = adherent.getTypeAdherent().getDureePret();
                                prevu = date.plusDays(dureePret);
                            }
                            List<LocalDate> ljf = jourFerieService.getJourFerie();
                            while (ljf.contains(prevu)) {
                                prevu = prevu.plusDays(1); // Décale jusqu'à une date non fériée
                            }
                            Pret p = new Pret(typePret, adherent, livre, prevu, date, null);
                            pretService.savePret(p);
                            redirectAttributes.addFlashAttribute("message", "Pret reussi.");
                            return "redirect:/pret/formPreter";
                        // } else {
                        //     redirectAttributes.addFlashAttribute("message", "Cet adherent n'a pas le droit de preter ce livre.");
                        //     return "redirect:/pret/formPreter";
                        // }
                    } else {
                        redirectAttributes.addFlashAttribute("message", "Aucun exemplaire disponible.");
                        return "redirect:/pret/formPreter";
                    }
                } else {
                    redirectAttributes.addFlashAttribute("message", "Quota livre atteint.");
                    return "redirect:/pret/formPreter";
                }
            } else {
                redirectAttributes.addFlashAttribute("message", "Cet adherent est encore penalise.");
                return "redirect:/pret/formPreter";
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Cet adherent n'est pas abonne.");
            return "redirect:/pret/formPreter";
        }
    }

    @GetMapping("enCours")
    public String listePretEnCours(@RequestParam("idAdherent") Integer idAdh, Model model) {
        List<Pret> lp = pretService.pretEnCoursAdherent(idAdh);
        model.addAttribute("pret-en-cours", lp);
        return "pret-en-cours";
    }

    @GetMapping("rendrePret")
    public String formRendre(Model model) {
        List<Pret> nonRendu = pretService.getAllPretNonRendu();
        model.addAttribute("listeNonRendu", nonRendu);
        return "rendre-pret";
    }

    @GetMapping("formSaveRendre")
    public String formRendrePret(@RequestParam("idPret") Long idPret, @RequestParam("idAdherent") Long idAdh, Model model) {
        model.addAttribute("idPret", idPret);
        model.addAttribute("idAdherent", idAdh);
        return "form-rendre";
    }

    @PostMapping("saveRendrePret")
    public String saveRendrePret(@RequestParam("dateRetourReel") LocalDate dateReel, @RequestParam("pret") int idPret, @RequestParam("adh") int idAdherent) {
        Long pret = (long) idPret;
        LocalDate datePrevu = pretService.getDateRetourPrevu(idPret);
        pretService.rendreLivre(idPret, dateReel);
        if(datePrevu.isBefore(dateReel)) {
            int duree = adherentService.getDureePenalite(idAdherent);
            LocalDate dateFin = dateReel.plusDays(duree);
            Adherent a = new Adherent();
            a.setId(idAdherent);
            Penalisation p = new Penalisation(a, dateReel, dateFin);
            penaliteService.savePenalite(p);
        }
        return "redirect:/pret/rendrePret";
    }
}
