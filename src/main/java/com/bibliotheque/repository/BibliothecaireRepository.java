package com.exemple.repository;

import com.exemple.entity.Bibliothecaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliothecaireRepository extends JpaRepository<Bibliothecaire, Long> {
    Bibliothecaire findByPseudo(String pseudo);
}