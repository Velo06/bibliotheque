package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "exemplaire") // Nom exact de la table
public class Exemplaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEtat", nullable = false) // Correction: "idEtat" au lieu de "id_etat"
    private EtatLivre etat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idLivre", nullable = false) // Correction: "idLivre" au lieu de "id_livre"
    private Livre livre;

    // Constructeurs
    public Exemplaire() {
        // Constructeur par défaut requis par JPA
    }

    public Exemplaire(EtatLivre etat, Livre livre) {
        this.etat = etat;
        this.livre = livre;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EtatLivre getEtat() {
        return etat;
    }

    public void setEtat(EtatLivre etat) {
        this.etat = etat;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    @Override
    public String toString() {
        return "Exemplaire{" +
                "id=" + id +
                ", etat=" + (etat != null ? etat.getId() : "null") +
                ", livre=" + (livre != null ? livre.getId() : "null") +
                '}';
    }
}