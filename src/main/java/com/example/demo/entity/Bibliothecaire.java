package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bibliothecaire")
public class Bibliothecaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nom", length = 100, nullable = false)
    private String nom;
    
    @Column(name = "prenom", length = 200, nullable = false)
    private String prenom;
    
    @Column(name = "dateNaissance", nullable = false)
    private LocalDate dateNaissance;
    
    @Column(name = "mdp", length = 100, nullable = false)
    private String mdp;

    // Constructeurs
    public Bibliothecaire() {
        // Constructeur par défaut requis par JPA
    }

    public Bibliothecaire(String nom, String prenom, LocalDate dateNaissance, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.mdp = mdp;
    }

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "Bibliothecaire{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
                '}';
    }
}
