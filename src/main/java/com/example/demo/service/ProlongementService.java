package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Prolongement;
import com.example.demo.entity.Etat;
import com.example.demo.repository.ProlongementRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ProlongementService {
    
    @Autowired
    private ProlongementRepository prolongRepository;

    public List<Prolongement> getProlongementEnAttente() {
        return prolongRepository.getProlongementEnAttente();
    }

    @Transactional
    public void accept(int idProlong, Etat e) {
        prolongRepository.accepterProlongement(idProlong, e);
    }

    @Transactional
    public void refus(int idProlong, Etat e) {
        prolongRepository.refuserProlongement(idProlong, e);
    }

}