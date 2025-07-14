package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "adherent")
public class Adherent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nom", length = 100, nullable = false)
    private String nom;
    
    @Column(name = "prenom", length = 200, nullable = false)
    private String prenom;
    
    @Column(name = "dateNaissance", nullable = false)
    private LocalDate dateNaissance;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTypeAdherent", nullable = false)
    private TypeAdherent typeAdherent;
    
    @Column(name = "mdp", length = 100, nullable = false)
    private String mdp;

    // Constructeurs
    public Adherent() {
        // Constructeur par défaut requis par JPA
    }

    public Adherent(String nom, String prenom, LocalDate dateNaissance, TypeAdherent typeAdherent, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.typeAdherent = typeAdherent;
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

    public TypeAdherent getTypeAdherent() {
        return typeAdherent;
    }

    public void setTypeAdherent(TypeAdherent typeAdherent) {
        this.typeAdherent = typeAdherent;
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
        return "Adherent{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", typeAdherent=" + (typeAdherent != null ? typeAdherent.getType() : "null") +
                '}';
    }
}