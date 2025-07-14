package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "typepret")
public class TypePret {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "type", length = 100, nullable = false, unique = true)
    private String type;

    // Constructeurs
    public TypePret() {
        // Constructeur par défaut requis par JPA
    }

    public TypePret(String type) {
        this.type = type;
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

    // Méthode toString()
    @Override
    public String toString() {
        return "TypePret{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}