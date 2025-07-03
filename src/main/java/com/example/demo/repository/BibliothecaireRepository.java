package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Bibliothecaire;

public interface BibliothecaireRepository extends JpaRepository<Bibliothecaire, Integer> {
    
    Bibliothecaire findByPseudo(String pseudo);
}