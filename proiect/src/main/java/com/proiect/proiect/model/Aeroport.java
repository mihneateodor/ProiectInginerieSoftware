package com.proiect.proiect.model;

import javax.persistence.*;

@Entity
@Table(name="aeroport")
public class Aeroport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAeroport;
    @Column(nullable = false, unique = true)
    private String numeAeroport;
    @Column(nullable = false)
    private String orasAeroport;
    @Column(nullable = false)
    private String taraAeroport;

    public Aeroport(){}

    public int getIdAeroport(){
        return this.idAeroport;
    }
    public void setIdAeroport(int idAeroport){
        this.idAeroport=idAeroport;
    }
    public String getNumeAeroport(){
        return this.numeAeroport;
    }
    public void setNumeAeroport(String numeAeroport){
        this.numeAeroport=numeAeroport;
    }
    public String getOrasAeroport(){
        return this.orasAeroport;
    }
    public void setOrasAeroport(String orasAeroport){
        this.orasAeroport=orasAeroport;
    }
    public String getTaraAeroport(){
        return this.taraAeroport;
    }
    public void setTaraAeroport( String taraAeroport){
        this.taraAeroport=taraAeroport;
    }
    public String toString(){
        String aeroport;
        aeroport = "ID " + this.idAeroport + ": " + this.numeAeroport + " din " + this.orasAeroport + ", " + this.taraAeroport;
        return aeroport;
    }
}
