package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.service.ReservationService;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.Adherent;
import com.example.demo.entity.Etat;
import com.example.demo.entity.Livre;

import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService resaService;

    @Autowired
    public ReservationController(ReservationService resaService) {
        this.resaService = resaService;
    }

    @GetMapping("formReserve")
    public String formResa(
            @RequestParam("idAdherent") Long idAdherent,
            @RequestParam("idLivre") Long idLivre,
            Model model) {
        model.addAttribute("idAdherent", idAdherent);
        model.addAttribute("idLivre", idLivre);
        return "form-resa";
    }

    @PostMapping("saveReserve")
    public String reserver(RedirectAttributes redirectAttributes, @RequestParam("adh") int adh, @RequestParam("livre") int livre, @RequestParam("dateResa") LocalDate date) {
        Adherent a = new Adherent();
        a.setId(adh);
        Livre l = new Livre();
        l.setId(livre);
        Etat e = new Etat();
        e.setId(3);
        LocalDate ld = LocalDate.now();
        if(date.isBefore(ld) || date.isEqual(ld)) {
            redirectAttributes.addFlashAttribute("message", "La date a reserver ne doit pas etre la date d'aujourd'hui ou anterieure a celle d'aujourd'hui.");
            return "redirect:/reservation/formReserve?idAdherent=" + adh + "&idLivre=" + livre;
        }
        Reservation r = new Reservation(a, l, e, ld, date);
        resaService.saveReservation(r);
        return "redirect:/livre/listeLivre";
    }
    
}
