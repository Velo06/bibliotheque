package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "theme")
public class Theme {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "theme", length = 100, nullable = false, unique = true)
    private String theme;

    // Constructeurs
    public Theme() {}

    public Theme(String theme) {
        this.theme = theme;
    }

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return "Theme{" + "id=" + id + ", theme='" + theme + '\'' + '}';
    }
}