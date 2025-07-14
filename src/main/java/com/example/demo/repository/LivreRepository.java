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

    // @Query("SELECT NEW com.example.demo.entity.Livre("
    //     + "l.id, l.titre, a.nom, t.theme, l.age, l.nbrExemplaire, "
    //     + "SUM(CASE WHEN e.etat.etat = 'disponible' THEN 1 ELSE 0 END), "
    //     + "SUM(CASE WHEN e.etat.etat = 'reserve' THEN 1 ELSE 0 END), "
    //     + "SUM(CASE WHEN e.etat.etat = 'en pret' THEN 1 ELSE 0 END)) "
    //     + "FROM Livre l "
    //     + "JOIN l.auteur a "
    //     + "JOIN l.theme t "
    //     + "LEFT JOIN l.exemplaires e " 
    //     + "GROUP BY l.id")
    // List<Livre> findAllLivresWithEtat();

    // checkDispo
    // livre, exemplaire, etat ????
}
