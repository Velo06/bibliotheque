package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Adherent;
import com.example.demo.repository.AdherentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdherentService {
    
    @Autowired
    private AdherentRepository adherentRepository;
    
    public Adherent saveAdherent(Adherent personne) {
        return adherentRepository.save(personne);
    }

    public boolean authenticate(String nom, String rawPassword) {
        boolean exist = adherentRepository.checkLogin(nom, rawPassword);
        return exist;
    }

    public List<Adherent> findAllAdherent() {
        return adherentRepository.findAll();
    }

    public Optional<Adherent> getById(Long id) {
        return adherentRepository.findById(id);
    }

    public boolean estAbonne(Long idAdh) {
        return adherentRepository.isAdherentAbonne(idAdh);
    }

    public boolean nonSanctionne(Long idAdh) {
        return adherentRepository.isAdherentNotSanctionne(idAdh);
    }

    public int getQuotaLivre(Long idAdh) {
        return adherentRepository.getQuotaLivre(idAdh);
    }

    public Long getNomById(String name) {
        return adherentRepository.getIdAdherentByNom(name);
    }

    public int getQuotaReservation(int idAdherent) {
        return adherentRepository.getQuotaReservation(idAdherent);
    }

    public int getAgeAdherent(Long id) {
        return adherentRepository.getAgeAdherent(id);
    }
}