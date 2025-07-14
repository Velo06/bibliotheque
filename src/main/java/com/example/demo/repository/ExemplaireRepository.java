package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Exemplaire;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, Long> {
    // findAll()
    // findById(id)
    // save(Adherent)
    // delete(id)

    @Query("SELECT COUNT(e) FROM Exemplaire e WHERE e.livre.id = :idLivre AND e.etat.etat = 'disponible'")
    int countExemplairesDisponibles(@Param("idLivre") Long idLivre);

}
