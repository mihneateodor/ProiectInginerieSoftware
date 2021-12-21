package model;

public class Aeroport {
    private int idAeroport;
    private String numeAeroport;
    private String orasAeroport;
    private String taraAeroport;

    public Aeroport(){};

    public int getIdAeroport(){
        return this.idAeroport;
    }
    public String getNumeAeroport(){
        return this.numeAeroport;
    }
    public String getOrasAeroport(){
        return this.orasAeroport;
    }
    public String getTaraAeroport(){
        return this.taraAeroport;
    }
    public String toString(){
        String aeroport;
        aeroport = "ID " + this.idAeroport + ": " + this.numeAeroport + " din " + this.orasAeroport + ", " + this.taraAeroport;
        return aeroport;
    }
}
