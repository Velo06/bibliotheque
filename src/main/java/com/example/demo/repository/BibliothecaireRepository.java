package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Bibliothecaire;

public interface BibliothecaireRepository extends JpaRepository<Bibliothecaire, Long> {
    // findAll()
    // findById(id)
    // save(Pret)
    // delete(id)

    @Query("SELECT COUNT(b) > 0 FROM Bibliothecaire b WHERE b.nom = :nom AND b.mdp = :mdp")
    boolean checkLogin(@Param("nom") String nom, @Param("mdp") String mdp);
    
}
    