package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Bibliothecaire;
import com.example.demo.repository.BibliothecaireRepository;

@Service
public class BibliothecaireService {
    
    @Autowired
    private BibliothecaireRepository biblioRepository;
    
    public Bibliothecaire saveBibliothecaire(Bibliothecaire personne) {
        return biblioRepository.save(personne);
    }

    public boolean authenticate(String nom, String rawPassword) {
        boolean exist = biblioRepository.checkLogin(nom, rawPassword);
        return exist;
    }
}