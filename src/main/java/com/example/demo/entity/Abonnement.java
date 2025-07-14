package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "abonnement")
public class Abonnement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAdherent", nullable = false)
    private Adherent adherent;
    
    @Column(name = "montant", nullable = false, precision = 10, scale = 2)
    private BigDecimal montant;
    
    @Column(name = "dateDebut", nullable = false)
    private LocalDate dateDebut;
    
    @Column(name = "dateFin", nullable = false)
    private LocalDate dateFin;

    // Constructeurs
    public Abonnement() {}

    public Abonnement(Adherent adherent, BigDecimal montant, LocalDate dateDebut, LocalDate dateFin) {
        this.adherent = adherent;
        this.montant = montant;
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

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
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
    
    // Méthode utilitaire pour vérifier si l'abonnement est actif
    public boolean estActif() {
        LocalDate aujourdhui = LocalDate.now();
        return !aujourdhui.isBefore(dateDebut) && !aujourdhui.isAfter(dateFin);
    }
}