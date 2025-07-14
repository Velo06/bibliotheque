package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Reservation;
import com.example.demo.repository.ReservationRepository;

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
}