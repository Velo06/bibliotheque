package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "etat")
public class Etat {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "etat", nullable = false)
    private String etat;

    // Constructeurs
    public Etat() {}

    public Etat(String etat) {
        this.etat = etat;
    }

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}