package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.time.LocalDate;

import com.example.demo.entity.Penalisation;

public interface PenalisationRepository extends JpaRepository<Penalisation, Long> {
    // findAll()
    // findById(id)
    // save(Penalisation)
    // delete(id)

}
