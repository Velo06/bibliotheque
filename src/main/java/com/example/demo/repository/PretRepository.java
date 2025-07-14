package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Pret;

public interface PretRepository extends JpaRepository<Pret, Long> {
    // findAll()
    // findById(id)
    // save(Pret)
    // delete(id)

    // findWithDetails()
    // @Query("SELECT * FROM Pret JOIN typePret ON typePret.id = Pret.idTypePret JOIN Adherent ON Adherent.id = Pret.idAdherent JOIN Livre ON Livre.id = Pret.idLivre")
    // Pret findWithDetails();

    // findByIdWithDetails(id)
    // @Query("SELECT * FROM Pret JOIN typePret ON typePret.id = Pret.idTypePret JOIN Adherent ON Adherent.id = Pret.idAdherent JOIN Livre ON Livre.id = Pret.idLivre WHERE Pret.id = :id")
    // Pret findByIdWithDetails(@Param("id") Long id);

    // countQuotaAdherent
    @Query("SELECT COUNT(p) FROM Pret p WHERE p.typePret.id = 2 AND p.adherent.id = :id AND p.dateEmprunt = CURRENT_DATE")
    int countQuotaAdherent(@Param("id") Long idAdherent);
}
