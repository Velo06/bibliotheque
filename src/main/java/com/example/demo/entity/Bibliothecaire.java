package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bibliothecaire")
public class Bibliothecaire {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_bibliothecaire;
    
    @Column(name = "pseudo") 
    private String pseudo;

    @Column(name = "mot_de_passe")
    private String mot_de_passe;

    // Getters et Setters
    public Integer getId_bibliothecaire() {
        return id_bibliothecaire;
    }

    public void setId_bibliothecaire(Integer id_bibliothecaire) {
        this.id_bibliothecaire = id_bibliothecaire;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }
}