package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Penalisation;
import com.example.demo.repository.PenalisationRepository;
import java.util.List;

@Service
public class PenalisationService {
    
    @Autowired
    private PenalisationRepository penaliteRepository;

    public Penalisation savePenalite(Penalisation p) {
        return penaliteRepository.save(p);
    }
}