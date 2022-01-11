package com.proiect.proiect.model;


public class Bilet {
    private ZborItem zborItem;
    private int nrPasageri;
    private Persoana persoana;
    private String data;
    private String from;
    private String to;

    public Bilet(ZborItem zborItem, int nrPasageri, Persoana persoana, String data, String from, String to) {
        this.nrPasageri = nrPasageri;
        this.zborItem = zborItem;
        this.persoana = persoana;
        this.data = data;
        this.from = from;
        this.to = to;
    }

    public Bilet() {
    }

    public int getNrPasageri() {
        return this.nrPasageri;
    }

    public Persoana getPersoana() {
        return this.persoana;
    }

    public ZborItem getZborItem() {
        return this.zborItem;
    }

    public String getData() {
        return this.data;
    }

    public void setZborItem(ZborItem zborItem) {
        this.zborItem = zborItem;
    }

    public void setNrPasageri(int nrPasageri) {
        this.nrPasageri = nrPasageri;
    }

    public void setPersoana(Persoana persoana) {
        this.persoana = persoana;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
