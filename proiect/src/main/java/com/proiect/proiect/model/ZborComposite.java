package com.proiect.proiect.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class ZborComposite extends ZborItem{

    private List<ZborItem> lista;

    private int durataOre;
    private int durataMin;
    private int pret;
    private int oreAdunate;
    private Time oraPlecare;

    public ZborComposite (){
        lista=new ArrayList<>();
    }

    public void add(ZborItem item){
        lista.add(item);
        this.setDurataMin();
        this.setDurataOre();
        this.setOraPlecare();
        this.getPret();
    }
    public void remove(ZborItem item){
        lista.remove(item);
        this.setDurataMin();
        this.setDurataOre();
        this.setOraPlecare();
        this.getPret();
    }

    public ZborItem getZborItem (int index){
        return lista.get(index);
    }


    public void setDurataMin(){
        int minute = 0;
        for (ZborItem item : lista){
            minute += item.getDurataMin();
        }
        while(minute>=60){
            this.oreAdunate++;
            minute-=60;
        }
        this.durataMin=minute;
    }

    public int getDurataMin(){
        return this.durataMin;
    }

    public void setDurataOre(){
        for(ZborItem item : lista){
            this.durataOre+=item.getDurataOre();
        }
        this.durataOre+=this.oreAdunate;
    }

    public int getDurataOre(){
        return this.durataOre;
    }

    public void setOraPlecare(){
        this.oraPlecare= this.lista.get(0).getOraPlecare();
    }

    public Time getOraPlecare(){
        return this.oraPlecare;
    }

    public int getPret(){
        int pretCompus=0;
        for(ZborItem  item : lista){
            pretCompus += item.getPret();
        }
        this.pret=pretCompus;
        return pretCompus;
    }

    public String toString(){
        String mesaj = "Traseul contine "+ (lista.size()-1) +" escale, dureaza "+ this.durataOre +
                " ore si " + this.durataMin + " minute. Cu plecare la ora  " + this.oraPlecare + ", la pretul de " + this.pret + " EURO " +
                "si este format din urmatoarele zboruri:\n" ;
        for(ZborItem item : lista){
            mesaj = mesaj + item.toString() + "\n";
        }
        return mesaj;
    }
}
