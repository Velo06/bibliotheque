package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
public class Reservation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAdherent", nullable = false)
    private Adherent adherent;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idLivre", nullable = false)
    private Livre livre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEtat", nullable = false)
    private Etat etat;
    
    @Column(name = "dateDemande", nullable = false)
    private LocalDate dateDemande;

    @Column(name = "dateReservation", nullable = false)
    private LocalDate dateReservation;

    // Constructeurs
    public Reservation() {
        this.dateDemande = LocalDate.now(); // Valeur par défaut
        this.dateReservation = LocalDate.now(); // Valeur par défaut
    }

    public Reservation(Adherent adherent, Livre livre, Etat etat) {
        this.adherent = adherent;
        this.livre = livre;
        this.etat = etat;
        this.dateDemande = LocalDate.now();
        this.dateReservation = LocalDate.now();
    }

    public Reservation(Adherent adherent, Livre livre, Etat etat, LocalDate dateDemande, LocalDate dateReservation) {
        this.adherent = adherent;
        this.livre = livre;
        this.etat = etat;
        this.dateDemande = dateDemande;
        this.dateReservation = dateReservation;
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

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public LocalDate getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(LocalDate date) {
        this.dateDemande = date;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", adherent=" + (adherent != null ? adherent.getNom() + " " + adherent.getPrenom() : "null") +
                ", livre=" + (livre != null ? livre.getTitre() : "null") +
                ", etat=" + (etat != null ? etat.getEtat() : "null") +
                ", dateDemande=" + dateDemande +
                ", dateReservation=" + dateReservation +
                '}';
    }
}