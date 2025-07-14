package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "livre")
public class Livre {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(
        mappedBy = "livre",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Exemplaire> exemplaires = new ArrayList<>();
    
    @Column(name = "titre", length = 100, nullable = false)
    private String titre;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAuteur", nullable = false)
    private Auteur auteur;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTheme", nullable = false)
    private Theme theme;
    
    @Column(name = "age", nullable = false)
    private Integer ageRestriction;
    
    @Column(name = "nbrExemplaire", nullable = false)
    private Integer nbrExemplaire;

    private int disponibles;
    private int reserves;
    private int enPret;

    // Constructeurs
    public Livre() {}

    public Livre(String titre, Auteur auteur, Theme theme, Integer ageRestriction, Integer nbrExemplaire, int disponibles, int reserves, int enPret) {
        this.titre = titre;
        this.auteur = auteur;
        this.theme = theme;
        this.ageRestriction = ageRestriction;
        this.nbrExemplaire = nbrExemplaire;
        this.disponibles = disponibles;
        this.reserves = reserves;
        this.enPret = enPret;
    }

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Integer getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(Integer ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public Integer getNbrExemplaire() {
        return nbrExemplaire;
    }

    public void setNbrExemplaire(Integer nbrExemplaire) {
        this.nbrExemplaire = nbrExemplaire;
    }

    public int getDisponibles() { return disponibles; }
    public int getReserves() { return reserves; }
    public int getEnPret() { return enPret; }

    @Override
    public String toString() {
        return "Livre{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", auteur=" + (auteur != null ? auteur.getNomAuteur() : "null") +
                ", theme=" + (theme != null ? theme.getTheme() : "null") +
                ", ageRestriction=" + ageRestriction +
                ", nbrExemplaire=" + nbrExemplaire +
                '}';
    }
    
    // Méthode utilitaire pour vérifier si un livre est adapté à un âge
    public boolean isAgeAppropriate(int ageAdherent) {
        return ageAdherent >= this.ageRestriction;
    }
}