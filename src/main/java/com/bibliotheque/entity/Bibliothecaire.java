package com.exemple.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "Bibliothecaire", 
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "pseudo")
       })
public class Bibliothecaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bibliothecaire")
    private Long id;

    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String pseudo;

    @NotBlank
    @Column(name = "mot_de_passe", nullable = false, length = 255)
    private String motDePasse;

    public Bibliothecaire() {}

    public Bibliothecaire(String pseudo, String motDePasse) {
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
    }

    public Long getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}