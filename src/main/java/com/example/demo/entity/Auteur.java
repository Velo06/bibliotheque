package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "auteur")
public class Auteur {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nomAuteur", length = 100, nullable = false)
    private String nomAuteur;

    // Constructeurs
    public Auteur() {}

    public Auteur(String nomAuteur) {
        this.nomAuteur = nomAuteur;
    }

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomAuteur() {
        return nomAuteur;
    }

    public void setNomAuteur(String nomAuteur) {
        this.nomAuteur = nomAuteur;
    }

    @Override
    public String toString() {
        return "Auteur{" + "id=" + id + ", nomAuteur='" + nomAuteur + '\'' + '}';
    }
}