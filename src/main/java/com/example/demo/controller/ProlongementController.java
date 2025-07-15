package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.ProlongementService;
import com.example.demo.entity.Prolongement;
import com.example.demo.service.AdherentService;
import com.example.demo.entity.Adherent;

import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/prolongement")
public class ProlongementController {
    private ProlongementService prolongService;
    private AdherentService adherentService;

    @Autowired
    public ProlongementController(ProlongementService prolongService, AdherentService adherentService) {
        this.prolongService = prolongService;
        this.adherentService = adherentService;
    }

    @GetMapping("prolonger")
    public String prolonger(@RequestParam("idPret") Long pret, @RequestParam("idAdherent") Long adherent) {
        boolean abonne = adherentService.estAbonne(adherent);
        LocalDate ajd = LocalDate.now();
        boolean nonPenalise = adherentService.nonSanctionne(adherent, ajd);
        if(abonne == true) {
            if(nonPenalise == true) {
                // mbola tsisy formulaire de prolongement
            }
        }
        return null;
    }
}
