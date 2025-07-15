package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Livre;
import com.example.demo.repository.LivreRepository;
import java.util.List;
import java.util.Optional;

@Service
public class LivreService {
    @Autowired
    private LivreRepository livreRepository;

    public List<Livre> getAllLivre() {
        List<Livre> l = livreRepository.findAllWithThemeAndAuteur();
        return l;
    }

    public Optional<Livre> getById(Long id) {
        return livreRepository.findById(id);
    }

    public int getAgeRestriction(Long idLivre) {
        return livreRepository.getAgeRestrictionLivre(idLivre);
    }

    @Transactional(readOnly = true)
    public Livre getLivreAvecExemplaires(Long id) {
        Livre livre = livreRepository.findById(id).orElseThrow(() -> new RuntimeException("Livre introuvable"));
        livre.getExemplaires().forEach(e -> {
            e.getEtat().getEtat(); // accède à la chaîne de caractères "disponible", etc.
        });
        return livre;
    }


}
