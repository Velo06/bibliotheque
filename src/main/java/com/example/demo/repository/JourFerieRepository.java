package com.example.demo.repository;

import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.time.LocalDate;

import com.example.demo.entity.JourFerie;

public interface JourFerieRepository extends JpaRepository<JourFerie, Long> {
    // findAll()
    // findById(id)
    // save(Pret)
    // delete(id)

    // countQuotaAdherent
    @Query("SELECT jf FROM JourFerie jf")
    List<LocalDate> getJourFerie();

   
}
