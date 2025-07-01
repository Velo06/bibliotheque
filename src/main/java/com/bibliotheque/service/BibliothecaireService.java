package com.exemple.service;

import com.exemple.entity.Bibliothecaire;
import com.exemple.repository.BibliothecaireRepository;
import org.springframework.stereotype.Service;

@Service
public class BibliothecaireService {

    private final BibliothecaireRepository bibliothecaireRepository;

    public BibliothecaireService(BibliothecaireRepository bibliothecaireRepository) {
        this.bibliothecaireRepository = bibliothecaireRepository;
    }

    public boolean pseudoExists(String pseudo) {
        return bibliothecaireRepository.findByPseudo(pseudo) != null;
    }

    public Bibliothecaire authentifier(String pseudo, String motDePasse) {
        Bibliothecaire bibliothecaire = bibliothecaireRepository.findByPseudo(pseudo);
        if (bibliothecaire != null && bibliothecaire.getMotDePasse().equals(motDePasse)) {
            return bibliothecaire;
        }
        return null;
    }
}