package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.TypeAdherent;

public interface TypeAdherentRepository extends JpaRepository<TypeAdherent, Long> {
    // findAll()
    // findById(id)
    // save(TypeAdherent)
    // delete(id)
}
