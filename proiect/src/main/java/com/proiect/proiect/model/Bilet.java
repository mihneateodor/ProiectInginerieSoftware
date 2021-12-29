package com.proiect.proiect.model;

import java.sql.Date;

public class Bilet {
    private ZborItem zborItem;
    private int nrPasageri;
    private Persoana persoana;
    private Date dataDus;
    private Date dataIntors;

    public Bilet(ZborItem zborItem, int nrPasageri, Persoana persoana, Date dataDus, Date dataIntors){
        this.nrPasageri=nrPasageri;
        this.zborItem=zborItem;
        this.persoana=persoana;
        this.dataDus = dataDus;
        this.dataIntors= dataIntors;
    }
    public Bilet(){}

    public int getNrPasageri(){return this.nrPasageri;}
    public Persoana getPersoana() {return this.persoana;}
    public ZborItem getZborItem() {return this.zborItem;}
    public Date getDataDus() { return this.dataDus;}
    public Date getDataIntors() { return this.dataIntors;}
    public void setZborItem(ZborItem zborItem) {this.zborItem=zborItem;}
    public void setNrPasageri(int nrPasageri) {this.nrPasageri=nrPasageri;}
    public void setPersoana(Persoana persoana) { this.persoana=persoana;}
    public void setDataDus(Date dataDus) { this.dataDus=dataDus;}
    public void setDataIntors(Date dataIntors) {this.dataIntors=dataIntors;}
}
