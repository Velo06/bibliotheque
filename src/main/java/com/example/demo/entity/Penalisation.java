package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "penalisation")
public class Penalisation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAdherent", nullable = false)
    private Adherent adherent;

    // Constructeurs
    public Penalisation() {}

    public Penalisation(Adherent adherent) {
        this.adherent = adherent;
    }

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }

    @Override
    public String toString() {
        return "Penalisation{" +
                "id=" + id +
                ", adherent=" + (adherent != null ? adherent.getNom() + " " + adherent.getPrenom() : "null") +
                '}';
    }
    
}