package com.proiect.proiect.model;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name="zbor")
public class Zbor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idZbor;
    @Column(nullable = false)
    private int idAeroportPlecare;
    @Column(nullable = false)
    private int idAeroportSosire;
    @Column(nullable = false)
    private Time oraPlecare;
    @Column(nullable = false)
    private int durataOre;
    @Column(nullable = false)
    private int durataMin;
    @Column(nullable = false)
    private int pret;
    @Column(nullable = false)
    private String companie;

    public Zbor(){}

    public int getIdZbor(){
        return this.idZbor;
    }
    public int getIdAeroportPlecare(){
        return this.idAeroportPlecare;
    }
    public int getIdAeroportSosire(){
        return this.idAeroportSosire;
    }
    public Time getOraPlecare(){
        return this.oraPlecare;
    }
    public int getDurataOre(){
        return this.durataOre;
    }
    public int getDurataMin(){
        return this.durataMin;
    }
    public int getPret(){
        return this.pret;
    }
    public String getCompanie(){
        return this.companie;
    }

    public void setIdAeroportPlecare(int idAeroportPlecare) {
        this.idAeroportPlecare = idAeroportPlecare;
    }
    public void setIdAeroportSosire(int idAeroportSosire) {
        this.idAeroportSosire = idAeroportSosire;
    }
    public void setOraPlecare(Time oraPlecare) {
        this.oraPlecare = oraPlecare;
    }
    public void setDurataOre(int durataOre) {
        this.durataOre = durataOre;
    }
    public void setDurataMin(int durataMin) {
        this.durataMin = durataMin;
    }
    public void setPret(int pret) {
        this.pret = pret;
    }
    public void setCompanie(String companie) {
        this.companie = companie;
    }

    public String toString(){
        String zbor;
        zbor = "ID " + this.idZbor + ": de la aeroportul " + this.idAeroportPlecare + " catre aeroportul " + this.idAeroportSosire + " la ora " + this.oraPlecare + ", durata de " +
                this.durataOre + " ore si " + this.durataMin + " minute, cu compania " + this.companie + " la pretul de " + this.pret + "EURO.";
        return zbor;
    }
}
