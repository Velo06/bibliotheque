package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.time.LocalDate;

import com.example.demo.entity.Pret;

public interface PretRepository extends JpaRepository<Pret, Long> {
    // findAll()
    // findById(id)
    // save(Pret)
    // delete(id)

    // countQuotaAdherent
    @Query("SELECT COUNT(p) FROM Pret p WHERE p.typePret.id = 2 AND p.adherent.id = :id AND p.dateEmprunt = CURRENT_DATE")
    int countQuotaAdherent(@Param("id") Long idAdherent);

    // pretEnCoursAdherent
    @Query("SELECT p FROM Pret p WHERE p.adherent.id = :idAdherent AND p.dateRetourReel IS NULL")
    List<Pret> findPretsEnCoursByAdherent(@Param("idAdherent") Integer idAdherent);

    // getAllPretNonRendu
    @Query("SELECT p FROM Pret p WHERE dateRetourReel IS NULL")
    List<Pret> getAllPretNonRendu();

    // getDateRetourPrevuPret
    @Query("SELECT p.dateRetourPrevu FROM Pret p WHERE p.id = :id")
    LocalDate getDateRetourPrevu(@Param("id") int idPret);

    // saveRendu
    @Modifying
    @Query("UPDATE Pret p SET p.dateRetourReel = :reel WHERE p.id = :id")
    void saveRendu(@Param("reel") LocalDate date, @Param("id") int idPret);

}
