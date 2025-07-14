package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "typeadherent")
public class TypeAdherent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "type", length = 100, nullable = false, unique = true)
    private String type;

    @Column(name = "dureePret", nullable = false)
    private Integer dureePret;
    
    @Column(name = "dureePenalite", nullable = false)
    private Integer dureePenalite;
    
    @Column(name = "quotaLivre", nullable = false)
    private Integer quotaLivre;

    @Column(name = "quotaReservation", nullable = false)
    private Integer quotaReservation;

    @Column(name = "quotaProlongement", nullable = false)
    private Integer quotaProlongement;

    // Constructeurs
    public TypeAdherent() {
        // Constructeur par défaut requis par JPA
    }

    public TypeAdherent(String type, Integer dureePret, Integer dureePenalite, Integer quotaLivre, Integer quotaReservation, Integer quotaProlongement) {
        this.type = type;
        this.dureePret = dureePret;
        this.dureePenalite = dureePenalite;
        this.quotaLivre = quotaLivre;
        this.quotaReservation = quotaReservation;
        this.quotaProlongement = quotaProlongement;
    }

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDureePret() {
        return dureePret;
    }

    public void setDureePret(Integer dureePret) {
        this.dureePret = dureePret;
    }

    public Integer getDureePenalite() {
        return dureePenalite;
    }

    public void setDureePenalite(Integer dureePenalite) {
        this.dureePenalite = dureePenalite;
    }

    public Integer getQuotaLivre() {
        return quotaLivre;
    }

    public void setQuotaLivre(Integer quota) {
        this.quotaLivre = quota;
    }

    public Integer getQuotaReservation() {
        return quotaReservation;
    }

    public void setQuotaReservation(Integer quota) {
        this.quotaReservation = quota;
    }

    public Integer getQuotaProlongement() {
        return quotaProlongement;
    }

    public void setQuotaProlongement(Integer quota) {
        this.quotaProlongement = quota;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "TypeAdherent{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", dureePenalite=" + dureePenalite +
                ", quotaLivre=" + quotaLivre +
                ", quotaReservation=" + quotaReservation +
                ", quotaProlongement=" + quotaProlongement +
                '}';
    }
}