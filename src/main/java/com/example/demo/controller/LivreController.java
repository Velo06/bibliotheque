package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.LivreService;
import com.example.demo.entity.Livre;
import com.example.demo.service.AdherentService;
import com.example.demo.entity.Adherent;
import java.util.List;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/livre")
public class LivreController {
    private final LivreService livreService;
    private final AdherentService adherentService;

    @Autowired
    public LivreController(LivreService livreService, AdherentService adherentService) {
        this.livreService = livreService;
        this.adherentService = adherentService;
    }

    @GetMapping("listeLivre")
    public String listLivres(Model model, HttpSession session) {
        List<Livre> livres = livreService.getAllLivre();
        model.addAttribute("livres", livres);
        String currentUserIdStr = (String) session.getAttribute("currentUserName");
        Long currentUserId = adherentService.getNomById(currentUserIdStr);
        if(currentUserIdStr == null) {
            return "redirect:/adherent/login";
        }
        model.addAttribute("currentUserId", currentUserId);
        return "liste-livres";
    }
}
