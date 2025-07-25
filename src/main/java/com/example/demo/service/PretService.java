package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Pret;
import com.example.demo.repository.PretRepository;
import java.util.List;
import java.time.LocalDate;

@Service
public class PretService {
    
    @Autowired
    private PretRepository pretRepository;

    public int countQuotaAdherent(Long idAdh) {
        return pretRepository.countQuotaAdherent(idAdh);
    }

    public void savePret(Pret p) {
        pretRepository.save(p);
    }

    public List<Pret> pretEnCoursAdherent(Integer adh) {
        return pretRepository.findPretsEnCoursByAdherent(adh);
    }

    public List<Pret> getAllPretNonRendu() {
        return pretRepository.getAllPretNonRendu();
    }

    public LocalDate getDateRetourPrevu(int idPret) {
        return pretRepository.getDateRetourPrevu(idPret);
    }

    @Transactional
    public void rendreLivre(int idPret, LocalDate dateRetour) {
        pretRepository.saveRendu(dateRetour, idPret);
    }
}