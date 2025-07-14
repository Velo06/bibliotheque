package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prolongement")
public class Prolongement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPret", nullable = false)
    private Pret pret;

    @Column(name = "dateFin", nullable = false)
    private LocalDate dateFin;

    public Prolongement() {}

    public Prolongement(Pret pret, LocalDate dateFin) {
        this.pret = pret;
        this.dateFin = dateFin;
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

    @Override
    public String toString() {
        return "Prolongement{" +
                "id=" + id +
                ", pretId=" + (pret != null ? pret.getId() : "null") +
                ", dateFin=" + dateFin +
                '}';
    }
}
