package com.example.demo.controller;

import com.example.demo.entity.Livre;
import com.example.demo.entity.Exemplaire;
import com.example.demo.service.LivreService;
import com.example.demo.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/livres")
public class LivreRestController {

    private final LivreRepository livreRepository;

    public LivreRestController(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLivreById(@PathVariable("id") Long id) {
        Optional<Livre> optionalLivre = livreRepository.findById(id);
        if (optionalLivre.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Livre livre = optionalLivre.get();

        // Construire une liste des exemplaires simplifiés
        List<Map<String, Object>> exemplairesSimplifies = new ArrayList<>();
        for (Exemplaire e : livre.getExemplaires()) {
            Map<String, Object> ex = new HashMap<>();
            ex.put("id", e.getId());
            ex.put("etat", e.getEtat().getEtat());
            exemplairesSimplifies.add(ex);
        }

        // Construire la réponse JSON simplifiée
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("titre", livre.getTitre());
        response.put("ageRestriction", livre.getAgeRestriction());
        response.put("nbrExemplaire", livre.getNbrExemplaire());
        response.put("exemplaires", exemplairesSimplifies);

        return ResponseEntity.ok(response);
    }
}

