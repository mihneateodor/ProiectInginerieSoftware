package com.example.accessingdatamysql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Aeroport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAeroport;

    private String numeAeroport;
    private String orasAeroport;
    private String taraAeroport;

    public Integer getIdAeroport() {
        return idAeroport;
    }

    public void setIdAeroport(Integer idAeroport) {
        this.idAeroport = idAeroport;
    }

    public String getNumeAeroport() {
        return numeAeroport;
    }

    public void setNumeAeroport(String numeAeroport) {
        this.numeAeroport = numeAeroport;
    }

    public String getOrasAeroport() {
        return orasAeroport;
    }

    public void setOrasAeroport(String orasAeroport) {
        this.orasAeroport = orasAeroport;
    }

    public String getTaraAeroport() {
        return taraAeroport;
    }

    public void setTaraAeroport(String taraAeroport) {
        this.taraAeroport = taraAeroport;
    }
}