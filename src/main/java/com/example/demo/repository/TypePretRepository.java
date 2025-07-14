package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.TypePret;

public interface TypePretRepository extends JpaRepository<TypePret, Long> {
    // findAll()
    // findById(id)
    // save(TypePret)
    // delete(id)
}
