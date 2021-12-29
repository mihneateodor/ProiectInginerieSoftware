package com.proiect.proiect.model;

import com.proiect.proiect.administrate.CautareZborCreareBilet;

import javax.persistence.*;
import java.sql.Time;


@Entity
@Table(name="zbor")
public class Zbor extends ZborItem implements Comparable<Zbor>{
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

    public Zbor(int idZbor, int idAeroportPlecare, int idAeroportSosire, Time oraPlecare, int durataMin, int durataOre, int pret, String companie){
        this.idZbor=idZbor;
        this.idAeroportPlecare=idAeroportPlecare;
        this.idAeroportSosire=idAeroportSosire;
        this.oraPlecare=oraPlecare;
        this.durataMin=durataMin;
        this.durataOre=durataOre;
        this.pret=pret;
        this.companie=companie;
    }

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
        Aeroport aeroport1 = CautareZborCreareBilet.findAeroportById(this.idAeroportPlecare);
        Aeroport aeroport2 = CautareZborCreareBilet.findAeroportById(this.idAeroportSosire);
        String zbor;
        zbor = "De la aeroportul " + aeroport1.toString() + " catre aeroportul " + aeroport2.toString() + " la ora " + this.oraPlecare + ", durata de " +
                this.durataOre + " ore si " + this.durataMin + " minute, cu compania " + this.companie + " la pretul de " + this.pret + " EURO.";
        return zbor;
    }

    @Override
    public int compareTo(Zbor o) {
        Integer idZbor1= this.idZbor;
        Integer idZbor2= o.idZbor;
        return idZbor1.compareTo(idZbor2);
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
