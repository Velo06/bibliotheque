package com.example.demo.entity;

import java.time.LocalDate;

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

    @Column(name = "dateDebut", nullable = false)
    private LocalDate dateDebut;

    @Column(name = "dateFin", nullable = false)
    private LocalDate dateFin;

    // Constructeurs
    public Penalisation() {}

    public Penalisation(Adherent adherent, LocalDate dateDebut, LocalDate dateFin) {
        this.adherent = adherent;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
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

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "Penalisation{" +
                "id=" + id +
                ", adherent=" + (adherent != null ? adherent.getNom() + " " + adherent.getPrenom() : "null") +
                "dateDebut=" + dateDebut +
                "dateFin=" + dateFin +
                '}';
    }
    
}