package model;

import java.sql.Time;

public class Zbor {
    private int idZbor;
    private int idAeroportPlecare;
    private int idAeroportSosire;
    private Time oraPlecare;
    private int durataOre;
    private int durataMin;
    private int pret;
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
    public String toString(){
        String zbor;
        zbor = "ID " + this.idZbor + ": de la aeroportul " + this.idAeroportPlecare + " catre aeroportul " + this.idAeroportSosire + " la ora " + this.oraPlecare + ", durata de " +
                this.durataOre + " ore si " + this.durataMin + " minute, cu compania " + this.companie + " la pretul de " + this.pret + "EURO.";
        return zbor;
    }
}
