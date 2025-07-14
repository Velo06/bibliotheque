package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // findAll()
    // findById(id)
    // save(Pret)
    // delete(id)
    
    // checkLogin
    @Query("SELECT COUNT(a) > 0 FROM Bibliothecaire a WHERE a.nom = :nom AND a.mdp = :mdp")
    boolean checkLogin(@Param("nom") String nom, @Param("mdp") String mdp);
}
