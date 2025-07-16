package com.example.demo.service;

import com.example.demo.repository.JourFerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.util.List;
import com.example.demo.entity.JourFerie;

public class JourFerieService {
    @Autowired
    private JourFerieRepository jourFerieRepository;

    public List<LocalDate> getJourFerie() {
        return jourFerieRepository.getJourFerie();
    }
}
