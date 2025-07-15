package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.Etat;
import com.example.demo.repository.ReservationRepository;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationService {
    
    @Autowired
    private ReservationRepository resaRepository;
    
    public void saveReservation(Reservation resa) {
        resaRepository.save(resa);
    }

    public int countResaAdherent(int idAdh) {
        return resaRepository.countReservationsEnAttente(idAdh);
    }

    public List<Reservation> getResaEnAttente() {
        return resaRepository.getResaEnAttente();
    }

    @Transactional
    public void accept(int idResa, Etat e) {
        resaRepository.accepterReservation(idResa, e);
    }

    @Transactional
    public void refus(int idResa, Etat e) {
        resaRepository.refuserReservation(idResa, e);
    }
}