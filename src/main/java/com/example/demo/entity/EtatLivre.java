package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "etatlivre")
public class EtatLivre {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "etat", length = 100, nullable = false, unique = true)
    private String etat;

    // Constructeurs
    public EtatLivre() {}

    public EtatLivre(String etat) {
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

    @Override
    public String toString() {
        return "EtatLivre{" + "id=" + id + ", etat='" + etat + '\'' + '}';
    }
}