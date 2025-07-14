package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

import com.example.demo.entity.Prolongement;

public interface ProlongementRepository extends JpaRepository<Prolongement, Long> {
    // findAll()
    // findById(id)
    // save(Prolongement)
    // delete(id)

}
