package com.proiect.proiect.model;


import javax.persistence.*;

@Entity
@Table(name="persoana")
public class Persoana {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPersoana;
    @Column(nullable = false, unique = true)
    private String numePersoana;
    @Column(nullable = false, unique = true)
    private String emailPersoana;
    @Column(nullable = false)
    private String parolaPersoana;

    public Persoana(){}

    public int getIdPersoana(){
        return this.idPersoana;
    }
    public String getNumePersoana(){
        return this.numePersoana;
    }
    public String getEmailPersoana(){
        return this.emailPersoana;
    }
    public String getParolaPersoana(){
        return this.parolaPersoana;
    }

    public void setIdPersoana(int idPersoana) {
        this.idPersoana = idPersoana;
    }

    public void setNumePersoana(String numePersoana) {
        this.numePersoana = numePersoana;
    }

    public void setEmailPersoana(String emailPersoana) {
        this.emailPersoana = emailPersoana;
    }

    public void setParolaPersoana(String parolaPersoana) {
        this.parolaPersoana = parolaPersoana;
    }

    public String toString(){
        String persoana;
        persoana = "ID " + this.idPersoana + ": " + this.numePersoana + " - " + this.emailPersoana + " - " + this.parolaPersoana;
        return persoana;
    }
}
