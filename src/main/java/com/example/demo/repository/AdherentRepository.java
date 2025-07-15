package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;

import com.example.demo.entity.Adherent;

public interface AdherentRepository extends JpaRepository<Adherent, Long> {
    // findAll()
    // findById(id)
    // save(Adherent)
    // delete(id)

    // findWithType()
    @Query("SELECT a FROM Adherent a JOIN FETCH a.typeAdherent t")
    Adherent findWithType();

    // findByIdWithType()
    @Query("SELECT a FROM Adherent a JOIN FETCH a.typeAdherent t WHERE a.id = :id")
    Adherent findByIdWithType(@Param("id") Long id);

    // checkLogin
    @Query("SELECT COUNT(a) > 0 FROM Adherent a WHERE a.nom = :nom AND a.mdp = :mdp")
    boolean checkLogin(@Param("nom") String nom, @Param("mdp") String mdp);

    // checkSanction(id)
    @Query("SELECT CASE WHEN COUNT(p) = 0 THEN true ELSE false END " +
           "FROM Penalisation p " +
           "WHERE p.adherent.id = :adherentId")
    boolean isAdherentNotSanctionne(@Param("adherentId") Long adherentId);

    // checkAbonne()
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END " +
           "FROM Abonnement a " +
           "WHERE a.adherent.id = :adherentId " +
           "AND CURRENT_DATE BETWEEN a.dateDebut AND a.dateFin")
    boolean isAdherentAbonne(@Param("adherentId") Long adherentId);

       // getQuotaLivre
       @Query("SELECT t.quotaLivre FROM Adherent a JOIN a.typeAdherent t WHERE a.id = :id")
       int getQuotaLivre(@Param("id") Long idAdherent);

       // getIdAdherentByNom
       @Query("SELECT a.id FROM Adherent a WHERE a.nom = :nom")
       Long getIdAdherentByNom(@Param("nom") String nom);

       @Query("SELECT a.typeAdherent.quotaReservation FROM Adherent a WHERE a.id = :idAdherent")
       int getQuotaReservation(@Param("idAdherent") int idAdherent);

       // getAgeAdherent
       @Query("SELECT a.dateNaissance FROM Adherent a WHERE a.id = :id")
       LocalDate getAgeAdherent(@Param("id") Long idAdh);

       // getQuotaProlongement
       @Query("SELECT a.typeAdherent.quotaProlongement FROM Adherent a WHERE a.id = :idAdherent")
       int getQuotaProlongement(@Param("idAdherent") int idAdherent);
}
