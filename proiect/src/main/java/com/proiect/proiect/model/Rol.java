package com.proiect.proiect.model;

import javax.persistence.*;

@Entity
@Table (name = "Rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 10)
    private String name;

    public Rol(int idPersoana, String name) {
        this.id = idPersoana;
        this.name = name;
    }

    public Rol(int idPersoana) {
        this.id = idPersoana;
    }

    public Rol(String name) {
        this.name = name;
    }

    public Rol() {

    }

    public int getIdPersoana() {
        return id;
    }

    public void setIdPersoana(int idPersoana) {
        this.id = idPersoana;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
