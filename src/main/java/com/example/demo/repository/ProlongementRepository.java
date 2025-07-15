package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

import com.example.demo.entity.Prolongement;
import com.example.demo.entity.Etat;

public interface ProlongementRepository extends JpaRepository<Prolongement, Long> {
    // findAll()
    // findById(id)
    // save(Prolongement)
    // delete(id)

    @Query("SELECT COUNT(p) FROM Prolongement p WHERE p.pret.adherent.id = :idAdherent")
    int countProlongementsByAdherent(@Param("idAdherent") Long idAdherent);
   
    
    @Query("SELECT p FROM Prolongement p WHERE p.etat.id = 3")
    List<Prolongement> getProlongementEnAttente();

     // accpeter
    @Modifying
    @Transactional
    @Query("UPDATE Prolongement p SET p.etat = :etat WHERE p.id = :id")
    void accepterProlongement(@Param("id") int idProlong, @Param("etat") Etat etat);

    // refuser
    @Modifying
    @Transactional
    @Query("UPDATE Prolongement p SET p.etat = :etat WHERE p.id = :id")
    void refuserProlongement(@Param("id") int idProlong, @Param("etat") Etat etat);
}
