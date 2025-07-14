package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pret")
public class Pret {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTypePret", nullable = false)
    private TypePret typePret;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAdherent", nullable = false)
    private Adherent adherent;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idLivre", nullable = false)
    private Livre livre;
    
    @Column(name = "dateRetourPrevu", nullable = false)
    private LocalDate dateRetourPrevu;

    @Column(name = "dateEmprunt", nullable = false)
    private LocalDate dateEmprunt;

    @Column(name = "dateRetourReel")
    private LocalDate dateRetourReel;

    // Constructeurs
    public Pret() {
        // Constructeur par défaut requis par JPA
    }

    public Pret(TypePret typePret, Adherent adherent, Livre livre, LocalDate dateRetourPrevu, LocalDate dateEmprunt, LocalDate dateRetourReel) {
        this.typePret = typePret;
        this.adherent = adherent;
        this.livre = livre;
        this.dateRetourPrevu = dateRetourPrevu;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourReel = dateRetourReel;
    }

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TypePret getTypePret() {
        return typePret;
    }

    public void setTypePret(TypePret typePret) {
        this.typePret = typePret;
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

    public LocalDate getDateRetourPrevu() {
        return dateRetourPrevu;
    }

    public void setDateRetourPrevu(LocalDate dateRetourPrevu) {
        this.dateRetourPrevu = dateRetourPrevu;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetourReel() {
        return dateRetourReel;
    }

    public void setDateRetourReel(LocalDate dateRetourReel) {
        this.dateRetourReel = dateRetourReel;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "Pret{" +
                "id=" + id +
                ", typePret=" + (typePret != null ? typePret.getType() : "null") +
                ", adherent=" + (adherent != null ? adherent.getNom() + " " + adherent.getPrenom() : "null") +
                ", livre=" + (livre != null ? livre.getTitre() : "null") +
                ", dateRetourPrevu=" + dateRetourPrevu +
                ", dateEmprunt=" + dateEmprunt +
                ", dateRetourReel=" + dateRetourReel +
                '}';
    }
}