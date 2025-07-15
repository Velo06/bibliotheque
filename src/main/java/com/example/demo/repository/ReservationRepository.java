package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Etat;
import com.example.demo.entity.Reservation;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // findAll()
    // findById(id)
    // save(Pret)
    // delete(id)
    
    // checkLogin
    @Query("SELECT COUNT(a) > 0 FROM Bibliothecaire a WHERE a.nom = :nom AND a.mdp = :mdp")
    boolean checkLogin(@Param("nom") String nom, @Param("mdp") String mdp);

    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.adherent.id = :idAdherent AND r.etat.etat = 'en attente'")
    int countReservationsEnAttente(@Param("idAdherent") int idAdherent);

    // resa en attente
    @Query("SELECT r FROM Reservation r WHERE r.etat.etat = 'en attente'")
    List<Reservation> getResaEnAttente();

    // accpeter
    @Modifying
    @Transactional
    @Query("UPDATE Reservation r SET r.etat = :etat WHERE r.id = :id")
    void accepterReservation(@Param("id") int idResa, @Param("etat") Etat etat);

    // refuser
    @Modifying
    @Transactional
    @Query("UPDATE Reservation r SET r.etat = :etat WHERE r.id = :id")
    void refuserReservation(@Param("id") int idResa, @Param("etat") Etat etat);

}
