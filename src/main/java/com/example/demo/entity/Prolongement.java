package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prolongement")
public class Prolongement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY) // Remplace OneToOne si un prêt peut être prolongé plusieurs fois
    @JoinColumn(name = "idPret", nullable = false)
    private Pret pret;

    @Column(name = "dateFin", nullable = false)
    private LocalDate dateFin;

    @Column(name = "dateDemande", nullable = false)
    private LocalDate dateDemande;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEtat", nullable = false)
    private Etat etat;

    public Prolongement() {}

    public Prolongement(Pret pret, LocalDate dateFin, LocalDate dateDemande, Etat etat) {
        this.pret = pret;
        this.dateFin = dateFin;
        this.dateDemande = dateDemande;
        this.etat = etat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pret getPret() {
        return pret;
    }

    public void setPret(Pret pret) {
        this.pret = pret;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public LocalDate getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(LocalDate dateDemande) {
        this.dateDemande = dateDemande;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Prolongement{" +
                "id=" + id +
                ", pretId=" + (pret != null ? pret.getId() : "null") +
                ", dateFin=" + dateFin +
                ", dateDemande=" + dateDemande +
                ", etat=" + (etat != null ? etat.getEtat() : "null") +
                '}';
    }
}
