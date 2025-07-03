package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Bibliothecaire;
import com.example.demo.repository.BibliothecaireRepository;

@Service
public class BibliothecaireService {
    
    @Autowired
    private BibliothecaireRepository bibliothecaireRepository;
    
    public Bibliothecaire authentifier(String pseudo, String motDePasse) {
        System.out.println("Tentative de connexion avec pseudo: " + pseudo);
        Bibliothecaire bibliothecaire = bibliothecaireRepository.findByPseudo(pseudo);
        
        if (bibliothecaire != null) {
            System.out.println("Utilisateur trouvé. Mot de passe DB: " + bibliothecaire.getMot_de_passe() 
                + " | Mot de passe saisi: " + motDePasse);
        } else {
            System.out.println("Aucun utilisateur trouvé avec ce pseudo");
        }
        
        if (bibliothecaire != null && bibliothecaire.getMot_de_passe().equals(motDePasse)) {
            return bibliothecaire;
        }
        return null;
    }
}