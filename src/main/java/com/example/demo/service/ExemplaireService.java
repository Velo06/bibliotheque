package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Exemplaire;
import com.example.demo.repository.ExemplaireRepository;

import java.util.List;

@Service
public class ExemplaireService {
    
    @Autowired
    private ExemplaireRepository exemplaireRepository;
    
    public int exemplaireDispo(Long id) {
        return exemplaireRepository.countExemplairesDisponibles(id);
    }
}