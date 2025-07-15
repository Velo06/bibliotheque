package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Livre;
import java.util.List;
import java.util.Optional;

public interface LivreRepository extends JpaRepository<Livre, Long> {
    // findAll()
    // findById(id)
    // save(Livre)
    // delete(id)

    // findWithThemeAndAuteur()
    @Query("SELECT l FROM Livre l JOIN FETCH l.theme JOIN FETCH l.auteur")
    List<Livre> findAllWithThemeAndAuteur();

    // Récupérer un livre spécifique avec son thème et son auteur
    @Query("SELECT l FROM Livre l JOIN FETCH l.theme JOIN FETCH l.auteur WHERE l.id = :id")
    Optional<Livre> findByIdWithThemeAndAuteur(@Param("id") Long id);

    // getAgeResttriction
    @Query("SELECT l.ageRestriction FROM Livre l WHERE l.id = :id")
    int getAgeRestrictionLivre(@Param("id") Long idLivre);

}
