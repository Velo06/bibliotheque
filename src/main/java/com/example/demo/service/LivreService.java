package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Livre;
import com.example.demo.repository.LivreRepository;
import java.util.List;
import java.util.Optional;

@Service
public class LivreService {
    @Autowired
    private LivreRepository livreRepository;

    public List<Livre> getAllLivre() {
        List<Livre> l = livreRepository.findAllWithThemeAndAuteur();
        return l;
    }

    public Optional<Livre> getById(Long id) {
        return livreRepository.findById(id);
    }
}
