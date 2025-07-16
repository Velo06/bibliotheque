package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Adherent;
import com.example.demo.entity.Penalisation;
import com.example.demo.repository.PenalisationRepository;
import com.example.demo.repository.PretRepository;
import com.example.demo.repository.AdherentRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;

@Service
public class AdherentService {
    
    @Autowired
    private AdherentRepository adherentRepository;
    @Autowired
    private PretRepository pretRepository;
    @Autowired
    private PenalisationRepository penalisationRepository;
    
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

    public boolean nonSanctionne(Long idAdh, LocalDate ajd) {
        return adherentRepository.isAdherentNotSanctionne(idAdh, ajd);
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
        LocalDate dateNaissance = adherentRepository.getAgeAdherent(id);
        if (dateNaissance == null) {
            throw new RuntimeException("Date de naissance introuvable pour l'adhérent avec l'id: " + id);
        }
        return Period.between(dateNaissance, LocalDate.now()).getYears();
    }

    public int getQuotaProlongement(Long idAdherent) {
        return adherentRepository.getQuotaProlongement(idAdherent);
    }    

    public int getDureePenalite(int id) {
        return adherentRepository.getDureePenalite(id);
    }

     public Map<String, Object> getAdherentDetails(Long id) {
        Adherent adherent = adherentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adherent non trouvé"));

        Map<String, Object> response = new HashMap<>();
        response.put("id", adherent.getId());
        response.put("nom", adherent.getNom());
        response.put("prenom", adherent.getPrenom());
        response.put("dateNaissance", adherent.getDateNaissance());

        Map<String, Object> typeAdh = new HashMap<>();
        typeAdh.put("type", adherent.getTypeAdherent().getType());
        response.put("typeAdherent", typeAdh);

        // Compter les prets à domicile non rendus
        int pretsEnCours = pretRepository.countByAdherentIdAndTypePretMaisonAndNonRendu(id);
        int quota = adherent.getTypeAdherent().getQuotaLivre();
        response.put("resteQuotaPret", quota - pretsEnCours);

        // Vérifier la pénalisation
        Penalisation penalisation = penalisationRepository.findSanctionActive(id, LocalDate.now());
        if (penalisation != null) {
            response.put("sanctionne", true);
            response.put("finSanction", penalisation.getDateFin());
        } else {
            response.put("sanctionne", false);
        }

        return response;
    }

    public int getDureePret(int idAdherent) {
        return adherentRepository.getDureePret(idAdherent);
    }
}