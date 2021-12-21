package model;

public class Persoana {
    private int idPersoana;
    private String numePersoana;
    private String emailPersoana;
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
    public String toString(){
        String persoana;
        persoana = "ID " + this.idPersoana + ": " + this.numePersoana + " - " + this.emailPersoana + " - " + this.parolaPersoana;
        return persoana;
    }
}
