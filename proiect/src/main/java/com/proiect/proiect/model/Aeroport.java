package com.proiect.proiect.model;

import javax.persistence.*;

@Entity
@Table(name="aeroport")
public class Aeroport implements Comparable<Aeroport>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAeroport;
    @Column(nullable = false, unique = true,  length = 100)
    private String numeAeroport;
    @Column(nullable = false ,  length = 100)
    private String orasAeroport;
    @Column(nullable = false ,  length = 100)
    private String taraAeroport;

    public Aeroport(int idAeroport, String numeAeroport, String orasAeroport, String taraAeroport){
        this.idAeroport=idAeroport;
        this.numeAeroport=numeAeroport;
        this.orasAeroport=orasAeroport;
        this.taraAeroport=taraAeroport;
    }
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
        aeroport =  "\"" + this.numeAeroport + "\""  + " din " + this.orasAeroport + ", " + this.taraAeroport;
        return aeroport;
    }

    @Override
    public int compareTo(Aeroport o) {
        Integer idAeroport1= this.idAeroport;
        Integer idAeroport2 = o.idAeroport;
        return idAeroport1.compareTo(idAeroport2);
    }
}
